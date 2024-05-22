package com.webshop.controller;

import com.webshop.dto.OfferDto;
import com.webshop.model.User;
import com.webshop.service.OfferServiceImpl;
import com.webshop.session.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/offers")
public class OfferController {

    @Autowired
    private OfferServiceImpl offerServiceImpl;

    public OfferController(OfferServiceImpl offerServiceImpl) {
    }

    @PostMapping("")
    public ResponseEntity<String> addOffer(HttpSession session, @Validated @RequestBody OfferDto offer) {
       UserSession loggedUser = (UserSession) session.getAttribute("User");
       if(loggedUser == null || !loggedUser.getRole().equals("buyer")) {
           return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
       }
        boolean success = offerServiceImpl.createOffer(loggedUser.getId(), offer);
        if(success) {
            return new ResponseEntity<>("You have added your offer successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("You have not added your offer successfully", HttpStatus.BAD_REQUEST);
    }
}
