package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.dto.Expertdto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.SubService;

import java.util.List;

public interface ExpertService {

    public void save(Expertdto expertDto) throws ResourceNotFoundException;

    public Expertdto findByEmailAddress(String emailAddress) throws ResourceNotFoundException;

//    public void update(Expertdto expertDto);
    boolean update(Expertdto expertDto);

    public List<SubService> findServicesByEmail(Expertdto expertDto);

    public void updateScore(Expertdto expertDto, Double instructionsScore);

    public Expertdto findById(Integer id) throws ResourceNotFoundException;

    Expertdto findByEmailAddressAndPassword(String email, String password) throws ResourceNotFoundException;
}
