package kr.co.sooragenius.study.board.service.impl;

import kr.co.sooragenius.study.board.service.Board;
import kr.co.sooragenius.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    public List<Board> findAll() {
        Example<Board> searchExample = Example.of(Board.builder().testYn("N").deleteYn("N").build());

        return boardRepository.findAll(searchExample);
    }

    @Override
    public void updateDeleteYByBoardId(Integer boardId) throws Exception {
        Board foundBoard = findByBoardId(boardId);

        if(foundBoard != null) {
            foundBoard.setDeleteYn("Y");

            boardRepository.save(foundBoard);
        }
    }

    @Override
    public Board findByBoardId(Integer boardId) throws  Exception{
        Board searchBoard = Board.builder()
                .boardId(boardId)
                .deleteYn("N")
                .testYn("N")
                .build();

        Example<Board> searchExample = Example.of(searchBoard);
        Optional<Board> foundBoard = boardRepository.findOne(searchExample);
        if(foundBoard.isPresent()) {
            return foundBoard.get();
        }

        return null;
    }
}
