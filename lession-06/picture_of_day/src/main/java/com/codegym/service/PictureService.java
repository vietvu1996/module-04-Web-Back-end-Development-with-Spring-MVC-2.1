package com.codegym.service;

import com.codegym.model.Picture;
import com.codegym.repository.IPictureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PictureService implements IPictureService {
    private final IPictureRepository pictureRepository;

    public PictureService(IPictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public List<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public Picture findById(int id) {
        return pictureRepository.findById(id);
    }

    @Override
    public Picture like(int id) {
        return pictureRepository.like(id);
    }

    @Override
    public void remove(int id) {
        pictureRepository.remove(id);
    }
}
