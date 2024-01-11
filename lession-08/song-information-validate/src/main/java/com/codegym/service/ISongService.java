package com.codegym.service;

import com.codegym.model.Song;

import java.util.List;
import java.util.Optional;

public interface ISongService {
    List<Song> findAll();

    void save(Song song);

    Optional<Song> findById(int id);

    void remove(int id);
}
