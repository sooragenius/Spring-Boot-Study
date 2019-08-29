package kr.co.sooragenius.study.board.entity.service.impl;

import kr.co.sooragenius.study.board.entity.service.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface BoardEntityRepository extends JpaRepository<BoardEntity, Integer> {
}
