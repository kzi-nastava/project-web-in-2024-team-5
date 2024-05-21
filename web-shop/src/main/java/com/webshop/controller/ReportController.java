package com.webshop.controller;

import com.webshop.dto.ReportDto;
import com.webshop.model.Product;
import com.webshop.repository.UserRepository;
import com.webshop.service.*;
import com.webshop.session.UserSession;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportServiceImpl;

    @PostMapping("/{id}")
    public ResponseEntity<String> reportUser(HttpSession session, @PathVariable Long id, @RequestBody Map<String, String> reason) {
        UserSession userSession = (UserSession) session.getAttribute("User");
        if(userSession == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long userId = userSession.getId();
        if(!reason.containsKey("reason")) {
            return new ResponseEntity<>("You must provide a reason for report", HttpStatus.BAD_REQUEST);
        }
        boolean success = reportServiceImpl.reportUser(userId, id, String.valueOf(reason.get("reason")));
        if(success) {
            return new ResponseEntity<>("Report successfully submitted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Report failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/resolve/{id}")
    public ResponseEntity<String> resolveReport(HttpSession session, @PathVariable Long id, @RequestBody Map<String, String> resolution) {
        UserSession userSession = (UserSession) session.getAttribute("User");
        if(userSession == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if(!userSession.getRole().equals("admin")) {
            return new ResponseEntity<>("You're not an admin.", HttpStatus.UNAUTHORIZED);
        }
        if(!resolution.containsKey("resolution")) {
            return new ResponseEntity<>("You must provide a resolution for report", HttpStatus.BAD_REQUEST);
        }
        boolean success = reportServiceImpl.resolveReport(id, resolution);
        if(success) {
            return new ResponseEntity<>("Report successfully resolved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong with resolving report.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
