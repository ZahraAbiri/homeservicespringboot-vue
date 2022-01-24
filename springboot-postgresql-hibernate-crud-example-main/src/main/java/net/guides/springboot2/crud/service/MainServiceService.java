package net.guides.springboot2.crud.service;


import lombok.Getter;
import lombok.Setter;
import net.guides.springboot2.crud.model.MainService;
import net.guides.springboot2.crud.repository.MainServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Getter
@Setter
public class MainServiceService {
    @Autowired
    private MainServiceDao mainServiceDao;

   public MainService saveMainService(MainService mainService){
       return mainServiceDao.save(mainService);
   }
    public void deleteMainService(MainService mainservice) {
         mainServiceDao.delete( mainservice);
    }
    public MainService findByName(String name) {
       return mainServiceDao.findByName(name);
    }
    public List<MainService> findAll() {
       return mainServiceDao.findAll();
    }


}
