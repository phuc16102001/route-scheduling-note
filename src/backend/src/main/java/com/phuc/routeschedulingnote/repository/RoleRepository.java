package com.phuc.routeschedulingnote.repository;

import com.phuc.routeschedulingnote.model.Role;
import com.phuc.routeschedulingnote.model.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByName(RoleEnum roleEnum);
    Boolean existsByName(RoleEnum roleEnum);
}
