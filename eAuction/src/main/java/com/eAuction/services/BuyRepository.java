package com.eAuction.services;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.eAuction.model.Buy;

@Service
public interface BuyRepository extends JpaRepository<Buy, Integer> {
	
	public List<Buy> findByProductId(Integer productId);

    @Query("SELECT e from Buy e where e.email =:email AND e.productId =:productId")
    Buy findByEmailAndProduct(@Param("email") String email,@Param("productId")Integer productId);
}
