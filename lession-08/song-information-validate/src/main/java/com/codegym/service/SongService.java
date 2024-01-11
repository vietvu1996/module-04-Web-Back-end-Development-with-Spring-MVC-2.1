package com.codegym.service;

import com.codegym.model.Song;
import com.codegym.repository.ISongRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements ISongService {
    private final ISongRepository repository;

    public SongService(ISongRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Song> findAll() {
        return repository.findAll();
    }

    @Override
    public void save(Song song) {
        repository.save(song);
    }

    @Override
    public Optional<Song> findById(int id) {
        return repository.findById(id);
    }

    @Override
    public void remove(int id) {
        repository.deleteById(id);
    }
}
