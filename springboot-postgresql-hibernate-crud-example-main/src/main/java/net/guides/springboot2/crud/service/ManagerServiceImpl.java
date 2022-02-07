package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.model.Manager;
import net.guides.springboot2.crud.repository.ManagerDao;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    private ManagerDao managerDao;
//    private final ModelMapper modelMapper;

    @Override
    public void save(Manager manager) {
        managerDao.save(manager);
    }

    @Override
    public Manager findByEmailAddressAndPassword(String email, String password) {
        Optional<Manager> optionalManager = managerDao.findByEmailAddressAndPassword(email, password);
        Manager manager = optionalManager.orElseThrow(() -> new ResourceNotFoundException("manager not found"));
        return manager;
    }
}
