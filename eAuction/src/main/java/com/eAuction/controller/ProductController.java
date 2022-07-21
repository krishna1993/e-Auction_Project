package com.eAuction.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private BuyRepository buyerRepository;

	@PostMapping("/seller/add-product")
	public ResponseEntity<String> retrieveSellerById(@Valid @RequestBody Product product) {
		System.out.println("i m in add product");
		productRepository.save(product);
		return ResponseEntity.ok("Data Saved Successfully");
	}

	@GetMapping("/seller/products")
	public List<Product> retrieveProducts() {
		return (List<Product>) productRepository.findAll();
	}

	@GetMapping("/seller/show-bids/{productId}")
	public Optional<Product> retrieveProductsById(@PathVariable int productId) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isEmpty()) {
			throw new NotFoundException("productId " + productId + " is not found");
		}
		return productRepository.findById(productId);
	}

	@DeleteMapping("/seller/delete/{productId}")
	public String removeProductsById(@PathVariable int productId) throws ValidationException {
		Optional<Product> productList = productRepository.findById(productId);
		if (productList.isEmpty()) {
			throw new NotFoundException("productId " + productId + " is not found");
		}
		Product p = productList.get();
		Date date = new Date();
		if (p.getBidEndDate().compareTo((date)) < 0) {
			System.out.println("the date is after");
			throw new ValidationException("You cannot delete the product after the bid enddate");
		}
		List<Buy> buyerList = buyerRepository.findByProductId(productId);
		if (buyerList.stream().anyMatch(n -> n.getProductId().equals(productId))) {
			System.out.println("the date is after");
			throw new ValidationException("You cannot delete due to some reason");
		}
		return "Data Deleted";
	}
}
