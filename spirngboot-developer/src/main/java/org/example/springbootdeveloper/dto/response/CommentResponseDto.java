package org.example.springbootdeveloper.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private Long postId;
    private String content;
    private String commenter;
}
