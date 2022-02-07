package net.guides.springboot2.crud.service;

import net.guides.springboot2.crud.dto.OrderDto;

public interface OrderService {

    public void save(OrderDto orderDto);

    public OrderDto findById(Integer id);

    void updateStatus(OrderDto doneOrder);
}
