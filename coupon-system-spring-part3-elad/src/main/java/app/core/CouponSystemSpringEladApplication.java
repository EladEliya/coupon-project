package app.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import app.core.exception.CouponSystemException;
import app.core.filters.LoginFilter;
import app.core.jwt.util.JwtUtil;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableScheduling
@SpringBootApplication
@EnableSwagger2
public class CouponSystemSpringEladApplication {

	public static void main(String[] args) throws CouponSystemException {
		
		
		SpringApplication.run(CouponSystemSpringEladApplication.class, args);
		
//		ApplicationContext ctx = 
//	    Company company = new Company(0, "bbbb", "bbbb", "bbbb", null);
//	    Company company1 = new Company(0, "dddd", "dddd", "dddd", null);
    	
//	      Coupon coupon = new Coupon(0, null, "ttt", "ttt", LocalDate.of(2020, 05, 05), LocalDate.of(2022, 05, 05), 10, 10D, "aaa", company1, null);
		
//		CompanyService companyService = ctx.getBean(CompanyService.class);
//		AdminService adminService = ctx.getBean(AdminService.class);
//		Company company = new Company(0, "lll", "lll", "lll", null);
//		adminService.addCompany(company);
//		adminService.addCompany(company1);
//		companyService.logIn("bbbb", "bbbb");
//		companyService.addCoupon(coupon);
		
//		Customer cust =  adminService.getOneCustomer(1);
//		System.out.println(cust);
//		companyService.logIn("bbbb", "bbbb");
//		companyService.deleteCoupon(2);
		
		}
	@Bean
	public FilterRegistrationBean<LoginFilter> loginFilter(JwtUtil jwtUtil) {
		FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new LoginFilter(jwtUtil));
		registrationBean.addUrlPatterns("/api/*");
		registrationBean.setOrder(1);
		return registrationBean;
	}

	}


