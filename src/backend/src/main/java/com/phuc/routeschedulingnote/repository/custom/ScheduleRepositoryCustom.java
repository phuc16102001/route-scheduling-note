package com.phuc.routeschedulingnote.repository.custom;

import com.phuc.routeschedulingnote.model.Schedule;

import java.util.Optional;

public interface ScheduleRepositoryCustom {

    Optional<Schedule> findByIdSortStopOrder(Integer id);

}
