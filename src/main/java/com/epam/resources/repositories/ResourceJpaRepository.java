package com.epam.resources.repositories;

import com.epam.resources.entities.Resource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceJpaRepository extends JpaRepository<Resource, Long> {
}
