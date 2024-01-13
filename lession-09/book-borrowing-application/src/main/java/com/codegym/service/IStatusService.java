package com.codegym.service;

import com.codegym.model.Status;

import java.util.List;

public interface IStatusService {
    List<Status> findAll();

    Status findById(int id);

    Status save(Status status);

    void delete(int id);

    Status findByStatusName(String statusName);
}
