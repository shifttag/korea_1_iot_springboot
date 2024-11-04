package org.example.springbootdeveloper.Todos;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/todos")
@RequiredArgsConstructor
public class TodosController {

    private final TodosService todosService;


    // 할 일 추가
    @PostMapping
    public ResponseEntity<ResponseDto<TodosResponseDto>> createTasks(@RequestBody TodosRequestDto requestDto) {
        ResponseDto<TodosResponseDto> createTasks = todosService.createTasks(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createTasks);
    }

    // 할 일 가져오기
    @GetMapping
    public ResponseEntity<ResponseDto<List<TodosResponseDto>>> fetchTasks() {
        ResponseDto<List<TodosResponseDto>> fetchTasks = todosService.getAllTasks();
        return ResponseEntity.status(HttpStatus.OK).body(fetchTasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<TodosResponseDto>> updateTaskStatus(@PathVariable Long id) {
        ResponseDto<TodosResponseDto> updateTaskStatus = todosService.updateTaskStatus(id);
        return ResponseEntity.status(HttpStatus.OK).body(updateTaskStatus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<TodosResponseDto>> deleteTask(@PathVariable Long id) {
        ResponseDto<TodosResponseDto> deleteTask = todosService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteTask);
    }
}
