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
import app.core.entities.Coupon;
import app.core.entities.Coupon.Category;
import app.core.jwt.util.JwtUtil;
import app.core.servicies.CompanyService;
@CrossOrigin
@RestController
@RequestMapping("/api/COMPANY")
public class CompanyController {

	@Autowired
	CompanyService companyService;
	@Autowired
	JwtUtil jwtUtil;

	@PostMapping("/addCoupon")
	public ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			
			int id = companyService.addCoupon(coupon,jwtUtil.extractClient(token).clientId );
			return ResponseEntity.ok(id);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@GetMapping("/getCompany")
	public ResponseEntity<?> getCompany( @RequestHeader String token) {
		try {
			Company company = companyService.getCompany(jwtUtil.extractClient(token).clientId);
			return ResponseEntity.ok(company);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@PutMapping("/updateCoupon")
	public ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader String token) {
		try {
			companyService.updateCoupon(coupon,jwtUtil.extractClient(token).clientId);
//			return ResponseEntity.ok().build();
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}

	@DeleteMapping("/deleteCoupon/{couponId}")
	public ResponseEntity<?> deleteCoupon(@PathVariable int couponId, @RequestHeader String token) {
		try {
			companyService.deleteCoupon(couponId,jwtUtil.extractClient(token).clientId);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("/ getCompanyCoupons")
	public ResponseEntity<?> getCompanyCoupons( @RequestHeader String token) {
		try {
			return ResponseEntity.ok(companyService.getCompanyCoupons(jwtUtil.extractClient(token).clientId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("/getCompanyCouponsByCategory/{category}")
	public ResponseEntity<?> getCompanyCouponsByCategory(@PathVariable Category category, @RequestHeader String token) {
		try {
			return ResponseEntity.ok(companyService.getCompanyCouponsByCategory(category,jwtUtil.extractClient(token).clientId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/getCouponsByMaxPrice/{maxPrice}")
	public ResponseEntity<?> getCouponsByMaxPrice(double maxPrice, @RequestHeader String token) {
		try {
			return ResponseEntity.ok(companyService.getCouponsByMaxPrice(maxPrice,jwtUtil.extractClient(token).clientId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@GetMapping("/getCompanyDetails")
	public ResponseEntity<?> getCompanyDetails( @RequestHeader String token) {
		try {
			return ResponseEntity.ok(companyService.getCompanyDetails(jwtUtil.extractClient(token).clientId));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	
	
	
	

}
