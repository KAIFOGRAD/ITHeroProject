package com.ithero.geomap.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ithero.geomap.Entity.Place;
import com.ithero.geomap.Entity.User.User;


@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    List<Place> findByCreatedBy(User user);
}
