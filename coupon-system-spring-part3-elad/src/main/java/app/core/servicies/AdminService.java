package app.core.servicies;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.core.entities.Company;
import app.core.entities.Customer;
import app.core.exception.CouponSystemException;

@Service
@Transactional
public class AdminService extends ClientService {
	
	@Value("${admin.email}")
	private String email;
	@Value("${admin.password}")
	private String password;

//	private final String email = "admin@admin.com";
//	private final String password = "admin";

	@Override
	public boolean logIn(String email, String password) {
		return email.equals(this.email) && password.equals(this.password);

	}

	public int addCompany(Company company) throws CouponSystemException {
		if (companyRepo.existsByEmailOrName(company.getEmail(), company.getName())) {
			throw new CouponSystemException("add company faild, company with that Email or Name is already exist");
		}
		companyRepo.save(company);
		return company.getId();
	}

	public void updateCompany(Company company) throws CouponSystemException {
		Optional<Company> opt = companyRepo.findById(company.getId());
		if (opt.isPresent()) {
			Company companyFromDb = opt.get();
			companyFromDb.setEmail(company.getEmail());
			companyFromDb.setPassword(company.getPassword());
		} else {
			throw new CouponSystemException("update company faild- company not exist");

		}
	}

	public void deleteCompany(int companyId) {
		companyRepo.deleteById(companyId);
	}

public List<Company> getAllCompanies(){
	
	List<Company> allCompanies =  companyRepo.findAll();
	return allCompanies;
}

public Company getOneCompany(int companyId) throws CouponSystemException {
	Optional<Company> opt = companyRepo.findById(companyId);
	if(opt.isPresent()) {
		return opt.get();
	} else {
		throw new CouponSystemException("company id not exist");
	}
	
}



public int addCustomer(Customer customer ) throws CouponSystemException{
	if(!customerRepo.existsByEmail(customer.getEmail())){
		customerRepo.save(customer);
		return customer.getId();
	}else {
		throw new CouponSystemException("addCustomer faild- this email customer is already exists");
	}
	
	
}

public void updateCustomer(Customer customer) throws CouponSystemException {
	Optional<Customer> opt = customerRepo.findById(customer.getId());
	if (opt.isPresent()) {
		customerRepo.save(customer);
	}else {
		throw new CouponSystemException("updateCustomer faild - customer not found");
	}
}


public void deleteCustomer(int customerId) throws CouponSystemException {
	if(customerRepo.existsById(customerId)) {
		customerRepo.deleteById(customerId);
	}else {
		throw new CouponSystemException("delete customer faild - customer not exists");
	}
}


public List<Customer> getAllCustomer(){
	List<Customer> allCustomers = customerRepo.findAll();
	return allCustomers;
	
}

public Customer getOneCustomer(int custumerId) throws CouponSystemException {
	if(customerRepo.existsById(custumerId)) {
     	return customerRepo.findById(custumerId).get();
	}else {
		throw new CouponSystemException("getOneCustomer faild, customer not exists");
	}
}

public void deleteExpiredCoupon () {
	couponRepo.deleteByEndDateBefore(LocalDate.now());
}

	

	
	
	
	
	
	

}
