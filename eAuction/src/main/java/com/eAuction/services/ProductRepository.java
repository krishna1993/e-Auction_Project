package com.eAuction.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.eAuction.model.Product;

@Service
public interface ProductRepository extends CrudRepository<Product, Integer>{
}
