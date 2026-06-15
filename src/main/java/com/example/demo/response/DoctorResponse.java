package com.example.demo.response;

import com.example.demo.entity.Doctors;
import lombok.Getter;

import java.util.UUID;

@Getter
public class DoctorResponse {
    private UUID id;
    private String fio;

    public DoctorResponse() {
    }

    public DoctorResponse(Doctors doctor) {
        this.id = doctor.getId();
        this.fio = doctor.getFio();
    }
}