package app.core.dailyjob;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import app.core.servicies.AdminService;

@Component
public class CouponExpirationDailyJob {
	
	@Autowired
	AdminService adminService;
	
	
	@Scheduled(cron = "1 0 0 * * * ")
	public void deleteExpiredCoupon() {
		System.out.println("daily job checks expirad coupon");
		adminService.deleteExpiredCoupon();
	}

}
