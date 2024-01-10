package com.codegym.service;

import com.codegym.model.Picture;
import com.codegym.repository.IPictureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PictureService implements IPictureService {
    private final IPictureRepository pictureRepository;

    public PictureService(IPictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Iterable<Picture> findAll() {
        return pictureRepository.findAll();
    }

    @Override
    public Optional<Picture> findById(int id) {
        return pictureRepository.findById(id);
    }

    @Override
    public void save(Picture picture) {
        pictureRepository.save(picture);
    }

    @Override
    public void remove(int id) {
        pictureRepository.deleteById(id);
    }

    @Override
    public int likes(int id) {
        pictureRepository.like(id);
        return pictureRepository.findById(id).get().getLikes();
    }

    @Override
    public Page<Picture> findAll(Pageable pageable) {
        return pictureRepository.findAll(pageable);
    }
}
