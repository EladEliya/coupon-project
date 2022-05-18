package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.entities.Customer;
import app.core.jwt.util.JwtUtil;
import app.core.servicies.CustomerService;
@CrossOrigin
@RestController
@RequestMapping("/api/CUSTOMER")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	@Autowired
	JwtUtil jwtUtil;
	
	
	@PostMapping("/purchaseCoupon(Coupon coupon)")
	public ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon,  @RequestHeader String token) {
		try {
			 customerService.purchaseCoupon(coupon, jwtUtil.extractClient(token).clientId);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@GetMapping("/getOneCustomer")
	public ResponseEntity<?> getOneCustomer(  @RequestHeader String token) {
		try {
			Customer customer = customerService.getOneCustomer( jwtUtil.extractClient(token).clientId);
			return ResponseEntity.ok(customer);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/getAllCoupon")
	public ResponseEntity<?> getAllCoupon( @RequestHeader String token){
		try {
			return ResponseEntity.ok(customerService.getAllCoupon( jwtUtil.extractClient(token).clientId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/getCompanyCouponsByCategory/{category}")
	public ResponseEntity<?>  getAllCouponByCategory(@PathVariable Category category,  @RequestHeader String token) {
		try {
			return ResponseEntity.ok(customerService.getAllCouponByCategory(category, jwtUtil.extractClient(token).clientId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/getAllCouponUpToMaxPrice/{maxPrice}")
	public ResponseEntity<?>  getAllCouponUpToMaxPrice(@PathVariable double maxPrice,  @RequestHeader String token) {
		try {
			return ResponseEntity.ok(customerService.getAllCouponUpToMaxPrice(maxPrice, jwtUtil.extractClient(token).clientId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
		
	}
	
	@GetMapping("/ getCustomerDetails")
	public ResponseEntity<?> getCustomerDetails( @RequestHeader String token) {
		try {
			Customer customer = customerService.getCustomerDetails( jwtUtil.extractClient(token).clientId);
			return ResponseEntity.ok(customer);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	

}
}
