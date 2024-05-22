package com.webshop.service;

import com.webshop.dto.OfferDto;
import com.webshop.model.Product;

/**
 * OfferService
 */
public interface OfferService {

    boolean createOffer(Long buyerId, OfferDto offerDto);
}
