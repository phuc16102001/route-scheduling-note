package com.phuc.routeschedulingnote.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name="schedule")
@EntityListeners(AuditingEntityListener.class)
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @CreatedDate
    private Instant createAt;

    @LastModifiedDate
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "schedule")
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE})
    private List<PlaceNote> placeNotes;

    @OneToMany(mappedBy = "schedule")
    @Cascade(value = {org.hibernate.annotations.CascadeType.DELETE})
    private List<Stop> stops;

}
