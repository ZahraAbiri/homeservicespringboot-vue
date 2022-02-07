package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.repository.SubserviceDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class SubServiceServiceImpl implements SubServiceService {
    @Autowired
    private SubserviceDao subserviceDao;
    private  MainServiceService mainServiceService;
    private  SubServiceMapper subServiceMapper;
    private  ModelMapper modelMapper;


    public void save(SubService subServiceDto) throws ResourceNotFoundException {
        SubService subService = subServiceMapper.getSubServiceWithOutId(subServiceDto);
        mainServiceService.findByName(subService.getMainService().getName());
        Optional<SubService> foundedSubService = subserviceDao.findByName(subService.getName());
        if (foundedSubService.isPresent()) {
            throw new ResourceNotFoundException("this subService exist!");
        } else {
            subserviceDao.save(subService);
        }
    }


}
