package com.webshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webshop.dto.ReportDto;
import com.webshop.model.Report;
import com.webshop.service.ReportServiceImpl;
import com.webshop.session.UserSession;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/reports")
public class ReportController {

    @Autowired
    private ReportServiceImpl reportServiceImpl;

    @PostMapping("/{id}")
    public ResponseEntity<String> reportUser(HttpSession session, @PathVariable Long id,
            @RequestBody Map<String, String> reason) {
        UserSession userSession = (UserSession) session.getAttribute("User");
        if (userSession == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        Long userId = userSession.getId();
        if (!reason.containsKey("reason") || reason.get("reason").isEmpty()) {
            return new ResponseEntity<>("You must provide a reason for report", HttpStatus.BAD_REQUEST);
        }
        boolean success = reportServiceImpl.reportUser(userId, id, String.valueOf(reason.get("reason")));
        if (success) {
            return new ResponseEntity<>("Report successfully submitted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Report failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/resolve/{id}")
    public ResponseEntity<String> resolveReport(HttpSession session, @PathVariable Long id,
            @RequestBody Map<String, String> resolution) {
        UserSession userSession = (UserSession) session.getAttribute("User");
        if (userSession == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        if (!userSession.getRole().equals("admin")) {
            return new ResponseEntity<>("You're not an admin.", HttpStatus.UNAUTHORIZED);
        }
        if (!resolution.containsKey("resolution") || resolution.get("resolution").isEmpty()) {
            return new ResponseEntity<>("You must provide a resolution for report", HttpStatus.BAD_REQUEST);
        }
        if (resolution.get("resolution").equals("rejected") && !resolution.containsKey("reason")
                || resolution.get("reason").isEmpty()) {
            return new ResponseEntity<>("You must provide a reason for rejecting report", HttpStatus.BAD_REQUEST);
        }
        boolean success = reportServiceImpl.resolveReport(id, resolution);
        if (success) {
            return new ResponseEntity<>("Report successfully resolved", HttpStatus.OK);
        }
        return new ResponseEntity<>("Something went wrong with resolving report.", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping
    public ResponseEntity<?> getReports(HttpSession session) {
        UserSession uSession = (UserSession) session.getAttribute("User");
        if (uSession == null || !uSession.getRole().equals("admin")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Report> reports = reportServiceImpl.findAll();
        List<ReportDto> rDtos = new ArrayList<>();
        for (Report report : reports) {
            rDtos.add(new ReportDto(report));
        }

        return ResponseEntity.ok(rDtos);
    }

}
