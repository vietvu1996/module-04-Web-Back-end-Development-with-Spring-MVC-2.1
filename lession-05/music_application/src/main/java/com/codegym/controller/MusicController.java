package com.codegym.controller;

import com.codegym.model.Music;
import com.codegym.model.MusicForm;
import com.codegym.service.HibernateMusicService;
import com.codegym.service.IMusicService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/music")
public class MusicController {
    @Value("${file-upload}")
    private String fileUpload;
    private final IMusicService musicService = new HibernateMusicService();

    @GetMapping("")
    public String list(Model model) {
        List<Music> musicList = musicService.findAll();
        model.addAttribute("musicList", musicList);
        return "list";
    }

    @GetMapping("/create")
    public ModelAndView showUploadForm() {
        ModelAndView view = new ModelAndView("create");
        view.addObject("musicForm", new MusicForm());
        return view;
    }

    @PostMapping("/upload")
    public ModelAndView uploadMusic(@ModelAttribute MusicForm musicForm) {
        MultipartFile file = musicForm.getFileMusic();
        String fileMusic = file.getOriginalFilename();
        try {
            FileCopyUtils.copy(musicForm.getFileMusic().getBytes(), new File(fileUpload + fileMusic));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Music music = new Music(musicForm.getId(), musicForm.getMusicName(), musicForm.getSingerName(), musicForm.getType(), fileMusic);
        musicService.save(music);
        System.out.println("FileMusic:" + fileMusic + "FilePath: " + fileUpload);
        ModelAndView modelAndView = new ModelAndView("redirect:/music");
        modelAndView.addObject("musicForm", musicForm);
        modelAndView.addObject("message", "Upload new file successfully");
        return modelAndView;
    }

    @GetMapping("/{id}/update")
    public String updateMusic(@PathVariable int id, Model model) {
        Music music = musicService.findById(id);
        MusicForm musicForm = new MusicForm();
        musicForm.setId(music.getId());
        musicForm.setMusicName(music.getMusicName());
        musicForm.setSingerName(music.getSingerName());
        musicForm.setType(music.getType());
        model.addAttribute("musicForm", musicForm);
        return "update";
    }

    @PostMapping("/delete")
    public String deleteMusic(@ModelAttribute Music music, RedirectAttributes redirect) {
        musicService.remove(music.getId());
        redirect.addFlashAttribute("success", "Removed song successfully !");
        return "redirect:/music";
    }

    @PostMapping("/update")
    public String updateSong(@ModelAttribute MusicForm musicForm, RedirectAttributes redirect) {
        MultipartFile multipartFile = musicForm.getFileMusic();
        String fileName = multipartFile.getOriginalFilename();
        assert fileName != null;
        if (!isValidFileExtension(fileName)) {
            redirect.addFlashAttribute("error", "Only accept file have extension: .mp3, .wav, .ogg, .m4p");
            return "update";
        }
        try {
            FileCopyUtils.copy(musicForm.getFileMusic().getBytes(), new File(fileUpload + fileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Music music = new Music(musicForm.getId(), musicForm.getMusicName(), musicForm.getSingerName(), musicForm.getType(), fileName);
        musicService.save(music);
        redirect.addFlashAttribute("success", "Update song successfully!");
        return "redirect:/music";
    }

    private boolean isValidFileExtension(String fileMusic) {
        return fileMusic.toLowerCase().endsWith(".mp3") ||
                fileMusic.toLowerCase().endsWith(".wav") ||
                fileMusic.toLowerCase().endsWith(".ogg") ||
                fileMusic.toLowerCase().endsWith(".mp4");
    }
}
