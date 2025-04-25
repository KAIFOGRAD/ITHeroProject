package com.ithero.geomap.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceResponseDTO {
    private Long id;
    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private String category;
    private String createdByUsername;
}