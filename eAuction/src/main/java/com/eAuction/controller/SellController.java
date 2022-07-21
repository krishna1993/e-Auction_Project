package com.eAuction.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eAuction.model.Sell;
import com.eAuction.services.SellRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/e-auction/api/v1")
public class SellController {
	
	@Autowired
    private SellRepository sellerRepository;

    @GetMapping("/seller/{id}")
    public Optional<Sell> retrieveSellerById(@PathVariable Long id) {
        return sellerRepository.findById(id);
    }

    @GetMapping("/seller")
    public List<Sell> retrieveAllSellers() {
        List<Sell> sellerList=new ArrayList<>();
        sellerList =sellerRepository.findAll();
        return sellerList;
    }

    @PostMapping("/seller")
    public ResponseEntity<String> createUser(@Valid @RequestBody Sell seller) {
        sellerRepository.save(seller);
        return ResponseEntity.ok("Data Saved Successfully");
    }

}
