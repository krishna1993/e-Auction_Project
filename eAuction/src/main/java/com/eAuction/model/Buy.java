package com.eAuction.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
public class Buy {
	
	@Id
    @GeneratedValue
    private int buyerId;
    @NotNull(message = "Name cannot be null")
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String pin;
    private String phone;
    @Email @NotNull
    private String email;
    private Integer productId;
    private String bidAmount;
    
    
}
