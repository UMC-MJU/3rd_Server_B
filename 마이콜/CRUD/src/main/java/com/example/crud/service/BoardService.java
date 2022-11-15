package com.example.crud.service;

import com.example.crud.dto.BoardDto;
import com.example.crud.entity.Board;
import com.example.crud.entity.User;
import com.example.crud.exception.BoardAndWriterNotFoundException;
import com.example.crud.exception.UserNotFoundException;
import com.example.crud.repository.BoardRepository;
import com.example.crud.repository.UserRepository;
import java.nio.channels.WritePendingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    //유저에 대한 게시글 전체 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getBoards(User user) {
        List<Board> boards = boardRepository.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();

        boards.stream().forEach(i->boardDtos.add(BoardDto.toDto(i)));
        return boardDtos;
    }
    @Transactional(readOnly = true)
    public BoardDto getBoard(Integer BoardId,User user) {
        Board board = boardRepository.findById(BoardId).orElseThrow(BoardAndWriterNotFoundException::new);

        return BoardDto.toDto(board);
    }
    @Transactional
    public BoardDto writeBoard(BoardDto newBoard,User user) {
        Board board = new Board();

        board.setUser(user);
        board.setWriter(newBoard.getWriter());
        board.setTitle(newBoard.getTitle());
        board.setComment(newBoard.getComment());
        boardRepository.save(board);


        BoardDto boardDto = BoardDto.toDto(board);

        return boardDto;
    }
    @Transactional
    public BoardDto modifyBoard(Integer id, BoardDto changedBoard,Integer userId, User user) {
        Board board = boardRepository.findById(id).orElseThrow(BoardAndWriterNotFoundException::new);
        board.setUser(user);
        board.setTitle(changedBoard.getTitle());
        board.setComment(changedBoard.getComment());
        BoardDto boardDto = BoardDto.toDto(board);


        return boardDto;
    }
    @Transactional
    public void deleteBoard(Integer id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardAndWriterNotFoundException::new);
        boardRepository.deleteBoardById(id);
        System.out.println("게시글 삭제 완료");
    }
}
