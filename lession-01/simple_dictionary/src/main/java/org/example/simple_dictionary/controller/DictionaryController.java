package org.example.simple_dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DictionaryController {
    Map<String, String> dictionaryList;

    @GetMapping("/dictionary")
    public ModelAndView show() {
        return new ModelAndView("/index");
    }

    @PostMapping("/dictionary")
    public ModelAndView look(@RequestParam String word) {
        dictionaryList = new HashMap<>();
        dictionaryList.put("hello", "xin chào");
        dictionaryList.put("desk", "bàn học");
        dictionaryList.put("table", "bàn ăn");
        dictionaryList.put("chair", "cái ghế");
        dictionaryList.put("water", "nước");

        String result = dictionaryList.get(word);

        if (result == null) {
            result = "This word cannot be found";
        }
        ModelAndView modelAndView = new ModelAndView("/index");
        modelAndView.addObject("result", result);
        return modelAndView;
    }
}
