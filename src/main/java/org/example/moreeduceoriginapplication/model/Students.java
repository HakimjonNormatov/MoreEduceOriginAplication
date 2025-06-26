package org.example.moreeduceoriginapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.moreeduceoriginapplication.model.temp.Status;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(nullable = false , unique = true)
    private String email;
    @Column(nullable = false , unique = true)
    private String username;
    @Column(nullable = false)
    private Long age;
    @Column(nullable = false)
    private String phonenumber;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String repassword;
    @Enumerated(EnumType.STRING)
    private Status status;
    @CreatedDate
    private LocalDateTime localDateTime=LocalDateTime.now();

    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String region;

    private boolean enable;

}
