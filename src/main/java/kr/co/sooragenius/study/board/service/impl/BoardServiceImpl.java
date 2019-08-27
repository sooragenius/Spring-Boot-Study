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
    public void deleteByTestYnEquals(String testYn) {
        boardRepository.deleteByTestYnEquals(testYn);
    }

    @Override
    public List<Board> findByTestYnEquals(String testYn) {
        return boardRepository.findByTestYnEquals(testYn);
    }
}
