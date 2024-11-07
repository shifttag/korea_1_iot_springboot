package org.example.springbootdeveloper.service;

import org.example.springbootdeveloper.common.constant.ResponseMessage;
import org.example.springbootdeveloper.dto.request.PostRequestDto;
import org.example.springbootdeveloper.dto.response.CommentResponseDto;
import org.example.springbootdeveloper.dto.response.PagedResponseDto;
import org.example.springbootdeveloper.dto.response.PostResponseDto;
import org.example.springbootdeveloper.dto.response.ResponseDto;
import org.example.springbootdeveloper.entity.Comment;
import org.example.springbootdeveloper.entity.Post;
import org.example.springbootdeveloper.repository.CommentRepository;
import org.example.springbootdeveloper.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;


    // 1. 추가
    public ResponseDto<PostResponseDto> createPost(PostRequestDto dto) {
        try {
            Post post = new Post(null ,dto.getTitle(), dto.getContent(), dto.getAuthor(), null);
            Post savedPost = postRepository.save(post);
            return ResponseDto.setSuccess("게시글을 성공적으로 등록했습니다.", convertToPostDto(savedPost));
        } catch (Exception e) {
            return ResponseDto.setFailed("게시글 등록에 실패했습니다.");
        }

    }

    // 2. 조회
//    public ResponseDto<List<PostResponseDto>> getAllPosts() {
//        try{
//            List<Post> posts = postRepository.findAll();
//            List<PostResponseDto> postResponseDto = posts.stream()
//                    .map(this::convertToPostDto)
//                    .collect(Collectors.toList());
//
//            if(postResponseDto.isEmpty()) {
//                return ResponseDto.setFailed("등록된 게시글이 없습니다.");
//            }
//            return ResponseDto.setSuccess("게시글을 성공적으로 조회되었습니다.", postResponseDto);
//        } catch (Exception e) {
//            return ResponseDto.setFailed("게시글 조회에 실패했습니다.");
//        }
//    }

    /*
     getPosts
        @Params: 페이지 번호, 페이지 크기
        return: ResponseDto - 성공 메시지와 페이징된 게시글 목록을 포함
    */
    public ResponseDto<PagedResponseDto<PostResponseDto>> getPosts(int page, int size) {
        PagedResponseDto<PostResponseDto> pagedResponse = null;
        try {
            // page와 size 값을 사용해 PageRequest 객체를 생성
            // : 해당 객체를 통해 DB에 해당 페이지의 Post 목록을 조회
            // : 결과는 Page<Post> 타입으로 반환

            /*
                1. PageRequest: Pageable 인터페이스의 구현체
                                - 특정 페이지의 데이터 조회 요청을 정의하는 객체
                                - 페이지 번호와 크기(데이터 수)를 기반으로 페이징 요청을 설정

                    EX) PageRequest.of(int page, int size)
                        : page - 페이지 번호 (0부터 시작)
                        : size - 한 페이지에 포함할 데이터의 개수
                        PageRequest.of(2, 10)

                 2. Page<T>: JPA에서 제공하는 인터페이스
                    , 특정 페이지에 대한 데이터와 페이징 정보를 포함한 객체
                    - 조회된 데이터 목록뿐만 아니라 페이징과 관련된 메타정보도 함께 제공
                    - 주요 메서드 -
                        : getContent() - 현재 페이지 데이터 목록
                        : getNumber() - 현재 페이지 번호 반환 (0부터 시작)
                        : getSize() - 한 페이지에 포함된 데이터의 개수 반환
                        : getTotalPages() - 전체 페이지 수 반환
                        : getTotalElements() - 전체 데이터 수 반환
             */
            Page<Post> postPage = postRepository.findAll(PageRequest.of(page, size));

            List<PostResponseDto> postDtos = postPage.getContent().stream()
                    .map(PostResponseDto::new)
                    .collect(Collectors.toList());

            pagedResponse = new PagedResponseDto<>(
                    postDtos,
                    postPage.getNumber(),   // 요청된 페이지 번호
                    postPage.getTotalPages(),   // 전체 페이지 수
                    postPage.getTotalElements() // 전체 요소 수
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
        }


        return ResponseDto.setSuccess("게시글 목록 조회 성공", pagedResponse);
    }


    // 특정 ID 게시글 조회
    public ResponseDto<PostResponseDto> getPostById(Long postId) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new Error("해당 게시글을 찾을 수 없습니다." + postId));
            return ResponseDto.setSuccess("게시글 조회에 성공했습니다", convertToPostDto(post));
        } catch(Exception e) {
            return ResponseDto.setFailed("게시글 조회에 실패하였습니다.");
        }
    }

    // 작성자 게시글 조회 - 필터링 (나중에 코드 다시 짜야된다함!)
//    public ResponseDto<List<PostResponseDto>> getPostByAuthor(String Author) {
//        try {
//            List<Post> posts = postRepository.findByAuthor(Author);
//            return ResponseDto.setSuccess(Author + "의 게시글이 조회되었습니다.",
//                    posts.stream()
//                            .map(this::convertToPostDto)
//                            .collect(Collectors.toList()));
//        } catch (Exception e) {
//            return ResponseDto.setFailed(Author + "의 게시글 조회에 실패하였습니다.");
//        }
//    }

    // 3. 수정
    public ResponseDto<PostResponseDto> updatePost(Long postId, PostRequestDto dto) {
        try {
             Post post = postRepository.findById(postId)
                     .orElseThrow(() -> new Error("해당 게시글을 찾을수가 없습니다" + postId));
             post.setTitle(dto.getTitle());
             post.setContent(dto.getContent());

             Post updatePost = postRepository.save(post);
             return ResponseDto.setSuccess("게시글이 성공적으로 수정되었습니다.", convertToPostDto(updatePost));
        } catch(Exception e) {
            return ResponseDto.setFailed("게시글 수정에 실패하였습니다.");
        }
    }

    // 4. 삭제
    public ResponseDto<Void> deletePost(Long postId) {
        try {
            postRepository.deleteById(postId);
            return ResponseDto.setSuccess("성공적으로 삭제되었습니다.", null);
        } catch(Exception e) {
            return ResponseDto.setFailed("게시글 삭제에 실패하였습니다.");
        }
    }

    //
    private PostResponseDto  convertToPostDto(Post post) {
        //
        List<CommentResponseDto> commentResponseDto = commentRepository.findByPostId(post.getId())
                .stream()
                .map(this::convertCommentToDto)
                .collect(Collectors.toList());

        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getAuthor(),
                commentResponseDto
        );

    }

    private CommentResponseDto convertCommentToDto(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getPost().getId(),
                comment.getContent(),
                comment.getCommenter()
        );
    }

}
