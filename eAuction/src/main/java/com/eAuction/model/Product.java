package com.eAuction.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

import java.util.*;
@Data
@Entity
public class Product {
	
	 @Id
	    @GeneratedValue
	    private int  productId;
	    private Long sellerId;
	    @Size(min = 5, max = 30, message
	            = "Product name should be min 5 and max 30 character")
	    @NotNull
	    private String productName;

	    private String shortDescription;

	    private String detailedDescription;
	    @Pattern(regexp = "Painting|Scultor|Ornament", flags = Pattern.Flag.CASE_INSENSITIVE)
	    private String category;
	    private float startingPrice;
	    @Future
	    private Date bidEndDate;
}
