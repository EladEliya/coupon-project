package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Company;
import app.core.entities.Customer;
//import app.core.entities.Store;
import app.core.servicies.AdminService;

@CrossOrigin
@RestController
@RequestMapping("/api/ADMINISTRATOR")
public class AdminController {
	
	
	@Autowired
	private AdminService adminService;
	
	
	//create
	@PostMapping("/addCompany")
	public ResponseEntity<?> addCompany(@RequestBody Company company,  @RequestHeader String token){
		try {
			int id = adminService.addCompany(company);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	// update
	@PutMapping("/updateCompany")
	public ResponseEntity<?> updateCompany(@RequestBody Company company,  @RequestHeader String token){
		try {
			adminService.updateCompany(company);;
//			return ResponseEntity.ok().build();
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	@DeleteMapping("/{companyId}")
	public ResponseEntity<?> deleteStore(@PathVariable int companyId,  @RequestHeader String token){
		try {
			adminService.deleteCompany(companyId);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/getAllCompanies")
	public ResponseEntity<?> getAllCompanies( @RequestHeader String token){
		try {
			return ResponseEntity.ok(adminService.getAllCompanies());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/get-company/{companyId}")
	public ResponseEntity<?> getOneCompany(@PathVariable int companyId,  @RequestHeader String token){	
		try {
		Company company = adminService.getOneCompany(companyId);
		return ResponseEntity.ok(company);
	} catch (Exception e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
	}
}
	
	@PostMapping("/addcustomer")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer,  @RequestHeader String token){
		try {
			int id = adminService.addCustomer(customer);
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
		
		@PutMapping("/updatecustomer")
		public ResponseEntity<?> updateCustomer(@RequestBody Customer customer,  @RequestHeader String token){
			try {
				adminService.updateCustomer(customer);
//				return ResponseEntity.ok().build();
				return ResponseEntity.ok(null);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		
			
		}
		
		@DeleteMapping("/delete-customer/{customerId}")
		public ResponseEntity<?> deleteCustomer(@PathVariable int customerId,  @RequestHeader String token){
			try {
				adminService.deleteCustomer(customerId);
				return ResponseEntity.ok(null);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			
		
		}
		@GetMapping("/getAllCustomer")
		public ResponseEntity<?> getAllCustomer( @RequestHeader String token){
			try {
				return ResponseEntity.ok(adminService.getAllCustomer());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}
		
		@GetMapping("/getOneCustomer/{customerId}")
		public ResponseEntity<?> getOneCustomer(@PathVariable int customerId,  @RequestHeader String token){	
			try {
			Customer customer = adminService.getOneCustomer(customerId);
			return ResponseEntity.ok(customer);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
		
		@DeleteMapping("/deleteExpiredCoupon")
		public ResponseEntity<?> deleteExpiredCoupon ( @RequestHeader String token){
			try {
				adminService.deleteExpiredCoupon();
				return ResponseEntity.ok(null);
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
			
		
		}
		
		
		
		
		
		
		
	}
	
	
	
		
		
	
	
	
	
	


