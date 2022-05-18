package app.core.servicies;



import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Company;
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.exception.CouponSystemException;

@Service
@Transactional
//@Scope("prototype")

public class CompanyService extends ClientService {
	
//	private int companyId;
//
//	
//	public int getCompanyId() {
//		return companyId;
//	}
//
//
//	public void setCompanyId(int companyId) {
//		this.companyId = companyId;
//	}


	@Override
	public boolean logIn(String email, String password) {
		if (companyRepo.existsByEmailAndPassword(email, password)) {
//			Company company = companyRepo.findCompanyByEmailAndPassword(email, password);
			
			return true;
		} else {
			return false;
		}
	}
	
	public int addCoupon(Coupon coupon, int companyId) throws CouponSystemException  {
		if(!couponRepo.existsByTitleAndCompanyId(coupon.getTitle(),companyId) ) {
			Optional<Company> opt = companyRepo.findById(companyId);
			if (opt.isPresent()) {
			Company company = opt.get();
			company.addCoupon(coupon);
			return coupon.getId();
			}
			
		}

		throw new CouponSystemException("addCoupon faid- this coupon already exists");

	}
	
	public Company getCompany(int companyId) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findById(companyId);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			throw new CouponSystemException("get company faild - company not exist.");
		}
	}
	
	public void updateCoupon(Coupon coupon, int companyId) throws CouponSystemException {
		if (couponRepo.existsByIdAndCompanyId(coupon.getId(), companyId)) {
			couponRepo.save(coupon);

		} else
			throw new CouponSystemException(" updatCoupon faild - can not change coupon id company id ");
	}
	
	
		public void deleteCoupon(int couponId, int companyId) throws CouponSystemException {
			if(couponRepo.existsByIdAndCompanyId(couponId, companyId)) {
				couponRepo.deleteById(couponId);				
			}else {
				throw new CouponSystemException("deleteCoupon failed ");
			}
		}
		
			public List<Coupon> getCompanyCoupons( int companyId){
				List<Coupon> coupons =	couponRepo.findByCompanyId(companyId);
				return coupons;
			}
			
			
			public List<Coupon>	getCompanyCouponsByCategory(Category category, int companyId){
				List<Coupon> coupons =	couponRepo.findByCategoryAndCompanyId(category ,companyId)	;
				return coupons;
			}
			
			public  List<Coupon> getCouponsByMaxPrice(double maxPrice, int companyId) {
				
				List<Coupon> coupons	=	couponRepo.findByCompanyIdAndPriceLessThan(companyId, maxPrice);
				return coupons;
				
				
			}
			
			public  Company getCompanyDetails( int companyId) throws CouponSystemException {
				Optional<Company> opt = companyRepo.findById(companyId);
				if(opt.isPresent()) {
					return opt.get();
				} else {
					throw new CouponSystemException("getCompanyDetials faild - company- " + companyId + " not exist.");
				}
				
			}
				
			
			
	}
	
	
	



