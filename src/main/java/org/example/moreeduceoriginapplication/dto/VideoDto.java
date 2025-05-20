package org.example.moreeduceoriginapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {
    private String name;
    private String duration;
    private String size;
    private String path;

}
