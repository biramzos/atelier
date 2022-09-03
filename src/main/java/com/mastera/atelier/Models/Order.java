package com.mastera.atelier.Models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column
    private String name;
    @Column
    private String phone;
    @Column
    private String username;
    @Column
    private String date;

    public Order(){}

    public Order(String name,String phone, String username, String date){
        this.name = name;
        this.phone = phone;
        this.username = username;
        this.date = date;
    }
}
