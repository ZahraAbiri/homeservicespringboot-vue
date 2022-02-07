package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.model.Manager;

public interface ManagerService {

    void save(Manager manager);

    Manager findByEmailAddressAndPassword(String email, String password);
}
