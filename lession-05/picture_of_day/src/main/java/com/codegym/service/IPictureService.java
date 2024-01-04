package com.codegym.service;

import com.codegym.model.Picture;

import java.util.List;

public interface IPictureService {
    List<Picture> findAll();

    void save(Picture picture);

    Picture findById(int id);

    Picture update(Picture picture);

    Picture like(int id);
}
