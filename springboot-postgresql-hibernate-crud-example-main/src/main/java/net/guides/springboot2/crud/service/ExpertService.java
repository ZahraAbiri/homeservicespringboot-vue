package net.guides.springboot2.crud.service;


import lombok.Getter;
import lombok.Setter;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.repository.ExpertDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Optional;

@Getter
@Setter
public class ExpertService {
    @Autowired
    private ExpertDao expertDao;
    public Expert save(Expert expert) throws IOException {
        expertDao.save(expert);
        return expert;
    }

    public Expert findByEmailAddress(String emailAddress) {
        Optional<Expert> expert = expertDao.findByEmailAddress(emailAddress);
        if (expert.isPresent()) {
            Expert foundedExpert = expert.get();
            return foundedExpert;
        } else {
            throw new RuntimeException("emailAddress not exist!");
        }
    }

    public boolean isExist(String emailAddress) {
        Optional<Expert> expert = expertDao.findByEmailAddress(emailAddress);
        if (expert.isPresent()) {
            throw new RuntimeException("this emailAddress exist!");
        } else {
            return false;
        }
    }
    public void updateScore(Expert expert, Double newScore) {
        Double expertScore = expert.getScore();
        Double new_Score = (expertScore + newScore);
        expert.setScore(new_Score);
//        update(expert);
    }

//    private void update(Expert expert) {
//            expertDao.update(expert);
//        }


}
