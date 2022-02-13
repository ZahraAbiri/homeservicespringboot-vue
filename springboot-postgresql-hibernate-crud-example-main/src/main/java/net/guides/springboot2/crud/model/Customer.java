package net.guides.springboot2.crud.model;

import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data

@Getter
@Service
public class Customer extends Person{
    private boolean isEnabled;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }
}
