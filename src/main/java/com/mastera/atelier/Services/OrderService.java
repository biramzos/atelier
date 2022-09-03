package com.mastera.atelier.Services;

import com.mastera.atelier.DTO.OrderResponse;
import com.mastera.atelier.Models.Order;
import com.mastera.atelier.Repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public boolean add(String name, String phone){
        if(name.isEmpty() && phone.isEmpty() || (name.isEmpty() && !phone.isEmpty()) || (!name.isEmpty() && phone.isEmpty())){
            return false;
        }
        else {
            LocalDateTime date = LocalDateTime.now(ZoneId.of("UTC+06:00"));
            String currentDate = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Order order = new Order(name, phone, "None", currentDate);
            orderRepository.save(order);
            return true;
        }
    }

    public List<Order> getAll(){
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUsername(String username){
        return orderRepository.findOrdersByUsername(username);
    }

    public void delete(Long id){
        Order order = orderRepository.findOrderById(id);
        orderRepository.delete(order);
    }

    public Order getOrderById(Long id){
        return orderRepository.findOrderById(id);
    }

    public void changeUsername(Long id, String username){
        Order order = orderRepository.findOrderById(id);
        order.setUsername(username);
        orderRepository.save(order);
    }
}
