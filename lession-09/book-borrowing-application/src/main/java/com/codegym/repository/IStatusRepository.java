package com.codegym.repository;

import com.codegym.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStatusRepository extends JpaRepository<Status, Integer> {
    Status findByStatusName(String statusName);
}
