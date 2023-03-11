package com.phuc.routeschedulingnote.repository;

import com.phuc.routeschedulingnote.model.Schedule;
import com.phuc.routeschedulingnote.repository.custom.ScheduleRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer>, ScheduleRepositoryCustom {

}
