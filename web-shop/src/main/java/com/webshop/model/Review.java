package com.webshop.model;
import jakarta.persistence.*;

import javax.swing.plaf.nimbus.State;
import java.util.Date;
/**
 * Review
 */
@Entity
public class Review {
    private Integer score;
    private String comment;
    private Date reviewDate;
    private User user;
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
