package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.service.ISongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final ISongService songService;

    public SongController(ISongService songService) {
        this.songService = songService;
    }

    @GetMapping("")
    public String showSong(Model model) {
        List<Song> songs = songService.findAll();
        model.addAttribute("songs", songs);
        return "list";
    }

    @GetMapping("/create")
    public ModelAndView createSong() {
        ModelAndView view = new ModelAndView("create");
        view.addObject("song", new Song());
        return view;
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("song") Song song, BindingResult result) {
        if (result.hasFieldErrors()) {
            return "create";
        }
        songService.save(song);
        ModelAndView view = new ModelAndView("result");
        view.addObject("song", song);
        return "result";
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateSong(@PathVariable int id) {
        ModelAndView view;
        Optional<Song> song = songService.findById(id);

        if (song.isPresent()) {
            view = new ModelAndView("update");
            view.addObject("song", song.get());
        } else {
            return new ModelAndView("error-404");
        }
        return view;
    }

    @PostMapping("/update")
    public String update(@Validated  Song song, RedirectAttributes redirect,BindingResult result) {
        if(result.hasFieldErrors()){
            return "update";
        }
        songService.save(song);
        redirect.addFlashAttribute("message", "Updated song successfully !");
        return "redirect:/songs";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView deleteSong(@PathVariable int id) {
        Optional<Song> song = songService.findById(id);
        if (song.isPresent()) {
            ModelAndView view = new ModelAndView("delete");
            view.addObject("song", song.get());
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("song") Song song, RedirectAttributes redirect) {
        songService.remove(song.getId());
        redirect.addFlashAttribute("message", "Delete song successfully !");
        return "redirect:/songs";
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewSong(@PathVariable int id) {
        Optional<Song> song = songService.findById(id);
        if (song.isPresent()) {
            ModelAndView view = new ModelAndView("view");
            view.addObject("song", song.get());
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }
}
