package kr.co.sooragenius.study.board.entity;

import kr.co.sooragenius.study.board.entity.service.BoardEntity;
import kr.co.sooragenius.study.board.entity.service.impl.BoardEntityRepository;
import kr.co.sooragenius.study.board.service.Board;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class BoardEntityTests {
    @Autowired
    BoardEntityRepository boardEntityRepository;

    @Test
    public void addTest() {
        String title = "Hello";
        String contents = "This is Content";
        Board board = Board.builder()
                .boardId(1)
                .build();

        BoardEntity boardEntity = BoardEntity.builder()
                .title(title)
                .contents(contents)
                .board(board)
                .build();

        boardEntityRepository.save(boardEntity);

        assertTrue(boardEntity.getEntityId() >= 0);
        log.info(boardEntity.toString());
    }

    @Test
    public void getEntityWithBoard() {
        List<BoardEntity> boardEntities = boardEntityRepository.findAll();

        boardEntities.stream().forEach(item ->
            {
                assertTrue(item.getBoard().getBoardName() != null);
                log.info(item.getBoard().getBoardName());
            }
        );
    }
}
