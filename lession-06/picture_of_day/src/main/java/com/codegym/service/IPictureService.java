package com.codegym.service;

import com.codegym.model.Picture;

import java.util.List;

public interface IPictureService {
    List<Picture> findAll();

    void save(Picture picture);

    Picture findById(int id);

    Picture like(int id);

    void remove(int id);
}
