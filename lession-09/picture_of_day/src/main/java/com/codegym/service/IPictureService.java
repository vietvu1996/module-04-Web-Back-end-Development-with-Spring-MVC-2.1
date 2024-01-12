package com.codegym.service;

import com.codegym.model.Picture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IPictureService {
    Iterable<Picture> findAll();

    Optional<Picture> findById(int id);

    void save(Picture picture) throws Exception;

    void remove(int id);

    int likes(int id);

    Page<Picture> findAll(Pageable pageable);
}
