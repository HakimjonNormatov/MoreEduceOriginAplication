package org.example.moreeduceoriginapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private String name;
    private String email;
    private String comment;
    private Integer like_id;
}
