package org.example.springbootdeveloper.Todos;

import lombok.RequiredArgsConstructor;
import org.example.springbootdeveloper.common.constant.ResponseMessage;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TodosService {

    private final TodoRepository todoRepository;


    public ResponseDto<TodosResponseDto> createTasks(TodosRequestDto requestDto) {
        Todo data = null;
        try {
            Boolean status = requestDto.getStatus() != null ? requestDto.getStatus() : false;
            Todo todo = new Todo(null, requestDto.getTask(), status);

            data = todoRepository.save(todo);



        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }
        return ResponseDto.setSuccess(ResponseMessage.SUCCESS, convertToResponseDto(data));
    }

    public ResponseDto<List<TodosResponseDto>> getAllTasks() {
        List<TodosResponseDto> data = null;

        try {
            List<Todo> todos = todoRepository.findAll();

            data = todos.stream()
                    .map(TodosResponseDto::new)
                    .collect(Collectors.toList());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("실패");
        }
        return ResponseDto.setSuccess("성공", data);
    }

    public ResponseDto<TodosResponseDto> updateTaskStatus(Long id) {
        TodosResponseDto data = null;

        try {
           Todo todo = todoRepository.findById(id)
                   .orElseThrow(() -> new IllegalArgumentException("오류"));

           todo.setStatus(!todo.getStatus());

           Todo updateTodo = todoRepository.save(todo);
           return ResponseDto.setSuccess("성공", convertToResponseDto(updateTodo));
        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("실패");
        }
    }

    public ResponseDto<TodosResponseDto> deleteTask(Long id) {
        try {
            todoRepository.deleteById(id);
            return ResponseDto.setSuccess("성공", null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed("실패");
        }

    }

    private TodosResponseDto convertToResponseDto(Todo todo) {
        return new TodosResponseDto(
                todo.getId(),
                todo.getTask(),
                todo.getStatus()
        );
    }
}
