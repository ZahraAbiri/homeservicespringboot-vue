package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.MainService;

import java.util.List;

public interface MainServiceService {

    void save(MainService mainServiceDto) throws ResourceNotFoundException;

    MainService findByName(String name) throws ResourceNotFoundException;

    List<MainService> findAll();
}
