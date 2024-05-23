package com.webshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.dto.OfferDto;
import com.webshop.model.Buyer;
import com.webshop.model.Offer;
import com.webshop.model.Product;
import com.webshop.model.TypeOfSale;
import com.webshop.repository.BuyerRepository;
import com.webshop.repository.OfferRepository;
import com.webshop.repository.ProductRepository;

/**
 * OfferServiceImpl
 */
@Service
public class OfferServiceImpl implements OfferService {

    @Autowired
    private OfferRepository offerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BuyerRepository buyerRepository;

    @Override
    public boolean createOffer(Long buyerId, OfferDto offerDto) {
        Product product = productRepository.findById(offerDto.getProductId());
        if (product.getTypeOfSale().equals(TypeOfSale.FIXED_PRICE)) {
            return false;
        }
        if (product.isSold()) {
            return false;
        }
        if (offerDto.getPrice().compareTo(product.getPrice()) < 0) {
            return false;
        }
        Buyer buyer = buyerRepository.findById(buyerId).get();
        product.setPrice(offerDto.getPrice());
        product.setBuyerId(buyer);
        Offer offer = new Offer();

        offer.setOfferAmountMoney(offerDto.getPrice());
        if (buyerRepository.findById(buyerId).isPresent()) {
            offer.setBuyer(buyerRepository.findById(buyerId).get());
        }
        product.getOffers().add(offer);
        offerRepository.save(offer);
        return true;
    }
}
