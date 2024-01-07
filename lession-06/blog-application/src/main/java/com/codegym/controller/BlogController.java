package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.IBlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/blogs")
public class BlogController {
    private final IBlogService blogService;

    public BlogController(IBlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("")
    public ModelAndView showList() {
        List<Blog> blogs = blogService.findAll();
        ModelAndView view = new ModelAndView("list");
        view.addObject("blogs", blogs);
        return view;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView view = new ModelAndView("create");
        view.addObject("blog", new Blog());
        return view;
    }

    @PostMapping("/create")
    public ModelAndView createBlog(@ModelAttribute("blog") Blog blog) {
        blogService.save(blog);
        ModelAndView view = new ModelAndView("create");
        view.addObject("blog", blog);
        view.addObject("message", "Created new blog successfully");
        return view;
    }

    @GetMapping("/{id}/update")
    public ModelAndView showUpdateForm(@PathVariable int id) {
        ModelAndView view;
        Blog blog = blogService.findById(id);
        if (blog != null) {
            view = new ModelAndView("update");
            view.addObject("blog", blog);
        } else {
            view = new ModelAndView("error-404");
        }
        return view;
    }

    @PostMapping("/update")
    public String updateBlog(Blog blog, RedirectAttributes redirect) {
        blogService.save(blog);
        redirect.addFlashAttribute("message", "Updated blog successfully !");
        return "redirect:/blogs";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDeleteBlog(@PathVariable int id) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView view = new ModelAndView("delete");
            view.addObject("blog", blog);
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/delete")
    public ModelAndView deleteBlog(@ModelAttribute("blog") Blog blog, RedirectAttributes redirect) {
        blogService.remove(blog.getId());
        redirect.addFlashAttribute("message", "Delete blog successfully !");
        return new ModelAndView("redirect:/blogs");
    }

    @GetMapping("/{id}/view")
    public ModelAndView viewBlog(@PathVariable int id) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            ModelAndView view = new ModelAndView("view");
            view.addObject("blog", blog);
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }
}
