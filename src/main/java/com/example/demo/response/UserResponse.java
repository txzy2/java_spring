package com.example.demo.response;

import com.example.demo.entity.User;
import com.example.demo.enums.RoleName;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@JsonPropertyOrder({"id", "name", "email", "age", "bookings", "roles", "ext_id"})
public class UserResponse {
    private Long id;

    private String name;

    private String email;

    private Integer age;

    @JsonProperty("org_hash")
    private String userHash;

    @JsonProperty("ext_id")
    private UUID extId;

    private List<BookingResponse> bookings;

    private RoleName role;

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.age = user.getAge();
        this.userHash = user.getUserHash();
        this.role = user.getRoles().getName();
        this.extId = user.getExtId();

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

    public String getUserHash() {
        return userHash;
    }

    public List<BookingResponse> getBookings() {
        return bookings;
    }

    public RoleName getRole() {
        return role;
    }
}