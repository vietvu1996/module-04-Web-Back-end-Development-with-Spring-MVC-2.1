package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.Province;
import com.codegym.service.ICustomerService;
import com.codegym.service.IProvinceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/provinces")
public class ProvinceController {
    private final IProvinceService provinceService;
    private final ICustomerService customerService;

    public ProvinceController(IProvinceService provinceService, ICustomerService customerService) {
        this.provinceService = provinceService;
        this.customerService = customerService;
    }

    @GetMapping("")
    public ModelAndView listProvince() {
        ModelAndView view = new ModelAndView("/province/list");
        Iterable<Province> provinces = provinceService.findAll();
        view.addObject("provinces", provinces);
        return view;
    }

    @GetMapping("/create")
    public ModelAndView createForm() {
        ModelAndView view = new ModelAndView("/province/create");
        view.addObject("province", new Province());
        return view;
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("province") Province province, RedirectAttributes redirect) {
        provinceService.save(province);
        redirect.addFlashAttribute("message", "Create new province successfully !");
        return "redirect:/provinces";
    }

    @GetMapping("/{id}/update")
    public ModelAndView updateProvince(@PathVariable int id) {
        Optional<Province> province = provinceService.findById(id);
        if (province.isPresent()) {
            ModelAndView view = new ModelAndView("/province/update");
            view.addObject("province", province.get());
            return view;
        } else {
            return new ModelAndView("error-404");
        }
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("province") Province province, RedirectAttributes redirect) {
        provinceService.save(province);
        redirect.addFlashAttribute("message", "Updated province successfully");
        return "redirect:/provinces";
    }

//    @GetMapping("/{id}/view")
//    public ModelAndView viewProvince(@PathVariable("id") int id) {
//        Optional<Province> province = provinceService.findById(id);
//        if (!province.isPresent()) {
//            return new ModelAndView("error-404");
//        }
//        Iterable<Customer> customers = customerService.findAllByProvince(province.get());
//        ModelAndView view = new ModelAndView("view");
//        view.addObject("customers", customers);
//        return view;
//    }
}
