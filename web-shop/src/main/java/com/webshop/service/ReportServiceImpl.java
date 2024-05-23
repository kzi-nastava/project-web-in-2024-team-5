package com.webshop.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webshop.model.Buyer;
import com.webshop.model.Product;
import com.webshop.model.Report;
import com.webshop.model.ReportStatus;
import com.webshop.model.Seller;
import com.webshop.model.User;
import com.webshop.repository.BuyerRepository;
import com.webshop.repository.ProductRepository;
import com.webshop.repository.ReportRepository;
import com.webshop.repository.SellerRepository;
import com.webshop.repository.UserRepository;

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
    @Autowired
    private EmailService emailService;

    @Override
    public boolean reportUser(Long whoReportedId, Long whoIsReportedId, String reason) {
        Optional<User> optWhoReported = Optional.ofNullable(userRepository.findById(whoReportedId));
        Optional<User> optWhoIsReported = Optional.ofNullable(userRepository.findById(whoIsReportedId));
        if (optWhoIsReported.isEmpty() || optWhoReported.isEmpty()) {
            return false;
        }
        User whoReported = optWhoReported.get();
        User whoIsReported = optWhoIsReported.get();
        if (whoReported.getUserRole().equals("buyer") && whoIsReported.getUserRole().equals("seller")) {
            return reportSeller(whoReportedId, whoIsReportedId, reason);
        }
        if (whoReported.getUserRole().equals("seller") && whoIsReported.getUserRole().equals("buyer")) {
            return reportBuyer(whoIsReportedId, whoReportedId, reason);
        }

        return false;
    }

    @Override
    public boolean resolveReport(Long reportId, Map<String, String> resolution) {
        if (resolution.get("resolution").equals("accepted")) {
            return acceptReport(reportId);
        }
        if (resolution.get("resolution").equals("rejected") && !resolution.get("reason").isEmpty()) {
            return rejectReport(reportId, resolution.get("reason"));
        }
        return false;
    }

    @Override
    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    private boolean acceptReport(Long reportId) {
        Optional<Report> optReport = reportRepository.findById(reportId);
        if (optReport.isEmpty()) {
            return false;
        }
        Report report = optReport.get();
        if (report.getStatus() != ReportStatus.SUBMITTED) {
            return false;
        }
        report.setStatus(ReportStatus.ACCEPTED);
        report.getWhoIsReported().setBlocked(Boolean.TRUE);
        emailService.sendEmail(report.getWhoReported().getEmail(), "Report accepted",
                "Your report has been reviewed, and we decided to accept your report.");
        reportRepository.save(report);
        return true;
    }

    private boolean rejectReport(Long reportId, String reason) {
        Optional<Report> optReport = reportRepository.findById(reportId);
        if (optReport.isEmpty()) {
            return false;
        }
        Report report = optReport.get();
        if (report.getStatus() != ReportStatus.SUBMITTED) {
            return false;
        }
        report.setStatus(ReportStatus.REJECTED);
        emailService.sendEmail(report.getWhoReported().getEmail(), "Report rejected",
                "Your report has been reviewed, and we decided to reject your offer for this reason: " + reason);
        reportRepository.save(report);
        return true;
    }

    private boolean reportSeller(Long buyerId, Long sellerId, String reason) {
        Optional<Buyer> optBuyer = buyerRepository.findById(buyerId);
        Seller seller = sellerRepository.findById(sellerId).get();
        if (optBuyer.isPresent()) {
            List<Product> products = productRepository.findAllBySellerAndBuyer(seller, optBuyer.get());
            for (Product product : products) {
                if (product.isSold()) {
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
        Buyer buyer = buyerRepository.findById(buyerId).get();
        if (optSeller.isPresent()) {
            List<Product> products = productRepository.findAllBySellerAndBuyer(optSeller.get(), buyer);
            for (Product product : products) {
                if (product.isSold()) {
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
