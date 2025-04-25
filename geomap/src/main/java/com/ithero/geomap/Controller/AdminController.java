package com.ithero.geomap.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ithero.geomap.Entity.Place;
import com.ithero.geomap.Entity.User.User;
import com.ithero.geomap.Repository.PlaceRepository;
import com.ithero.geomap.Repository.UserRepository;

@RestController
@RequestMapping("/api/administrator")
public class AdminController {

    private final PlaceRepository placeRepository;
    private final UserRepository userRepository;

    public AdminController(PlaceRepository placeRepository, UserRepository userRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/places")
    public ResponseEntity<List<Place>> getAllPlaces() {
        List<Place> places = placeRepository.findAll();
        return ResponseEntity.ok(places);
    }

    @GetMapping("/places/user/{userId}")
    public ResponseEntity<List<Place>> getPlacesByUserId(@PathVariable Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        List<Place> places = placeRepository.findByCreatedBy(user);
        return ResponseEntity.ok(places);
    }

    @DeleteMapping("/places/{placeId}")
    public ResponseEntity<Void> deletePlace(@PathVariable Long placeId) {
        if (!placeRepository.existsById(placeId)) {
            throw new RuntimeException("Place not found with id: " + placeId);
        }

        placeRepository.deleteById(placeId);
        return ResponseEntity.noContent().build();
    }

}