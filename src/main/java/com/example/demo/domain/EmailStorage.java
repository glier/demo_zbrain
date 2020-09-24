package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "email_storage")
public class EmailStorage implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String email;
}
