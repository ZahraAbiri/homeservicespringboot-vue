package net.guides.springboot2.crud.service;

import lombok.Getter;
import lombok.Setter;
import net.guides.springboot2.crud.model.Person;
import net.guides.springboot2.crud.repository.PersonDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Getter
@Setter
@Service
public class PersonService {
    @Autowired
    private static PersonDao personDao;

    public Person save(Person person) {
        personDao.save(person);
        return person;
    }

//    public void update(Person person) {
//        personDao.update(person);
//    }

    public static Person findByEmailAddress(String emailAddress) {
//        Optional<Person> person = personDao.findByEmailAddress(emailAddress);
//        if (person.isPresent()) {
//            Person foundedUser = person.get();
//            return foundedUser;
//        } else {
            throw new RuntimeException("emailAddress not exist!");
//        }
    }
    public Person update(Person person) {
        return personDao.save(person);
    }

    public Person changePassword(Person person, String currentPassword, String newPassword) {
        String password = person.getPassword();
        if (password.equals(currentPassword)) {
            person.setPassword(newPassword);
            System.out.println("your password change successfully.");
            return update(person);
        } else {
            throw new RuntimeException("password is wrong!");
        }
    }



}
