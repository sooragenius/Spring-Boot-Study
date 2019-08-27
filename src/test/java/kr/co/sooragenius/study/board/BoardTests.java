package kr.co.sooragenius.study.board;

import kr.co.sooragenius.study.board.service.Board;
import kr.co.sooragenius.study.board.service.BoardService;
import kr.co.sooragenius.study.board.service.impl.BoardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardTests {
    @Autowired
    BoardService boardService;


    @Test
    public void testBoardInsert() {
        boardService.deleteByTestYnEquals("Y");

        String testBoardName = "Test_";
        int loopSize = 10;

        for(int i = 0; i<loopSize; i++) {
            Board board = Board.builder()
                    .boardName(testBoardName+i)
                    .deleteYn("N")
                    .testYn("Y")
                    .build();

            boardService.save(board);
        }

        List<Board> board = boardService.findByTestYnEquals("Y");

        assertFalse(board.isEmpty());
        assertTrue(board.size() == loopSize);
        board.stream().forEach(board1 -> {
            assertTrue(board1.getBoardName().contains(testBoardName));
            assertTrue(board1.getDeleteYn().equals("N"));
        });

        boardService.deleteByTestYnEquals("Y");

        assertTrue(boardService.findByTestYnEquals("Y").isEmpty());

    }
}
