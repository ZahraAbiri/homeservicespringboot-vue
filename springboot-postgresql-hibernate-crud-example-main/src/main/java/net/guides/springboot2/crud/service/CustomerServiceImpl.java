package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import net.guides.springboot2.crud.dto.Customerdto;
import net.guides.springboot2.crud.dto.PaymentDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.model.Payment;
import net.guides.springboot2.crud.model.enums.OrderStatus;
import net.guides.springboot2.crud.model.enums.PaymentWay;
import net.guides.springboot2.crud.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OfferDao offerDao;
    @Autowired
    private ExpertDao expertDao;
    @Autowired
    private PaymentDao paymentDao;

    @Override
    public Customer findByEmailCustomerAndPassword(String email, String password) throws ResourceNotFoundException {
        return null;
    }



//    @Override
//    public Customer save(Customerdto customerdto) throws ResourceNotFoundException
//    {
//        Customer customer = new Customer();
//        String encodedPassword = passwordEncoder.encode(customerdto.getPassword());
//        customer.setPassword(encodedPassword);
//
//        String randomCode = RandomString.make(64);
//        customer.setVerificationCode(randomCode);
//        customer.setEnabled(false);
//
//
//        customer.setFirstname(customerdto.getFirstname());
//        customer.setLastname(customerdto.getLastname());
//        customer.setPersonStatuse(customerdto.getPersonStatuse());
//        customer.setCredit(String.valueOf(customerdto.getCredit()));
//        customer.setEmailAddress(customerdto.getEmailAddress());
//        customer.setPassword(customerdto.getPassword());
//        customer.setRegistrationDate(customerdto.getRegistrationDate());
////        repo.save(user);
//
//        sendVerificationEmail(customer, siteURL);
//        return customerDao.save(customer);
//    }

    private void sendVerificationEmail(Customer customer, String siteURL) {
    }

    @Override
    public Customer getById(Integer customerId) throws ResourceNotFoundException {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerId));
        return customer;
    }

    @Override
    public Customer deleteById(Integer customerId) throws ResourceNotFoundException {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + customerId));
        customerDao.delete(customer);
        return customer;
    }

    @Override
    public Customer updateById(Integer customerId, Customer customerDetails) throws ResourceNotFoundException {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for this id :: " + customerId));
        customer.setCredit(customerDetails.getCredit());
        customer.setPassword(customerDetails.getPassword());
        customer.setEmailAddress(customerDetails.getEmailAddress());
        customer.setLastname(customerDetails.getLastname());
        customer.setFirstname(customerDetails.getFirstname());
        customer.setPersonStatuse(customerDetails.getPersonStatuse());
        customer.setRole(customerDetails.getRole());
        customer.setRegistrationDate(customerDetails.getRegistrationDate());
        final Customer updatedcustomer = customerDao.save(customer);
        return updatedcustomer;
    }

    @Override
    public Payment paymentByCard(PaymentDto paymentDto) throws ResourceNotFoundException {
        Customer customer = new Customer();
        Order order = new Order();
        Payment payment = new Payment();
        Expert expert = new Expert();
        double expPersent = paymentDto.getPayment() * 0.07;
        String credit = expert.getCredit();
        long credits = Long.parseLong(credit);
        expert.setCredit(String.valueOf(credits + expPersent));
        try {
            expertDao.save(expert);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Optional<Customer> custome = customerDao.findById(paymentDto.getCustomerId());
        if (custome.isPresent()) {
            customer = custome.get();
        }
        Optional<Order> orders = orderDao.findById(paymentDto.getCustomerId());
        if (orders.isPresent()) {
            order = orders.get();
        }
        order.setOrderStatus(OrderStatus.PAID);
        payment.setOrder(order);
        payment.setCustomer(customer);
        payment.setCardNumber(paymentDto.getCardNumber());
        payment.setPaymentWay(PaymentWay.ONLINE);
        return paymentDao.save(payment);

    }


    @Override
    public List<Customer> get() {
        return customerDao.findAll();
    }


    @Override
    public Payment paymentByCredit(PaymentDto paymentDto) {
        Customer customer = new Customer();
        Order order = new Order();
        Payment payment = new Payment();
        Expert expert = new Expert();
        Optional<Expert> exp = expertDao.findById(paymentDto.getExpertId());
        if (exp.isPresent()) {
            expert = exp.get();
        }
        double expPersent = paymentDto.getPayment() * 0.07;
        String credit = expert.getCredit();
        long credits = Long.parseLong(credit);
        expert.setCredit(String.valueOf(credits + expPersent));
        try {
            expertDao.save(expert);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Optional<Customer> custome = customerDao.findById(paymentDto.getCustomerId());
        if (custome.isPresent()) {
            customer = custome.get();
        }
        Optional<Order> orders = orderDao.findById(paymentDto.getCustomerId());
        if (orders.isPresent()) {
            order = orders.get();
        }
        long pay = Long.parseLong(customer.getCredit()) - paymentDto.getPayment();
        customer.setCredit(String.valueOf(pay));
        order.setOrderStatus(OrderStatus.PAID);
        payment.setOrder(order);
        payment.setCustomer(customer);
        payment.setCardNumber(paymentDto.getCardNumber());
        payment.setPaymentWay(PaymentWay.CREDIT);
        return paymentDao.save(payment);

    }
//    @Autowired
//    private UserRepository repo;

//    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private JavaMailSender mailSender;


    public void register(Customer customer, String siteURL) {

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer userEntity = new Customer();
        userEntity.setEmailAddress(username);
        Optional<Customer> found = customerDao.findOne(Example.of(userEntity));
        if (!found.isPresent())
            throw new UsernameNotFoundException("[" + username + "] not found");

        Customer user = found.get();
        Set<GrantedAuthority> authorities = new HashSet<>();
//        for (RoleEntity role : user.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
        return new org.springframework.security.core.userdetails.User(user.getEmailAddress(),
                user.getPassword(), authorities);
    }
    }

//    private void sendVerificationEmail(Customer customer, String siteURL) {
//        String toAddress = customer.getEmailAddress();
//        String fromAddress = "Your email address";
//        String senderName = "Your company name";
//        String subject = "Please verify your registration";
//        String content = "Dear [[name]],<br>"
//                + "Please click the link below to verify your registration:<br>"
//                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
//                + "Thank you,<br>"
//                + "Your company name.";
//
//        MimeMessage message = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        helper.setFrom(fromAddress, senderName);
//        helper.setTo(toAddress);
//        helper.setSubject(subject);
//
//        content = content.replace("[[name]]", user.getFullName());
//        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
//
//        content = content.replace("[[URL]]", verifyURL);
//
//        helper.setText(content, true);
//
//        mailSender.send(message);
//    }


