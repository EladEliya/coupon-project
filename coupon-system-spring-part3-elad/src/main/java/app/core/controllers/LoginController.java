package app.core.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import app.core.exception.CouponSystemException;
import app.core.jwt.util.JwtUtil;
import app.core.jwt.util.JwtUtil.ClientDetails;
import app.core.loginManager.Clients.ClientType;
import app.core.loginManager.LoginManager;
import app.core.repos.CompanyRepo;
import app.core.repos.CustomerRepo;

@CrossOrigin
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private LoginManager loginManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private CompanyRepo companyRepo;
	@Autowired
	private CustomerRepo customerRepo;

	// in the http body: email=aaa&password=bbb
	@PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String login(@RequestParam String email, @RequestParam String password, @RequestParam String clientType)	throws CouponSystemException {
		ClientType type = ClientType.valueOf(clientType);
		try {
			if (this.loginManager.login(email, password, type) != null) {
				int clientId;
				if (type == ClientType.ADMINISTRATOR) {
					clientId = 0;
				} else if (type == ClientType.COMPANY) {
					clientId = companyRepo.findCompanyByEmailAndPassword(email, password).getId();
				} else {
					clientId = customerRepo.findCustomerByEmailAndPassword(email, password).getId();
				}
				ClientDetails clientdetails = new ClientDetails(email, type, clientId);
				return jwtUtil.generateToken(clientdetails);

			} else{
				throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "bad request");
			}
		} catch (CouponSystemException e) {
			throw new CouponSystemException (e.getMessage());
			
		}

	}

}
