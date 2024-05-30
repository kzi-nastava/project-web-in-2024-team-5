package com.webshop.service;

import com.webshop.dto.OfferDto;

/**
 * OfferService
 */
public interface OfferService {

    boolean createOffer(Long buyerId, OfferDto offerDto);
}
