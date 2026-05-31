package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class User extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Roles role;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;
    @Column(nullable = false, unique = true, length = 100)
    private String email;
    @Column(nullable = false, length = 256)
    private String password;
    @Column(nullable = false)
    private Integer age;
    @Column(nullable = false)
    private String userHash;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private List<Booking> bookings = new ArrayList<>();
    @Column(nullable = false, unique = true)
    private UUID extId;

    public static User create(String email, String name, int age, String hashedPassword, String userHash, Roles role,
                              UUID extId) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setAge(age);
        user.setPassword(hashedPassword);
        user.setUserHash(userHash);
        user.setRole(role);
        user.setExtId(extId);
        return user;
    }

    public void addBooking(Booking booking) {
        bookings.add(booking);
        booking.setUser(this);
    }

    public void removeBooking(Booking booking) {
        bookings.remove(booking);
        booking.setUser(null);
    }
}
