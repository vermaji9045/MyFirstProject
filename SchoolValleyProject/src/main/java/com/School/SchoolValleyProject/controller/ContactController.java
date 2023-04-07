package com.School.SchoolValleyProject.controller;

import com.School.SchoolValleyProject.ContactService.ServiceContact;
import com.School.SchoolValleyProject.Model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Slf4j
@Controller
public class ContactController  {


    private ServiceContact serviceContact;

    @RequestMapping("/contact")

    public String DispalyContactPage(Model model)
    {
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }

//    @RequestMapping(value = "/saveMsg",method = POST)
//    public ModelAndView saveMessage(@RequestParam String name,@RequestParam String mobileNum,@RequestParam String email,
//                                    @RequestParam String subject,@RequestParam String message) {
//        logger.info("Name :" + name);
//        logger.info("Mobile Number :" + mobileNum);
//        logger.info("Email :" + email);
//        logger.info("Subject :" + subject);
//        logger.info("Message :" + message);
//
//        return new ModelAndView("redirect:/contact");
//    }

    @Autowired
    public ContactController(ServiceContact serviceContact) {
        this.serviceContact = serviceContact;
    }

    @RequestMapping(value = "/saveMsg",method = POST)

    public String saveMessage(@Valid @ModelAttribute("contact")  Contact contact, Errors errors)
    {
        if(errors.hasErrors())
        {
            log.error("Contact Form validation failed due to : " +errors.toString());
            return "contact.html";
        }
        serviceContact.saveMessage(contact);
        serviceContact.setCounter(serviceContact.getCounter()+1);
        log.info("Number of times the Contact form is submitted :"+serviceContact.getCounter());

        return "redirect:/contact";
    }
}
