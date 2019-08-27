package kr.co.sooragenius.study.board.service.web;

import kr.co.sooragenius.study.board.service.Board;
import kr.co.sooragenius.study.board.service.BoardService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardWebController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Board> boardList() {
        return boardService.findAll();
    }


    @RequestMapping(value = "/view/{boardId}", method = RequestMethod.GET)
    public Object view(@PathVariable Integer boardId) throws Exception {
        Board foundBoard = boardService.findByBoardId(boardId);
        if(foundBoard != null) {
            return foundBoard;
        }

        return new ResponseEntity<String>("Not Found Board", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public Board add(@RequestBody Board board) {
        boardService.save(board);

        return board;
    }
    @RequestMapping(value = "/delete/{boardId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer boardId) {
        boardService.updateDeleteYByBoardId(boardId);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
