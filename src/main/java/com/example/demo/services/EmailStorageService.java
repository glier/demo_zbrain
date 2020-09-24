package com.example.demo.services;

import com.example.demo.domain.EmailStorage;
import com.example.demo.exceptions.DuplicateException;
import com.example.demo.repositories.EmailStorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmailStorageService {
    private final EmailStorageRepository repository;

    @Autowired
    public EmailStorageService(EmailStorageRepository repository) {
        this.repository = repository;
    }

    public List<EmailStorage> findAll() {
        return repository.findAll();
    }

    public Optional<EmailStorage> findById (Long id) {
        return repository.findById(id);
    }

    public EmailStorage save(EmailStorage emailStorage)  {
        if (repository.findByEmail(emailStorage.getEmail()) != null) {
            throw new DuplicateException();
        } else {
            repository.save(emailStorage);
        }

        return repository.findByEmail(emailStorage.getEmail());
    }

    public EmailStorage findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
