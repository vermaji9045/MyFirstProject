package com.School.SchoolValleyProject.controller;
import com.School.SchoolValleyProject.Model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidayContoller {

    @GetMapping("/holidays/{display}")
    public String DisplayHoliday(@PathVariable String display, Model model)
//This used to get the RequestParam by using the @requestparam keyword
//RequestParam(required = false) boolean festival,@RequestParam(required = false) boolean federal
    {
        //model.addAttribute("festival",festival);
       // model.addAttribute("federal",federal);

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

        List<Holiday>holidays= Arrays.asList(

                new Holiday("1 January","New Year", Holiday.Type.FEDERAL),
                new Holiday("18 February","Shivratri", Holiday.Type.FESTIVAL),
                new Holiday("8 March","Holi", Holiday.Type.FESTIVAL),
                new Holiday("12 April","Chinampa", Holiday.Type.FEDERAL),
                new Holiday("1 May","Labor Day", Holiday.Type.FEDERAL),
                new Holiday("1 October","Diwali", Holiday.Type.FESTIVAL),
                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
                new Holiday(" Jan 17 ","Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
                new Holiday("18 Aug","Rakesh Bandan",Holiday.Type.FESTIVAL)
                );

        Holiday.Type[] types=Holiday.Type.values();

        for (Holiday.Type type: types)
        {
         model.addAttribute(type.toString(),(holidays.stream().filter(holiday ->
                 holiday.getType().equals(type)).collect(Collectors.toList()))
         );
        }

        return "holidays.html";
    }
}
