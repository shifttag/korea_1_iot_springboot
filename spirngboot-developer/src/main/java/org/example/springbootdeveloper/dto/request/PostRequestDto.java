package org.example.springbootdeveloper.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostRequestDto {
    private String title;
    private String content;
    private String author;
}
