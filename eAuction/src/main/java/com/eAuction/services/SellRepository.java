package com.eAuction.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.eAuction.model.Sell;

@Service
public interface SellRepository extends JpaRepository<Sell, Long>{
}
