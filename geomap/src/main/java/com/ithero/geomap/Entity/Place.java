package com.ithero.geomap.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.ithero.geomap.Entity.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String category;
    private Double rating;
    private Double latitude;
    private Double longitude;
    private String address;
    private String workingHours;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User createdBy;
    
    @CreationTimestamp
    private LocalDateTime createdAt;
}