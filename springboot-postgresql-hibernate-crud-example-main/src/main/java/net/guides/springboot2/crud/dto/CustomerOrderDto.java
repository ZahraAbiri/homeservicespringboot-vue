package net.guides.springboot2.crud.dto;

import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.model.enums.OrderStatus;

import java.util.Date;

public class CustomerOrderDto {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private SubService subService;
    private Long proposedPrice;
    private String jobDescription;
    private Date orderRegistrationDate;
    private Date dateOfWorkPerformed;
    private OrderStatus orderStatus;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public SubService getSubService() {
        return subService;
    }

    public void setSubService(SubService subService) {
        this.subService = subService;
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
