package com.phuc.routeschedulingnote.repository;

import com.phuc.routeschedulingnote.model.Stop;
import com.phuc.routeschedulingnote.model.StopKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends JpaRepository<Stop, StopKey> {
}
