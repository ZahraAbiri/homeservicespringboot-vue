package net.guides.springboot2.crud.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data

@Getter
@Service
public class Customer extends Person{

//    public Customer() {
//        super();
//    }
}
