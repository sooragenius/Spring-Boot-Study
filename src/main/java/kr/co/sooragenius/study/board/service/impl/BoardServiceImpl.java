package kr.co.sooragenius.study.board.service.impl;

import kr.co.sooragenius.study.board.service.Board;
import kr.co.sooragenius.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional(rollbackOn = Exception.class)
@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Override
    public void save(Board board) {
        boardRepository.save(board);
    }

    @Override
    public void deleteByBoardNameStartingWith(String startWith) {
        boardRepository.deleteByBoardNameStartingWith(startWith);
    }

    @Override
    public List<Board> findByBoardNameStartingWith(String startWith) {
        return boardRepository.findByBoardNameStartingWith(startWith);
    }
}
