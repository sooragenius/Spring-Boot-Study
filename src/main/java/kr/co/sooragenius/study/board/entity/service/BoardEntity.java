package kr.co.sooragenius.study.board.entity.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import kr.co.sooragenius.study.board.service.Board;
import kr.co.sooragenius.study.common.service.CommonModel;
import lombok.*;

import javax.persistence.*;

@Getter @Setter @ToString @Builder
@NoArgsConstructor @AllArgsConstructor
@Entity
public class BoardEntity extends CommonModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer entityId;
    @Column
    private String title;
    @Column
    private String contents;
    @JsonIgnore
    @JoinColumn(name = "BOARD_ID",referencedColumnName = "boardId")
    @ManyToOne
    private Board board;
}
