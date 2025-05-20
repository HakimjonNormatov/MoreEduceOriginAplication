package org.example.moreeduceoriginapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {

    private String email;

    private String username;

    private Long age;

    private String phonenumber;

    private String password;

    private String repassword;

    private Long address_id;


    //Address

    private String city;

    private String region;

}
