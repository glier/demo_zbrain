package com.example.demo.controllers;

import com.example.demo.domain.EmailStorage;
import com.example.demo.services.EmailStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EmailStorageController {

    private final EmailStorageService service;
    @Autowired
    public EmailStorageController(EmailStorageService service) {
        this.service = service;
    }

    @GetMapping("/emails")
    public List<EmailStorage> getAll() {
        return service.findAll();
    }

    @GetMapping("/emails/{id}")
    public Optional<EmailStorage> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/emails/add")
    public EmailStorage addEmailToStorage(@RequestBody EmailStorage newEmail) {
        return service.save(newEmail);
    }
}
