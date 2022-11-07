package com.example.crud.repository;

import com.example.crud.entity.Board;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
//Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    public List<Board> findAll();

    public Board save(Board writeBoard);

    public void deleteBoardById(Integer id);
}
