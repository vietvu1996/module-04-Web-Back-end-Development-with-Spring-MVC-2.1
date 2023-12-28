package com.springtimezonemaven;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.TimeZone;

@Controller
public class TimeController {
    @GetMapping("world-clock")
    public String getByTimeZone(Model model, @RequestParam(name = "city", required = false, defaultValue = "Asia/Ho_Chi_Minh") String city) {
        Date date = new Date();
        TimeZone local = TimeZone.getDefault();
        TimeZone locale = TimeZone.getTimeZone(city);

        long localTime = date.getTime() + (locale.getRawOffset() - local.getRawOffset());
        date.setTime(localTime);

        model.addAttribute("city", city);
        model.addAttribute("date", date);
        return "index";
    }
}
