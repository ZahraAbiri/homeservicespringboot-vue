package net.guides.springboot2.crud.dto;

import java.util.Date;

public class OfferDto {
    private String expertEmailAddress;
    private Integer orderId;
    private Date registrationDate;
    private Long proposedPrice;
    private Double durationOfWork;

    private Date startTime;
    private String description;

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getProposedPrice() {
        return proposedPrice;
    }

    public void setProposedPrice(Long proposedPrice) {
        this.proposedPrice = proposedPrice;
    }

    public Double getDurationOfWork() {
        return durationOfWork;
    }

    public void setDurationOfWork(Double durationOfWork) {
        this.durationOfWork = durationOfWork;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExpertEmailAddress() {
        return expertEmailAddress;
    }

    public void setExpertEmailAddress(String expertEmailAddress) {
        this.expertEmailAddress = expertEmailAddress;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
}
