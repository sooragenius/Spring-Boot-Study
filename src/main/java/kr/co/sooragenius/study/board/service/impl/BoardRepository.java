package kr.co.sooragenius.study.board.service.impl;

import kr.co.sooragenius.study.board.service.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Integer> {
    void deleteByBoardNameStartingWith(String startWith);
    List<Board> findByBoardNameStartingWith(String startWith);
}
