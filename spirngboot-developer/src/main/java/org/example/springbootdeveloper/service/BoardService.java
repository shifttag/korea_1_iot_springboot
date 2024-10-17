package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.dto.BoardDto;
import org.example.springbootdeveloper.entity.Board;
import org.example.springbootdeveloper.repository.BoardRepository;
import org.example.springbootdeveloper.repository.StudentRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    // 생성자 주입
    public BoardService(BoardRepository boardRepository, StudentRepository studentRepository) {
        this.boardRepository = boardRepository;
    }

    // 1) 단건 조회
    public BoardDto getBoardById(Long id) {
        try{
            Board board = boardRepository.findById(id)
                    .orElseThrow(() -> new Error("board not found with id" + id));
            return new BoardDto(
                    board.getId(),
                    board.getWriter(),
                    board.getTitle(),
                    board.getContent(),
                    board.getCategory()
            );
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "error", e
            );
        }
    }

    // 2) 전체 조회
    public List<BoardDto> getAllBoards() {
        try {
            return boardRepository
                    .findAll()
                    .stream()
                    .map(board -> new BoardDto(
                            board.getId(),
                            board.getWriter(),
                            board.getTitle(),
                            board.getContent(),
                            board.getCategory()
                    )).collect(Collectors.toList());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "error", e
            );
        }
    }

    // 3) 추가
    public BoardDto createBoard(BoardDto boardDto) {
        try {
            Board board = new Board(boardDto.getWriter(), boardDto.getTitle(), boardDto.getContent(), boardDto.getCategory());

            Board savedboard = boardRepository.save(board);

            return new BoardDto(savedboard.getId(), savedboard.getWriter(), savedboard.getTitle(), savedboard.getContent(), savedboard.getCategory());
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "error" , e
            );
        }
    }

    // 4) 수정
    public BoardDto updateBoard(Long id, BoardDto boardDto) {
        try {
            Board board = boardRepository.findById(id)
                    .orElseThrow(() -> new Error("Board not fount with id" + id));
            board.setWriter(boardDto.getWriter());
            board.setTitle(boardDto.getTitle());
            board.setContent(boardDto.getContent());
            board.setCategory(boardDto.getCategory());

            Board updateBoard = boardRepository.save(board);
            return new BoardDto(updateBoard.getId(),
                    updateBoard.getWriter(),
                    updateBoard.getTitle(),
                    updateBoard.getContent(),
                    updateBoard.getCategory());
        } catch(Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "error", e
            );
        }
    }

    // 5) 삭제
    public void deleteBoard(Long id) {
        try {
            Board board = boardRepository.findById(id)
                    .orElseThrow(() -> new Error("Board not fount with id" + id));
            boardRepository.delete(board);
        } catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "error", e
            );
        }
    }
}
