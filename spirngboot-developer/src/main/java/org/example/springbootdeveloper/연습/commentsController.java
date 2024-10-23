//package org.example.springbootdeveloper.연습;
//
//import org.example.springbootdeveloper.dto.request.CommentRequestDto;
//import org.example.springbootdeveloper.dto.response.CommentResponseDto;
//import org.example.springbootdeveloper.dto.response.ResponseDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/comments1")
//public class commentsController {
//    @Autowired
//    private CommentService commentService;
//
//    // 1. 추가
//    @PostMapping
//    public ResponseDto<CommentResponseDto> createComment(@RequestBody CommentRequestDto dto) {
//        return commentService.createComment(dto);
//    }
//
//    // 2. 조회
//    @GetMapping("/post/{postId}")
//    public ResponseDto<List<CommentResponseDto>> getCommentsByPost(@PathVariable Long postId) {
//        return commentService.getCommentsByPost(postId);
//    }
//
//    // 3. 수정
//    @PutMapping("/{commentId}")
//    public ResponseDto<CommentResponseDto> updateComment(@PathVariable Long commentId, @RequestBody String newContent) {
//        return commentService.updateComment(commentId, newContent);
//    }
//
//    // 4. 삭제
//    @DeleteMapping("/{commentId}")
//    public ResponseDto<Void> deleteComment(@PathVariable Long commentId) {
//        return commentService.deleteComment(commentId);
//    }
//
//}
