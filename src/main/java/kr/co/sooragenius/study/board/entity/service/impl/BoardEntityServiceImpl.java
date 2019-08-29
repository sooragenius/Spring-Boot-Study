package kr.co.sooragenius.study.board.entity.service.impl;

import kr.co.sooragenius.study.board.entity.service.BoardEntity;
import kr.co.sooragenius.study.board.entity.service.BoardEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(rollbackFor = Exception.class)
@Service
public class BoardEntityServiceImpl implements BoardEntityService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Override
    public List<BoardEntity> findAll() {
        return boardEntityRepository.findAll();
    }
}
