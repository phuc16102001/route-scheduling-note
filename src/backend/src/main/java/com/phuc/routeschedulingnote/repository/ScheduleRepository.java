package com.phuc.routeschedulingnote.repository;

import com.phuc.routeschedulingnote.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
}
