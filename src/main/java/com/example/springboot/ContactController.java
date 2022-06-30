package com.example.springboot;

import com.example.springboot.dataservice.es.repositories.entities.Contact;
import com.example.springboot.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
public class ContactController {

    @Autowired
    ContactService contactService;

    @GetMapping("/all")
    public Iterable<Contact> getAll() {
        return contactService.getAll();
    }

    @PostMapping
    public String createContact(@RequestBody Contact contact) {
        contactService.addContact(contact);
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/byFnLn")
    public List<Contact> getContact(@RequestParam("firstName") String firstName,
                                    @RequestParam("lastName") String lastName) {
        return contactService.getContact(firstName, lastName);
    }

}
