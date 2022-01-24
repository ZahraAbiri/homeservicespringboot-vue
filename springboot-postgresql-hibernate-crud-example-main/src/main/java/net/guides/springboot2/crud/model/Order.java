package net.guides.springboot2.crud.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.guides.springboot2.crud.model.enums.OrderStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(nullable = false)
    private SubService subService;
    private Long proposedPrice;
    @Column(length = 300)
    private String jobDescription;
    @CreationTimestamp
    private Date orderRegistrationDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfWorkPerformed;
    @OneToOne
    private Address address;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER)
    private Set<Offer> offers = new HashSet<>();
    @ManyToOne
    private Expert expert;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void setOffers(Set<Offer> offers) {
        this.offers = offers;
    }

    public Expert getExpert() {
        return expert;
    }

    public void setExpert(Expert expert) {
        this.expert = expert;
    }

    @Override
    public String toString() {
        return "Instruction{" +
                "id=" + id +
                ", proposedPrice=" + proposedPrice +
                ", jobDescription='" + jobDescription + '\'' +
                ", orderRegistrationDate=" + orderRegistrationDate +
                ", dateOfWorkPerformed=" + dateOfWorkPerformed +
                ", address='" + address + '\'' +
                ", orderStatus=" + orderStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order that = (Order) o;
        return Objects.equals(id, that.id) && Objects.equals(proposedPrice, that.proposedPrice) && Objects.equals(jobDescription, that.jobDescription) && Objects.equals(orderRegistrationDate, that.orderRegistrationDate) && Objects.equals(dateOfWorkPerformed, that.dateOfWorkPerformed) && Objects.equals(address, that.address) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, proposedPrice, jobDescription, orderRegistrationDate, dateOfWorkPerformed, address, orderStatus);
    }
}
