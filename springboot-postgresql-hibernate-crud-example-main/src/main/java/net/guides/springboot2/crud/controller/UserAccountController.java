package net.guides.springboot2.crud.controller;

import net.guides.springboot2.crud.model.ConfirmationToken;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.repository.ConfirmationTokenRepository;
import net.guides.springboot2.crud.repository.CustomerDao;
import net.guides.springboot2.crud.service.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/us")
//@Controller
public class UserAccountController {
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;

	@RequestMapping(value="/registers", method=RequestMethod.GET)
	public ModelAndView displayRegistration(ModelAndView modelAndView, Customer customer)
	{
		modelAndView.addObject("customer", customer);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	@PostMapping("/register")
//	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView registerUser(ModelAndView modelAndView, Customer customer)
	{
		
		Customer existingUser = customerDao.findByEmailAddressIgnoreCase(customer.getEmailAddress());
		if(existingUser != null)
		{
			modelAndView.addObject("message","This email already exists!");
			modelAndView.setViewName("http://localhost:8081/error");
		}
		else 
		{
			customerDao.save(customer);
			
			ConfirmationToken confirmationToken = new ConfirmationToken(customer);
			
			confirmationTokenRepository.save(confirmationToken);
			
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(customer.getEmailAddress());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("chand312902@gmail.com");
			mailMessage.setText("To confirm your account, please click here : "
			+"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());
			
			emailSenderService.sendEmail(mailMessage);
			
			modelAndView.addObject("emailId", customer.getEmailAddress());
			
			modelAndView.setViewName("successfulRegisteration");
		}
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
	{
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);
		
		if(token != null)
		{
			Customer user = customerDao.findByEmailAddressIgnoreCase(token.getCustomer().getEmailAddress());
			user.setEnabled(true);
			customerDao.save(user);
			modelAndView.setViewName("accountVerified");
		}
		else
		{
			modelAndView.addObject("message","The link is invalid or broken!");
			modelAndView.setViewName("error");
		}
		
		return modelAndView;
	}

	public CustomerDao getUserRepository() {
		return customerDao;
	}

	public void setUserRepository(CustomerDao userRepository) {
		this.customerDao = userRepository;
	}

	public ConfirmationTokenRepository getConfirmationTokenRepository() {
		return confirmationTokenRepository;
	}

	public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
		this.confirmationTokenRepository = confirmationTokenRepository;
	}

	public EmailSenderService getEmailSenderService() {
		return emailSenderService;
	}

	public void setEmailSenderService(EmailSenderService emailSenderService) {
		this.emailSenderService = emailSenderService;
	}
	
}
