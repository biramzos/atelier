package com.mastera.atelier.Controllers;

import com.mastera.atelier.DTO.UserRequest;
import com.mastera.atelier.Models.Order;
import com.mastera.atelier.Models.User;
import com.mastera.atelier.Services.OrderService;
import com.mastera.atelier.Services.UserService;
import com.mastera.atelier.Token.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
    public List<Order> getOrders(){
        return orderService.getAll();
    }

    @CrossOrigin
    @GetMapping(value = "/orders/{username}",produces = "application/json")
    public List<Order> getOrdersByUsername(@PathVariable("username") String username){
        return orderService.getOrdersByUsername(username);
    }
}
