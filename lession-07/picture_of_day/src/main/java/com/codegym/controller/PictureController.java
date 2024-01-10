package com.codegym.controller;

import com.codegym.model.Picture;
import com.codegym.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/pictures")
public class PictureController {
    private final IPictureService pictureService;

    public PictureController(IPictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("")
    public ModelAndView getAllComments(Model model) {
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Sort.Order.asc("authorName")));
        Page<Picture> pictures = pictureService.findAll(pageable);
        ModelAndView view = new ModelAndView("index");
        view.addObject("pictures", pictures);
        view.addObject("picture", new Picture());
        return view;
    }

    @PostMapping("/create")
    public ModelAndView create(Picture picture) {
        pictureService.save(picture);
        Iterable<Picture> pictures = pictureService.findAll();
        ModelAndView view = new ModelAndView("index");
        view.addObject("pictures", pictures);
        view.addObject("picture", new Picture());
        view.addObject("message", "Add picture successfully !");
        return view;
    }

    @PostMapping("/{id}/like")
    public String likeComment(@PathVariable("id") int id) {
        pictureService.likes(id);
        return "redirect:/pictures";
    }

    @GetMapping("/page")
    public ModelAndView page(@RequestParam(value = "page", defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 1, Sort.by(Sort.Order.asc("authorName")));
        Page<Picture> pictures = pictureService.findAll(pageable);
        ModelAndView view = new ModelAndView("index");
        view.addObject("pictures", pictures);
        view.addObject("picture", new Picture());
        return view;
    }
}
