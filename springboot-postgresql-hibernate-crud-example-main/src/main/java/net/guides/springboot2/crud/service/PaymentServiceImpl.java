package net.guides.springboot2.crud.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.dto.OrderDto;
import net.guides.springboot2.crud.dto.PaymentDto;
import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.enums.OrderStatus;
import net.guides.springboot2.crud.model.enums.PaymentWay;
import net.guides.springboot2.crud.repository.PaymentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Getter
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private  PaymentDao paymentDao;

    private OrderService orderService;

    private CustomerService customerService;

    private ExpertService expertService;


//    @Override
//    public Customer pay(PaymentDto paymentDto) {
//        assert paymentDto != null;
////        OrderDto doneOrder = paymentDto.getOrder();
//        double price = doneOrder.getProposedPrice();
//        Customer customer = doneOrder.getCustomer();
////        if (paymentDto.getPaymentWay().equals(PaymentWay.CREDIT))
//            customer = customerService.payByCredit(customer, price);
//
//        Expert expertDto = doneOrder.getExpert();
//        double expertFees = price * 0.7;
//        expertDto.setCredit((long) (expertDto.getCredit() + expertFees));
//        doneOrder.setOrderStatus(OrderStatus.PAID);
////        expertService.update(expertDto);
//        orderService.updateStatus(doneOrder);
//        paymentDao.save(PaymentMapper.mapPaymentDtoToPaymentForSaving(paymentDto));
//        return customer;
//    }
}
