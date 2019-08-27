package kr.co.sooragenius.study.board;

import kr.co.sooragenius.study.board.service.Board;
import kr.co.sooragenius.study.board.service.BoardService;
import kr.co.sooragenius.study.board.service.impl.BoardRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardTests {


    @Value("${test.web.absolute-url}")
    String testAbsUrl;

    @Autowired
    BoardService boardService;

    @Autowired
    RestTemplate restTemplate;

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

    @Test
    public void testBoardList() throws Exception{
        String result = restTemplate.getForObject(new URI(testAbsUrl+"/board/list"), String.class);

        System.out.println(result);
        assertNotNull(result);

    }

    @Test
    public void addBoard() throws Exception {
        String boardName = "Test Insert";
        String deleteYn = "N";
        String testYn = "N";

        Board result = addBoardByURI(boardName, deleteYn, testYn);

        assertNotNull(result);

        assertEquals(boardName, result.getBoardName());
        assertEquals(deleteYn, result.getDeleteYn());
        assertEquals(testYn, result.getTestYn());
    }
    @Test
    public void boardDelete() throws Exception {
        String boardName = "Test Insert";
        String deleteYn = "N";
        String testYn = "N";

        Board addedBoard = addBoardByURI(boardName, deleteYn, testYn);

        restTemplate.delete(testAbsUrl+"/board/delete/"+addedBoard.getBoardId());

        HttpStatus viewStatus = null;
        try {
            restTemplate.getForEntity(testAbsUrl+"/board/view/"+addedBoard.getBoardId(),String.class);
        }catch(HttpClientErrorException ex) {
            viewStatus = ex.getStatusCode();
        }finally {
            assertEquals(HttpStatus.NOT_FOUND, viewStatus);
        }
    }


    private Board addBoardByURI(String boardName, String deleteYn, String testYn) throws Exception{
        Board board = Board.builder()
                .boardName(boardName)
                .deleteYn(deleteYn)
                .testYn(testYn)
                .build();

        Board result = restTemplate.postForObject(new URI(testAbsUrl+"/board/add"), board, Board.class);

        return result;
    }
}
