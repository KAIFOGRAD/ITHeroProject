package com.ithero.geomap.Controller;

import com.ithero.geomap.DTO.PlaceResponseDTO;
import com.ithero.geomap.Entity.Place;
import com.ithero.geomap.Entity.User.User;
import com.ithero.geomap.Repository.PlaceRepository;
import com.ithero.geomap.Repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/places")
public class PlaceApiController {

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public PlaceApiController(PlaceRepository placeRepository, 
                           UserRepository userRepository) {
        this.placeRepository = placeRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<PlaceResponseDTO> createPlace(@RequestBody Place placeRequest) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = authentication.getName();
            
            User currentUser = userRepository.findByLogin(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            Place place = new Place();
            place.setName(placeRequest.getName());
            place.setLatitude(placeRequest.getLatitude());
            place.setLongitude(placeRequest.getLongitude());
            place.setDescription(placeRequest.getDescription());
            place.setCategory(placeRequest.getCategory());
            place.setCreatedBy(currentUser);
            
            Place savedPlace = placeRepository.save(place);
            
            PlaceResponseDTO responseDTO = new PlaceResponseDTO(
                savedPlace.getId(),
                savedPlace.getName(),
                savedPlace.getLatitude(),
                savedPlace.getLongitude(),
                savedPlace.getDescription(),
                savedPlace.getCategory(),
                currentUser.getLogin()
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new PlaceResponseDTO(null, "Error: " + e.getMessage(), 0, 0, null, null, null));
        }
    }

    @GetMapping
    public ResponseEntity<List<PlaceResponseDTO>> getAllPlaces() {
        try {
            List<Place> places = placeRepository.findAll();
            
            List<PlaceResponseDTO> responseDTOs = places.stream()
                .map(place -> new PlaceResponseDTO(
                    place.getId(),
                    place.getName(),
                    place.getLatitude(),
                    place.getLongitude(),
                    place.getDescription(),
                    place.getCategory(),
                    place.getCreatedBy() != null ? place.getCreatedBy().getLogin() : null
                ))
                .collect(Collectors.toList());
            
            return ResponseEntity.ok(responseDTOs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}