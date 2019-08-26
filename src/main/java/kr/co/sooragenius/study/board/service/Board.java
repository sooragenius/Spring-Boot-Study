package kr.co.sooragenius.study.board.service;

import kr.co.sooragenius.study.common.service.CommonModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Board extends CommonModel {
    @Id @GeneratedValue
    private int boardId;
    @Column
    private String boardName;
    @Column
    private String deleteYn;
}
