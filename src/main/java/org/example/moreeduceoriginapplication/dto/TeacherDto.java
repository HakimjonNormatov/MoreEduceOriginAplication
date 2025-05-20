package org.example.moreeduceoriginapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {

    private String original_full_name;

    private String email;

    private String phonenumber;

    private String username;

    private Long age;

    private Long address_Id;

    private String password;

    private String repassword;

    private String fanlar;

    // Address

    private String city;

    private String region;

}
