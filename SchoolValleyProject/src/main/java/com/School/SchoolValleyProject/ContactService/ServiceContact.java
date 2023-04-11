package com.School.SchoolValleyProject.ContactService;

import com.School.SchoolValleyProject.Constants.ValleyPublicConst;
import com.School.SchoolValleyProject.Model.Contact;
import com.School.SchoolValleyProject.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ServiceContact {


    @Autowired
    private ContactRepository contactRepository;



    public boolean saveMessage(Contact contact)
    {
        boolean isSaved=false;
        contact.setStatus(ValleyPublicConst.OPEN);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setCreatedBy(ValleyPublicConst.ANONYMOUS);
        int result=contactRepository.saveContactMsg(contact);



       if (result>0)
          isSaved=true;

       return isSaved;
    }

    public List<Contact> findMsgsWithOpenStatus()
    {
        List<Contact> contactMsgs=contactRepository.findMsgsWithStatus(ValleyPublicConst.OPEN);

        return contactMsgs;
    }


}
