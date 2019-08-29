package kr.co.sooragenius.study.board;

import kr.co.sooragenius.study.board.service.Board;
import kr.co.sooragenius.study.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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

        boardDelete(addedBoard);
    }
    @Test
    public void boardValidateTest() {
        Board nameEmpty = Board.builder()
                .deleteYn("N")
                .testYn("Y").build();

        Board deleteEmpty = Board.builder()
                .boardName("Hello")
                .testYn("Y").build();

        Board testEmpty = Board.builder()
                .boardName("Hello")
                .deleteYn("N")
                .build();

        Board normal = Board.builder()
                .boardName("Hello")
                .deleteYn("N")
                .testYn("N").build();

        HttpStatus nameStatus = getHttpStatusByPostForEntity(testAbsUrl+"/board/add", nameEmpty);
        HttpStatus deleteStatus = getHttpStatusByPostForEntity(testAbsUrl+"/board/add", deleteEmpty);
        HttpStatus testStatus = getHttpStatusByPostForEntity(testAbsUrl+"/board/add", testEmpty);


        Board addedBoard = restTemplate.postForObject(testAbsUrl+"/board/add", normal, Board.class);

        assertEquals(HttpStatus.BAD_REQUEST, nameStatus);
        assertEquals(HttpStatus.BAD_REQUEST, deleteStatus);
        assertEquals(HttpStatus.BAD_REQUEST, testStatus);

        assertEquals(normal.getBoardName(), addedBoard.getBoardName());
        assertEquals(normal.getDeleteYn(), addedBoard.getDeleteYn());
        assertEquals(normal.getTestYn(), addedBoard.getTestYn());

        boardDelete(addedBoard);
    }
    @Test
    @Transactional
    public void getBoardWithEntitys() {
        List<Board> boards = boardService.findAll();
        AtomicInteger entityCount = new AtomicInteger();
        boards.stream().filter(board -> board.getEntities().size() > 0).forEach( item ->
            item.getEntities().forEach(
                    entity ->
                    {
                        log.info(entity.getTitle());
                        entityCount.getAndIncrement();
                        assertTrue(entity.getTitle() != null);
                    }
            )
        );
        assertTrue(entityCount.get() > 0);
    }
    private HttpStatus getHttpStatusByPostForEntity(String url, Object object) {

        try {
            restTemplate.postForEntity(url,object,String.class);
        }catch(HttpClientErrorException ex) {
            return ex.getStatusCode();
        }

        return null;
    }
    private HttpStatus getHttpStatusByGetForEntity(String url) {

        try {
            restTemplate.getForEntity(url,String.class);
        }catch(HttpClientErrorException ex) {
            return ex.getStatusCode();
        }

        return null;
    }
    private void boardDelete(Board board) {
        restTemplate.delete(testAbsUrl+"/board/delete/"+board.getBoardId());

        HttpStatus viewStatus = getHttpStatusByGetForEntity(testAbsUrl+"/board/view/"+board.getBoardId());

        assertEquals(HttpStatus.NOT_FOUND, viewStatus);
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
