package com.example.demo.response;

import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.stream.Collectors;

@JsonPropertyOrder({"id", "name", "email", "age", "bookings"})
public class UserResponse {
    private final Long id;
    private final String name;
    private final String email;
    private final Integer age;
    private List<BookingResponse> bookings;

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();

        if (user.getBookings() != null) {
            this.bookings = user.getBookings().stream()
                    .map(BookingResponse::new)
                    .collect(Collectors.toList());
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }

    public List<BookingResponse> getBookings() {
        return bookings;
    }
}