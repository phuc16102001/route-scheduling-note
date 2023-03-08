package com.phuc.routeschedulingnote.repository;

import com.phuc.routeschedulingnote.model.PlaceNote;
import com.phuc.routeschedulingnote.model.PlaceScheduleKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceNoteRepository extends JpaRepository<PlaceNote, PlaceScheduleKey> {
}
