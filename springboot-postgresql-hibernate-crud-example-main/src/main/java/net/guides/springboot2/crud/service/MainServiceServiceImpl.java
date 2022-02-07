package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.MainService;
import net.guides.springboot2.crud.repository.MainServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainServiceServiceImpl implements MainServiceService {
    @Autowired
    private MainServiceDao mainServiceDao;

    @Override
    public void save(MainService mainService) throws ResourceNotFoundException {
        Optional<MainService> foundedMainService = Optional.ofNullable(mainServiceDao.findByName(mainService.getName()));
        if (foundedMainService.isPresent()) {
            throw new ResourceNotFoundException("mainservice not found");
        } else {
            mainServiceDao.save(mainService);
        }
    }

    @Override
    public MainService findByName(String name) throws ResourceNotFoundException {
        Optional<MainService> optionalMainService = Optional.ofNullable(mainServiceDao.findByName(name));
        MainService mainService = optionalMainService.orElseThrow(() -> new ResourceNotFoundException("not found"));
        return mainService;
    }

    @Override
    public List<MainService> findAll() {
        List<MainService> mainServices = mainServiceDao.findAll();
        return mainServices;
    }

}
