package app.core.repos;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;

public interface CouponRepo extends JpaRepository<Coupon, Integer> {
	
	
	
	
	
	public boolean existsByIdAndCompanyId(int couponId , int companyId);
	public boolean   existsByTitleAndCompanyId (String title , int CompanyId);
//	void deleteByIdAndCompanyId(int couponId , int companyId);
	public List<Coupon> findByCompanyId(int companyID);
	
	public List<Coupon> findByCategoryAndCompanyId(Category category , int companyID);
	
	public List<Coupon> findByCompanyIdAndPriceLessThan (int companyId , Double maxPrice);
	
	public List<Coupon> findByCustomersId (int customerId);
	
	
	List<Coupon> findByCustomersIdAndCategory (int customerId , Category category);
	
	List<Coupon> findByCustomersIdAndPriceLessThan (int customerId , Double maxPrice);
	
	public void deleteByEndDateBefore (LocalDate time);
	

}
