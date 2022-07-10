package com.cpswork.backend.models;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table( name = "patients_tbl")
public class Patient {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="uuid-char")
    @Column(columnDefinition = "VARCHAR(255)")
    private UUID id;

    private String fullName;

    private Integer age;

    private String phoneNumber;

    public Patient() {
    }

    public Patient(String fullName, Integer age, String phoneNumber) {
        this.fullName = fullName;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
