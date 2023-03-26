package com.phuc.routeschedulingnote.repository;

import com.phuc.routeschedulingnote.model.Place;
import com.phuc.routeschedulingnote.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Integer> {
    List<Place> findAllByUser(User user);
}
