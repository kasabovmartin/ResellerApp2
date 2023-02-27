package com.resellerapp.repository;


import com.resellerapp.model.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

    Set<Offer> findBySellerId(Long userId);

    Set<Offer> findBySellerIdNot(Long userId);

    Set<Offer> findByBuyerId(Long userId);

    @Modifying
    @Query("update Offer offer set offer.buyer.id = :buyerId where offer.id = :id")
    void updateBuyer(@Param(value = "id") Long id, @Param(value = "buyerId") Long buyerId);
}
