package com.example.crud.service;

import com.example.crud.entity.Board;
import com.example.crud.repository.BoardRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        List<Board> boards = boardRepository.findAll();
        return boards;
    }
    @Transactional(readOnly = true)
    public Board getBoard(Integer id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        return board;
    }
    @Transactional
    public Board writeBoard(Board newBoard) {
        Board board = boardRepository.save(newBoard);
        return board;
    }
    @Transactional
    public Board modifyBoard(Integer id, Board changedBoard) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        board.setTitle(changedBoard.getTitle());
        board.setComment(changedBoard.getComment());
        return board;
    }
    @Transactional
    public void deleteBoard(Integer id) {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다.")
        );
        boardRepository.deleteBoardById(id);
        System.out.println("게시글 삭제 완료");
    }
}
