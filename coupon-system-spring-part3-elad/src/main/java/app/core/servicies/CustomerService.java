package app.core.servicies;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Coupon;
import app.core.entities.Customer;
import app.core.entities.Coupon.Category;
import app.core.exception.CouponSystemException;

@Service
@Transactional
@Scope("prototype")
public class CustomerService extends ClientService {



	@Override
	public boolean logIn(String email, String password) {
		if (customerRepo.existsByEmailAndPassword(email, password)) {
				return true;
		} else {
			return false;
		}
	}

	public void purchaseCoupon(Coupon coupon, int customerId) throws CouponSystemException {
		if (customerId == 0) {
			throw new CouponSystemException("purchaseCoupon failed - need to login first");
		}
		Customer customer = customerRepo.findById(customerId).get();
		Optional<Coupon> opt = couponRepo.findById(coupon.getId());

		if (opt.isPresent()) {
			Coupon couponFromDb = couponRepo.getById(coupon.getId());
			if (customer.getCoupons().contains(couponFromDb)) {
				throw new CouponSystemException("purchaseCoupon failed - ccoupon " + coupon.getId() + " alredy exsits");
			}
			if (couponFromDb.getAmount() < 1) {
				throw new CouponSystemException(
						"purchaseCoupon failed - the coupon " + coupon.getId() + " out of stock");
			}
			if (couponFromDb.getEndDate().isBefore(LocalDate.now())) {
				throw new CouponSystemException(
						"purchaseCoupon failed - the date of coupon " + coupon.getId() + " has passed");
			}
			customer.addCoupon(couponFromDb);
			couponFromDb.setAmount(couponFromDb.getAmount() - 1);
		}

	}

	public Customer getOneCustomer(int customerId) throws CouponSystemException {
		Optional<Customer> opt = customerRepo.findById(customerId);
		if (opt.isPresent()) {
			Customer customerFromDb = opt.get();
			return customerFromDb;
		} else {
			throw new CouponSystemException("getOneCustomer failed - this customer not exist");
		}

	}

	public List<Coupon> getAllCoupon( int customerId) {
		return couponRepo.findByCustomersId(customerId);
	}

	public List<Coupon> getAllCouponByCategory(Category category, int customerId) {
		return couponRepo.findByCustomersIdAndCategory(customerId, category);
	}

	public List<Coupon> getAllCouponUpToMaxPrice(double maxPrice, int customerId) {
		return couponRepo.findByCustomersIdAndPriceLessThan(customerId, maxPrice);
	}

	public Customer getCustomerDetails( int customerId) {
		return customerRepo.findById(customerId).get();
	}

}
