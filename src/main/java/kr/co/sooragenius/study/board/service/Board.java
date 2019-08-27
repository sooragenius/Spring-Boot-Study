package kr.co.sooragenius.study.board.service;

import kr.co.sooragenius.study.common.service.CommonModel;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter @Getter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Board extends CommonModel {
    @Id @GeneratedValue
    private Integer boardId;
    @Column
    private String boardName;
    @Column
    private Integer entityPageUnitSize;
    @Column
    private String deleteYn;
    @Column
    private String testYn;
}
