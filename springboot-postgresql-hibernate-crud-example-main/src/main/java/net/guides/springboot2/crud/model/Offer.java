package net.guides.springboot2.crud.model;


import lombok.*;
import net.guides.springboot2.crud.model.enums.OfferStatus;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne

    private Expert expert;
    @CreationTimestamp
    private Date registrationDate;
    private Long proposedPrice;
    private Double durationOfWork;
    @Temporal(TemporalType.TIME)
    private Date startTime;
    @Enumerated(EnumType.STRING)
    private OfferStatus offerStatus;
    @ManyToOne
    private Order order;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

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

    public OfferStatus getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
