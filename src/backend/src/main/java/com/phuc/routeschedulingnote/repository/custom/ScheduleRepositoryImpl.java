package com.phuc.routeschedulingnote.repository.custom;

import com.phuc.routeschedulingnote.model.QSchedule;
import com.phuc.routeschedulingnote.model.QStop;
import com.phuc.routeschedulingnote.model.Schedule;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public class ScheduleRepositoryImpl implements ScheduleRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Optional<Schedule> findByIdSortStopOrder(Integer id) {
        return Optional.ofNullable(
                    new JPAQuery<Schedule>(em)
                    .from(QSchedule.schedule)
                    .leftJoin(QSchedule.schedule.stops, QStop.stop)
                    .fetchJoin()
                    .where(QSchedule.schedule.id.eq(id))
                    .orderBy(QStop.stop.stopOrder.asc())
                    .fetchFirst());
    }

}
