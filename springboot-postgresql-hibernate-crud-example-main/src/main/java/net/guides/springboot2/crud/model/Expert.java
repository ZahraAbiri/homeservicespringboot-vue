package net.guides.springboot2.crud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
public class Expert extends Person {
    @Lob
    @Column(columnDefinition = "BLOB" ,length = 300)
    private byte[] photo;
    private Double score;
    @ManyToMany(mappedBy = "experts",fetch = FetchType.LAZY)
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

    @Override
    public String toString() {
        return "Expert{" +
                "photo=" + Arrays.toString(photo) +
                ", score=" + score +
                '}';
    }
}
