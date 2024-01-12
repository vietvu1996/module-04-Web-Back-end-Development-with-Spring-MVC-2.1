package com.codegym.service;

import com.codegym.logger.BadWordException;
import com.codegym.model.Picture;
import com.codegym.repository.IPictureRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PictureService implements IPictureService {
    private final List<String> badWordsList;
    private final IPictureRepository pictureRepository;

    public PictureService(IPictureRepository pictureRepository, @Value("${bad.words}") String bad_words) {
        this.pictureRepository = pictureRepository;
        this.badWordsList = Arrays.asList(bad_words.split(","));
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
    public void save(Picture picture) throws Exception {
        if (!containBadWord(picture.getComment())) {
            pictureRepository.save(picture);
        }
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

    public boolean containBadWord(String feedback) throws BadWordException {
        String[] badWords = feedback.toLowerCase().split("\\s+");

        System.out.println(badWordsList);
        for (String word : badWords) {
            System.out.println(word);
            if (badWordsList.contains(word)) {
                throw new BadWordException();
            }
        }
        return false;
    }
}
