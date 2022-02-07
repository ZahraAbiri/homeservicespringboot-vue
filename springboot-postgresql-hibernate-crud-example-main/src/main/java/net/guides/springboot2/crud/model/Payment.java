package net.guides.springboot2.crud.model;

import lombok.*;
import net.guides.springboot2.crud.model.enums.PaymentWay;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private PaymentWay PaymentWay;

    @OneToOne
    private Order order;

    @CreationTimestamp
    private Date paymantDate;

    private String cardNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public net.guides.springboot2.crud.model.enums.PaymentWay getPaymentWay() {
        return PaymentWay;
    }

    public void setPaymentWay(net.guides.springboot2.crud.model.enums.PaymentWay paymentWay) {
        PaymentWay = paymentWay;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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
