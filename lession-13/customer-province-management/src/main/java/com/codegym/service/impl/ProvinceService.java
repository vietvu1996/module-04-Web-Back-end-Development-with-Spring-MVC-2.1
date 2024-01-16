package com.codegym.service.impl;

import com.codegym.model.Province;
import com.codegym.repository.IProvinceRepository;
import com.codegym.service.IProvinceService;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class ProvinceService implements IProvinceService {
    private final IProvinceRepository provinceRepository;

    public ProvinceService(IProvinceRepository provinceRepository) {
        this.provinceRepository = provinceRepository;
    }

    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public void save(Province province) {
        provinceRepository.save(province);
    }

    @Override
    public Optional<Province> findById(int id) {
        return provinceRepository.findById(id);
    }

    @Override
    public void remove(int id) {
        provinceRepository.deleteById(id);
    }
}
