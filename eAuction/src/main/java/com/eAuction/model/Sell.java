package com.eAuction.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;

import lombok.Data;

@Entity
@Data
public class Sell {
	
	 @Id
	    @GeneratedValue
	    private long sellerId;
	    @Size(min = 5, max = 30, message
	            = "Seller first name should be min 5 and max 30 characters ")
	    @NotNull
	    private String firstName;
	    @Size(min = 5, max = 25, message
	            = "Seller last name should be min 3 and max 25 characters ")
	    @NotNull
	    private String lastName;
	    private String address;
	    private String city;
	    private String state;
	    private int pin;
	    @Pattern(regexp="(^$|[0-9]{10})")
	    @NotNull
	    private String phone;
	    @Email
	    @NotNull
	    private String email;

}
