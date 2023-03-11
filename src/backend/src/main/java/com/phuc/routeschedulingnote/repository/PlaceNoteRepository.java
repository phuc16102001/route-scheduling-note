package com.phuc.routeschedulingnote.repository;

import com.phuc.routeschedulingnote.model.PlaceNote;
import com.phuc.routeschedulingnote.model.PlaceScheduleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaceNoteRepository extends JpaRepository<PlaceNote, PlaceScheduleKey> {
}
