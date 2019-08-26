package kr.co.sooragenius.study.board.model;

import kr.co.sooragenius.study.common.service.CommonModel;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Board extends CommonModel {
    @Id @Column
    private int boardId;
    @Column
    private String boardName;
    @Column
    private String deleteYn;
}
