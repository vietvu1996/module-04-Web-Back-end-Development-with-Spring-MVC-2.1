package com.codegym.formatter;

import com.codegym.model.Province;
import com.codegym.service.IProvinceService;
import org.springframework.format.Formatter;

import java.util.Locale;
import java.util.Optional;

public class ProvinceFormatter implements Formatter<Province> {
    private final IProvinceService provinceService;

    public ProvinceFormatter(IProvinceService provinceService) {
        this.provinceService = provinceService;
    }

    @Override
    public Province parse(String text, Locale locale) {
        Optional<Province> province = provinceService.findById(Integer.parseInt(text));
        return province.orElse(null);
    }

    @Override
    public String print(Province object, Locale locale) {
        return "[" + object.getId() + ", " + object.getName() + "]";
    }
}
