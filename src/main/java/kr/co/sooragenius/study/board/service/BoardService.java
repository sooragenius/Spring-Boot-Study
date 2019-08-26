package kr.co.sooragenius.study.board.service;

import java.util.List;

public interface BoardService {
    public void save(Board board);
    public void deleteByBoardNameStartingWith(String startWith);
    public List<Board> findByBoardNameStartingWith(String startWith);
}
