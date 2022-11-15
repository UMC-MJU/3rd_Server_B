package com.example.crud.controller;

import com.example.crud.dto.BoardDto;
import com.example.crud.entity.Board;
import com.example.crud.entity.User;
import com.example.crud.exception.UserNotFoundException;
import com.example.crud.repository.UserRepository;
import com.example.crud.response.Response;
import com.example.crud.service.BoardService;
import com.example.crud.service.UserService;
import io.swagger.models.auth.In;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardservice;

    private final UserRepository userRepository;

    //유저에 대한 게시글 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}/boards")
    public Response getBoards() {
        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        return Response.success(boardservice.getBoards(user));
    }
    //유저에 대한 게시글 단건 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/users/{userId}/boards/{boardId}")
    public Response getBoard(@PathVariable("boardId") Integer boardId,@PathVariable("userId") Integer userId) {
        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        return Response.success(boardservice.getBoard(boardId,user));
    }
    //유저에 대한 게시글 작성
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/users/{userId}/boards")
    public  Response writeBoard(@RequestBody BoardDto boardDto) {
        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        return Response.success(boardservice.writeBoard(boardDto,user));
    }
    //유저에 대한 게시글 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/users/{userId}/boards/{boardId}")
    public Response modifyBoard(@PathVariable("boardId") Integer boardId, @PathVariable("userId") Integer userId, @RequestBody BoardDto boardDto) {

        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        return Response.success(boardservice.modifyBoard(boardId,boardDto,userId, user));
    }
    //유저에 대한 게시글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/boards/{boardId}")
    public void deleteBoard(@PathVariable("boardId") Integer boardId) {
        User user = userRepository.findById(1).orElseThrow(UserNotFoundException::new);

        boardservice.deleteBoard(boardId);

    }


}
