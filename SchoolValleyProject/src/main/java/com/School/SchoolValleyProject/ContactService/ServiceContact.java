package com.School.SchoolValleyProject.ContactService;

import com.School.SchoolValleyProject.Model.Contact;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ServiceContact {


    private int counter=0;

    public ServiceContact() {
        System.out.println("Contact Service Bean Created");
    }

    public boolean saveMessage(Contact contact)
    {
        boolean isSaved=true;

        log.info(contact.toString());
         return   isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
