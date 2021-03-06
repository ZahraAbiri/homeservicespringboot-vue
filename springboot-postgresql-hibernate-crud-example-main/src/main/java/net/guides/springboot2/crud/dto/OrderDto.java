package net.guides.springboot2.crud.dto;

import net.guides.springboot2.crud.model.Customer;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.Offer;
import net.guides.springboot2.crud.model.enums.OrderStatus;

import java.util.Date;
import java.util.Set;

public class OrderDto {
    private String customerEmailAddrress;
    private Integer subServiceId;

    private Customer customer;
    private Long proposedPrice;
    private String jobDescription;
    private Date orderRegistrationDate;

    private Date dateOfWorkPerformed;
    private String city;
    private String streetAddress;
    private String houseNumber;
    private Long zipCode;
    private OrderStatus orderStatus;
    private Offer offers;

    private Expert expert;

    public void setCustomerEmailAddrress(String customerEmailAddrress) {
        this.customerEmailAddrress = customerEmailAddrress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Offer getOffers() {
        return offers;
    }

    public void setOffers(Offer offers) {
        this.offers = offers;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    public String getCustomerEmailAddrress() {
        return customerEmailAddrress;
    }

    public void setCustomerId(String customerEmailAddrress) {
        this.customerEmailAddrress = customerEmailAddrress;
    }

    public Integer getSubServiceId() {
        return subServiceId;
    }

    public void setSubServiceId(Integer subServiceId) {
        this.subServiceId = subServiceId;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public Long getZipCode() {
        return zipCode;
    }

    public void setZipCode(Long zipCode) {
        this.zipCode = zipCode;
    }

    public Long getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(Long proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Date getOrderRegistrationDate() {
        return orderRegistrationDate;
    }

    public void setOrderRegistrationDate(Date orderRegistrationDate) {
        this.orderRegistrationDate = orderRegistrationDate;
    }

    public Date getDateOfWorkPerformed() {
        return dateOfWorkPerformed;
    }

    public void setDateOfWorkPerformed(Date dateOfWorkPerformed) {
        this.dateOfWorkPerformed = dateOfWorkPerformed;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
