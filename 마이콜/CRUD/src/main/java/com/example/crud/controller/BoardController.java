package com.example.crud.controller;

import com.example.crud.entity.Board;
import com.example.crud.service.BoardService;
import java.util.List;
import javax.swing.text.html.parser.Entity;
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

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards")
    public List<Board> getBoards() {
        return boardservice.getBoards();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/{id}")
    public Board getBoard(@PathVariable("id") Integer id) {
        return boardservice.getBoard(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/boards")
    public Board writeBoard(@RequestBody Board boardReq) {
        return boardservice.writeBoard(boardReq);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/boards/{id}")
    public Board modifyBoard(@PathVariable("id") Integer id,@RequestBody Board boardReq) {
        return boardservice.modifyBoard(id,boardReq);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/boards/{id}")
    public void deleteBoard(@PathVariable("id") Integer id) {
        boardservice.deleteBoard(id);

    }











}
