package org.example.springbootdeveloper.Todos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodosRequestDto {
    private String task;
    private Boolean status;
}
