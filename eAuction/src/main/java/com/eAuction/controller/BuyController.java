package com.eAuction.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eAuction.exception.NotFoundException;
import com.eAuction.model.Buy;
import com.eAuction.model.Product;
import com.eAuction.services.BuyRepository;
import com.eAuction.services.ProductRepository;

@RestController
@RequestMapping("/e-auction/api/v1")
public class BuyController {

	@Autowired
	BuyRepository buyRepository;

	@Autowired
	ProductRepository productRepository;

	@PostMapping("/buyer/place-bid")
	public ResponseEntity<String> createUser(@Valid @RequestBody Buy buyer) {
		List<Buy> buyerList = buyRepository.findAll();
		if (buyerList.stream().anyMatch(n -> n.getEmail().equals(buyer.getEmail()))) {
			throw new ValidationException("cannot bid the product twice using the same emailId!");
		}

		Optional<Product> product = productRepository.findById(buyer.getProductId());
		Product pro = product.get();
		pro.getBidEndDate();
		Date date = new Date();
		if (pro.getBidEndDate().compareTo((date)) < 0) {
			throw new ValidationException("You cannot bid the product after the bid enddate");
		}
		buyRepository.save(buyer);
		return ResponseEntity.ok("Buyer data saved successfully!!!!!");
	}

	@GetMapping("/buyer/allbids")
	public List<Buy> retrieveAllBids() {
		return buyRepository.findAll();
	}

	@PutMapping("/buyer/update-bid/{productId}/{buyerEmailId}/{newBidAmount}")
	public String createUsers(@PathVariable Integer productId, @PathVariable String buyerEmailId,
			@PathVariable String newBidAmount) {
		Buy buyer = buyRepository.findByEmailAndProduct(buyerEmailId, productId);
		if (buyer != null) {
			buyer.setProductId(productId);
			buyer.setEmail(buyerEmailId);
			buyer.setBidAmount(newBidAmount);
			buyRepository.save(buyer);

		}else {
			throw new NotFoundException(
					"Either productId " + productId + " or buyerEmailId " + buyerEmailId + " is not found");
		}
		
		return "data added successfully";
	}
}
