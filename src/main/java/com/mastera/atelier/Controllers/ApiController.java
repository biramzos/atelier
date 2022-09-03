package com.mastera.atelier.Controllers;

import com.mastera.atelier.DTO.*;
import com.mastera.atelier.Models.Order;
import com.mastera.atelier.Models.User;
import com.mastera.atelier.Services.OrderService;
import com.mastera.atelier.Services.UserService;
import com.mastera.atelier.Token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ApiController {
    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    Token generator = new Token();

    @CrossOrigin
    @PostMapping(value = "/login",produces = "application/json")
    public Map<String,String> getCurrentUser(@RequestBody UserRequest request){
        User user = userService.getUserByUsername(request.getUsername());
        if(user != null){
            if(user.getPassword().equals(request.getPassword())){
                return new HashMap<String,String>(){{
                    put("fullname",user.getFirstname() + " " + user.getLastname());
                    put("username",user.getUsername());
                    put("phone",user.getPhone());
                    put("image","https://ateliermastera.herokuapp.com/images/" + user.getUsername());
                    put("password",user.getPassword());
                    put("token",generator.tokenByUsername(user.getUsername()));
                    put("message","success");
                }};
            }
            return new HashMap<String, String>(){{
                put("message","Password is incorrect");
            }};
        }
        return new HashMap<String, String>(){{
            put("message","User does not found");
        }};
    }

    @CrossOrigin
    @GetMapping(value = "/orders",produces = "application/json")
    public List<OrderResponse> getOrders(){
        List<OrderResponse> orderResponses = new ArrayList<>();
        for(Order order:orderService.getAll()){
            UserResponse user = new UserResponse(order.getUsername(), userService.getFullname(order.getUsername()));
            orderResponses.add(new OrderResponse(order.getId(),order.getName(),order.getPhone(),user));
        }
        return orderResponses;
    }

    @CrossOrigin
    @GetMapping(value = "/orders/{username}",produces = "application/json")
    public List<MyOrderResponse> getOrdersByUsername(@PathVariable("username") String username){
        List<MyOrderResponse> myOrderResponses = new ArrayList<>();
        for(Order order:orderService.getOrdersByUsername(username)){
            myOrderResponses.add(new MyOrderResponse(order.getId(),order.getName(), order.getPhone(), userService.getFullname(username)));
        }
        return myOrderResponses;
    }

    @CrossOrigin
    @PostMapping(value = "/update",produces = "application/json")
    public String UpdatePost(@RequestBody UpdateRequest updateRequest){
        User user = userService.getUserByUsername(updateRequest.getUsername());
        if(user != null){
            if(user.getPassword().equals(updateRequest.getOldPassword())){
                userService.update(user,updateRequest.getNewPassword());
                return "success";
            }
            return "password";
        }
        return "user";
    }

    @CrossOrigin
    @PostMapping(value = "/add/{id}",produces = "application/json")
    public String addOrder(@PathVariable("id") Long id, @RequestParam("username") String username){
        orderService.changeUsername(id, username);
        return "success";
    }

    @CrossOrigin
    @PostMapping(value = "/delete/{id}",produces = "application/json")
    public String deleteOrder(@PathVariable("id") Long id){
        orderService.changeUsername(id, "None");
        return "success";
    }

    @CrossOrigin
    @PostMapping(value = "/finish/{id}", produces = "application/json")
    public String finishOrder(@PathVariable("id") Long id){
        orderService.delete(id);
        return "success";
    }
}
