package kr.co.sooragenius.study.board.service;

import java.util.List;

public interface BoardService {
    public void save(Board board);
    public void deleteByTestYnEquals(String testYn);
    public void updateDeleteYByBoardId(Integer boardId);
    public List<Board> findByTestYnEquals(String testYn);
    public List<Board> findAll();
    public Board findByBoardId(Integer boardId) throws Exception;
}
