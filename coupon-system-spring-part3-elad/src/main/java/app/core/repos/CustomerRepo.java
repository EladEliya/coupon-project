
package app.core.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import app.core.entities.Company;
import app.core.entities.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	
	
	boolean existsByEmail(String email);
	
	
	boolean  existsByEmailAndPassword (String email , String password);
	
	public Customer  findCustomerByEmailAndPassword (String email , String password);

}
