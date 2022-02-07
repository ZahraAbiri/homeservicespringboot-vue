package net.guides.springboot2.crud.dto;

import net.guides.springboot2.crud.model.enums.PaymentWay;

import javax.validation.constraints.Size;
import java.util.Date;

public class PaymentDto {
    private PaymentWay paymentWay;

    private OrderDto order;

    private Date paymantDate;

    @Size(max = 12, min = 8, message = "invalid card number!")
    private String cardNumber;

    public PaymentWay getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(PaymentWay paymentWay) {
        this.paymentWay = paymentWay;
    }

    public OrderDto getOrder() {
        return order;
    }

    public void setOrder(OrderDto order) {
        this.order = order;
    }

    public Date getPaymantDate() {
        return paymantDate;
    }

    public void setPaymantDate(Date paymantDate) {
        this.paymantDate = paymantDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}