package net.guides.springboot2.crud.dto;

import javax.validation.constraints.Size;

public class PaymentDto {
    private  Integer customerId;
    private Integer orderId;
    private Integer expertId;
    private  Long payment;


    @Size(max = 16, min = 16, message = "invalid card number!")
    private String cardNumber;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Long getPayment() {
        return payment;
    }

    public void setPayment(Long payment) {
        this.payment = payment;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Integer getExpertId() {
        return expertId;
    }

    public void setExpertId(Integer expertId) {
        this.expertId = expertId;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}