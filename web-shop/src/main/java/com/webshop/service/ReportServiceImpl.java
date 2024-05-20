package com.webshop.service;

import com.webshop.model.*;
import com.webshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private BuyerRepository buyerRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean reportUser(Long whoReportedId, Long whoIsReportedId, String reason) {
        Optional<User> optWhoReported = userRepository.findById(whoReportedId);
        Optional<User> optWhoIsReported = userRepository.findById(whoIsReportedId);
        if(optWhoIsReported.isEmpty() ||optWhoReported.isEmpty()) {
            return false;
        }
        User whoReported = optWhoReported.get();
        User whoIsReported = optWhoIsReported.get();
        if(whoReported.getUserRole().equals("buyer") && whoIsReported.getUserRole().equals("seller")) {
            return reportSeller(whoReportedId, whoIsReportedId, reason);
        }
        if(whoReported.getUserRole().equals("seller") && whoIsReported.getUserRole().equals("buyer")) {
            return reportBuyer(whoIsReportedId, whoReportedId, reason);
        }

        return false;
    }
    @Override
    public boolean resolveReport(Long reportId, String resolution) {
        if(resolution.equalsIgnoreCase("accepted")) {
           return acceptReport(reportId);
        }
        if(resolution.equalsIgnoreCase("rejected")) {
            return rejectReport(reportId);
        }
        return false;
    }
    private boolean acceptReport(Long reportId) {
        Optional<Report> optReport = reportRepository.findById(reportId);
        if(optReport.isEmpty()) {
            return false;
        }
        Report report = optReport.get();
        report.setStatus(ReportStatus.ACCEPTED);
        reportRepository.save(report);
        return true;
    }

    private boolean rejectReport(Long reportId) {
        Optional<Report> optReport = reportRepository.findById(reportId);
        if(optReport.isEmpty()) {
            return false;
        }
        Report report = optReport.get();
        report.setStatus(ReportStatus.ACCEPTED);
        reportRepository.save(report);
        return true;
    }

    private boolean reportSeller(Long buyerId, Long sellerId, String reason) {
        Optional<Buyer> optBuyer = buyerRepository.findById(buyerId);
        if(optBuyer.isPresent()) {
            List<Product> products = productRepository.findAllBySellerIdAndBuyerId(sellerId, buyerId);
            for(Product product : products) {
                if(product.isSold()) {
                    Buyer buyer = optBuyer.get();
                    Seller s = sellerRepository.findById(sellerId).get();
                    Report report = new Report();
                    report.setWhoReported(buyer);
                    report.setWhoIsReported(s);
                    report.setReason(reason);
                    report.setStatus(ReportStatus.SUBMITTED);
                    report.setDatePosted(LocalDateTime.now());
                    reportRepository.save(report);
                    return true;
                }
            }
        }
        return false;
    }
    private boolean reportBuyer(Long buyerId, Long sellerId, String reason) {
        Optional<Seller> optSeller = sellerRepository.findById(sellerId);
        if(optSeller.isPresent()) {
            List<Product> products = productRepository.findAllBySellerIdAndBuyerId(sellerId, buyerId);
            for(Product product : products) {
                if(product.isSold()) {
                    Seller seller = optSeller.get();
                    Buyer b = buyerRepository.findById(buyerId).get();
                    Report report = new Report();
                    report.setWhoReported(seller);
                    report.setWhoIsReported(b);
                    report.setReason(reason);
                    report.setStatus(ReportStatus.SUBMITTED);
                    report.setDatePosted(LocalDateTime.now());
                    reportRepository.save(report);
                    return true;
                }
            }
        }
        return false;
    }
}
