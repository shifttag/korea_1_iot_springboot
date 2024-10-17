package org.example.springbootdeveloper.controller;

import org.example.springbootdeveloper.dto.BoardDto;
import org.example.springbootdeveloper.service.BoardService;
import org.example.springbootdeveloper.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/board")
public class BoardController {
    private final BoardService boardService;

    // 생성자 주입
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // 1) id로 단건 조회
    @GetMapping("/{id}")
    public BoardDto getBoardById(@PathVariable Long id) {
        return boardService.getBoardById(id);
    }

    // 2) 전체 조회
    @GetMapping
    public List<BoardDto> getAllBoards() {
        return boardService.getAllBoards();
    }

    // 3) 추가
    @PostMapping
    public BoardDto createBoard(@RequestBody BoardDto boardDto){
        return boardService.createBoard(boardDto);
    }

    // 4) 수정
    @PutMapping("/{id}")
    public BoardDto updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        return boardService.updateBoard(id, boardDto);
    }

    // 5) 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }

}



