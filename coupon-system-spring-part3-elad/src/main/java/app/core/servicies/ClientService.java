package app.core.servicies;

import org.springframework.beans.factory.annotation.Autowired;

import app.core.repos.CompanyRepo;
import app.core.repos.CouponRepo;
import app.core.repos.CustomerRepo;

public abstract class ClientService {
	
	@Autowired
	protected CompanyRepo companyRepo;
	@Autowired
	protected CouponRepo couponRepo;
	@Autowired
	protected CustomerRepo customerRepo;
	
	
	public boolean logIn(String email, String password) {
		return false;
	}
	
	
	
	

}
