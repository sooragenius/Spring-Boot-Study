package kr.co.sooragenius.study.board.web;

import kr.co.sooragenius.study.board.service.Board;
import kr.co.sooragenius.study.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

        return ResponseEntity.notFound().build();
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public Object add(@Valid @RequestBody Board board, Errors errors) {
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        boardService.save(board);

        return board;
    }
    @RequestMapping(value = "/delete/{boardId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> delete(@PathVariable Integer boardId) throws Exception {
        boardService.updateDeleteYByBoardId(boardId);

        return ResponseEntity.ok().build();
    }
}
