package com.codegym.service;

import com.codegym.model.Music;

import java.util.ArrayList;
import java.util.List;

public class MusicService implements IMusicService {
    private final List<Music> musicList;

    public MusicService() {
        musicList = new ArrayList<>();
    }

    @Override
    public List<Music> findAll() {
        return musicList;
    }

    @Override
    public void save(Music music) {
        musicList.add(music);
    }

    @Override
    public Music findById(int id) {
        return musicList.get(id);
    }

    @Override
    public void update(int id, Music music) {
        int index = musicList.indexOf(findById(id));
        musicList.set(index, music);
    }

    @Override
    public void remove(int id) {
        musicList.remove(findById(id));
    }
}
