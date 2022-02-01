package net.guides.springboot2.crud.dto;

import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.model.enums.PersonStatuse;
import net.guides.springboot2.crud.model.enums.Role;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Expertdto {
    private String firstname;
    private String lastname;
    private String emailAddress;
    private String password;
    private PersonStatuse personStatuse;
    private Date registrationDate;
    private Long credit;
    private Role role;
    private byte[] photo;
    private Double score;
    private Set<SubService> services = new HashSet<>();

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Set<SubService> getServices() {
        return services;
    }

    public void setServices(Set<SubService> services) {
        this.services = services;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PersonStatuse getPersonStatuse() {
        return personStatuse;
    }

    public void setPersonStatuse(PersonStatuse personStatuse) {
        this.personStatuse = personStatuse;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
