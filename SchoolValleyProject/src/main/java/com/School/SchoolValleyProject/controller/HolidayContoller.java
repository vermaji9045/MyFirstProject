package com.School.SchoolValleyProject.controller;
import com.School.SchoolValleyProject.Model.Holiday;
import com.School.SchoolValleyProject.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayContoller {


@Autowired
  private  HolidayRepository holidayRepository;

    @GetMapping("/holidays/{display}")
    public String DisplayHoliday(@PathVariable String display, Model model)
    {

        if (null!= display && display.equals("all"))
        {
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        }
        else   if (null!= display && display.equals("festival"))
        {
            model.addAttribute("festival",true);
        }else   if (null!= display && display.equals("federal"))
        {

            model.addAttribute("federal",true);
        }

       List<Holiday>holidays= holidayRepository.findAllHoliday();
        Holiday.Type[] types=Holiday.Type.values();

        for (Holiday.Type type: types)
        {
         model.addAttribute(type.toString(),(holidays.stream().filter(holiday ->
                 holiday.getType().equals(type)).collect(Collectors.toList())));

        }

        return "holidays.html";
    }
}
