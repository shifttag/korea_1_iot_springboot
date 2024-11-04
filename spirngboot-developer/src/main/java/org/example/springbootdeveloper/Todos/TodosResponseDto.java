package org.example.springbootdeveloper.Todos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodosResponseDto {
    private Long id;
    private String task;
    private boolean status;


    public TodosResponseDto(Todo todo) {
        this.id = todo.getId();
        this.task = todo.getTask();
        this.status = todo.getStatus();

    }
}
