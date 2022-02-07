package net.guides.springboot2.crud.service;


import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.dto.Expertdto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Expert;
import net.guides.springboot2.crud.model.SubService;
import net.guides.springboot2.crud.repository.ExpertDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpertServiceImpl implements ExpertService {
    @Autowired
    private ExpertDao expertDao;

    private ModelMapper modelMapper;
    @Override
    public void save(Expertdto expertDto) throws ResourceNotFoundException {
        Optional<Expert> foundedExpert = expertDao.findByEmailAddress(expertDto.getEmailAddress());
        if (foundedExpert.isPresent()) {
            throw new ResourceNotFoundException("dont have expert");
        } else {
            Expert expert=new Expert();
            expert.setPhoto(expertDto.getPhoto());
            expert.setFirstname(expertDto.getFirstname());
            expert.setLastname(expertDto.getLastname());
            expert.setEmailAddress(expertDto.getEmailAddress());
            expert.setLastname(expertDto.getLastname());
            expert.setLastname(expertDto.getLastname());
            expert.setLastname(expertDto.getLastname());
            expertDao.save(expert);
        }
    }

    @Override
    public Expertdto findByEmailAddress(String emailAddress) throws ResourceNotFoundException {
        Optional<Expert> optionalExpert = expertDao.findByEmailAddress(emailAddress);
        Expert expert = optionalExpert.orElseThrow(() -> new ResourceNotFoundException("not fount expert"));
        return modelMapper.map(expert, Expertdto.class);
    }

    @Override
    public boolean update(Expertdto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        expertDao.save(expert);
        return false;
    }

    @Override
    public List<SubService> findServicesByEmail(Expertdto expertDto) {
        Expert expert = modelMapper.map(expertDto, Expert.class);
        List<SubService> services = expert.getServices();
        return services.stream()
                .map(subService -> modelMapper.map(subService, SubService.class)).collect(Collectors.toList());
    }

    @Override
    public void updateScore(Expertdto expertDto, Double instructionsScore) {
        Double expertScore = expertDto.getScore();
        Double newScore = (expertScore + instructionsScore) / 2;
        expertDto.setScore(newScore);
        update(expertDto);
    }

    @Override
    public Expertdto findById(Integer id) throws ResourceNotFoundException {
        Optional<Expert> optionalExpert = expertDao.findById(id);
        Expert expert = optionalExpert.orElseThrow(() -> new ResourceNotFoundException("expert not exist!"));
        return modelMapper.map(expert, Expertdto.class);
    }

    @Override
    public Expertdto findByEmailAddressAndPassword(String email, String password) throws ResourceNotFoundException {
        Optional<Expert> optionalExpert = expertDao.findByEmailAddressAndPassword(email, password);
        Expert expert = optionalExpert.orElseThrow(() -> new ResourceNotFoundException("not found"));
        return modelMapper.map(expert, Expertdto.class);
    }
}
