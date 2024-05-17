package com.webshop.repository;
import com.webshop.model.Buyer;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
@Registered
public interface BuyerRepository extends JpaRepository<Buyer, Long> {

}
