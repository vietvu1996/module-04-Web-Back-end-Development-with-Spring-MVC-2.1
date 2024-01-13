package com.codegym.service.impl;

import com.codegym.model.Status;
import com.codegym.repository.IStatusRepository;
import com.codegym.service.IStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusService implements IStatusService {
    private final IStatusRepository statusRepository;

    public StatusService(IStatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    @Override
    public List<Status> findAll() {
        return statusRepository.findAll();
    }

    @Override
    public Status findById(int id) {
        return statusRepository.findById(id).orElse(null);
    }

    @Override
    public Status save(Status status) {
        return statusRepository.save(status);
    }

    @Override
    public void delete(int id) {
        statusRepository.deleteById(id);
    }

    @Override
    public Status findByStatusName(String statusName) {
        return statusRepository.findByStatusName(statusName);
    }
}
