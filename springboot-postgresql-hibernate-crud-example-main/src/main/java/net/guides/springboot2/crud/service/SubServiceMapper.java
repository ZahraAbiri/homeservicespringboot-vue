package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.model.SubService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubServiceMapper {

    public SubService getSubServiceWithOutId(SubService subServiceDto) {
        return subServiceDto;
    }
}
