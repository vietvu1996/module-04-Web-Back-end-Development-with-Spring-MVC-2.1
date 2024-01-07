package com.codegym.controller;

import com.codegym.model.Picture;
import com.codegym.service.IPictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/pictures")
public class PictureController {
    @Autowired
    private IPictureService pictureService;

    @GetMapping("")
    public String getAllComments(Model model) {
        List<Picture> pictures = pictureService.findAll();
        model.addAttribute("pictures", pictures);
        return "index";
    }

    @PostMapping("")
    public String save(Picture picture) {
        pictureService.save(picture);
        return "redirect:/pictures";
    }

    @GetMapping("/{id}")
    public String getCommentById(@PathVariable int id, Model model) {
        Picture picture = pictureService.findById(id);
        model.addAttribute("picture", picture);
        return "picture";
    }

    @PostMapping("/{id}/update")
    public String updateComment(@PathVariable int id, @ModelAttribute Picture picture) {
        picture.setId(id);
        pictureService.save(picture);
        return "redirect:/pictures";
    }

    @PostMapping("/{id}/like")
    public String likeComment(@PathVariable int id) {
        pictureService.like(id);
        return "redirect:/pictures";
    }
}
