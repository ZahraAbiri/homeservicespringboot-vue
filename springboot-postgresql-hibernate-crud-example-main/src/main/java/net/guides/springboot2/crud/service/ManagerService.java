package net.guides.springboot2.crud.service;


import lombok.Getter;
import lombok.Setter;
import net.guides.springboot2.crud.model.Manager;
import net.guides.springboot2.crud.repository.ManagerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Getter
@Setter
@Service
public class ManagerService {
    @Autowired
    private ManagerDao managerDao;

    public void save(Manager manager) {
        managerDao.save(manager);
    }

    public Boolean isManagerExist(String username) {
        Boolean exist = false;
        Optional<Manager> manager = managerDao.findByUsername(username);
        if (manager.isPresent()) {
            exist = true;
        } else {
            throw new RuntimeException("username not exist!");
        }
        return exist;
    }

    public Manager findByUsername(String username) {
        Optional<Manager> manager = managerDao.findByUsername(username);
        if (manager.isPresent()) {
            Manager foundedManager = manager.get();
            return foundedManager;
        } else {
            throw new RuntimeException("username not exist!");
        }
    }
}
