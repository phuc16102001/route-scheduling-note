package com.phuc.routeschedulingnote.repository;

import com.phuc.routeschedulingnote.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
}
