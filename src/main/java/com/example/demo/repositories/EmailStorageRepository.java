package com.example.demo.repositories;

import com.example.demo.domain.EmailStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailStorageRepository extends JpaRepository<EmailStorage, Long> {
  EmailStorage findByEmail(String email);

  Optional<EmailStorage> findById(Long id);

}
