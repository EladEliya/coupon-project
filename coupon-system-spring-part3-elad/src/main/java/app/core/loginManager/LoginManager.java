package app.core.loginManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import app.core.exception.CouponSystemException;
import app.core.loginManager.Clients.ClientType;
import app.core.servicies.AdminService;
import app.core.servicies.ClientService;
import app.core.servicies.CompanyService;
import app.core.servicies.CustomerService;

@Component
public class LoginManager {
	
	@Autowired
	private ApplicationContext ctx;
	
	public ClientService login(String email, String password, ClientType clientType) throws CouponSystemException   {
		
	ClientService clientService = null;
	
	switch(clientType) {
	case ADMINISTRATOR:
		clientService = ctx.getBean(AdminService.class);
		break;
	case COMPANY:
		clientService = ctx.getBean(CompanyService.class);
		break;
	case CUSTOMER:
		clientService = ctx.getBean(CustomerService.class);
		break;
	}
	if (clientService.logIn(email, password)) {
		return clientService;
		
		
	}else {
		return null;
	}
	
	
		
	}
}
