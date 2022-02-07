package net.guides.springboot2.crud.service;

import lombok.RequiredArgsConstructor;
import net.guides.springboot2.crud.dto.OrderDto;
import net.guides.springboot2.crud.exception.ResourceNotFoundException;
import net.guides.springboot2.crud.model.Order;
import net.guides.springboot2.crud.repository.OrderDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {
    @Autowired
    private  OrderDao orderDao;
    private  ModelMapper modelMapper;

    public void save(OrderDto orderDto) {
        Order order = modelMapper.map(orderDto, Order.class);
        orderDao.save(order);
    }

    public OrderDto findById(Integer id) throws ResourceNotFoundException {
        Optional<Order> optionalOrder = orderDao.findById(id);
        Order order = optionalOrder.orElseThrow(() -> new ResourceNotFoundException("this order not exist!"));
        return modelMapper.map(order,OrderDto.class);
    }

}
