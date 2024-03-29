package kr.co.sooragenius.study.board.service;

import kr.co.sooragenius.study.board.entity.service.BoardEntity;
import kr.co.sooragenius.study.common.service.CommonModel;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Setter @Getter @ToString @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Board extends CommonModel {
    @Id @GeneratedValue
    private Integer boardId;
    @NotEmpty
    @Column
    private String boardName;
    @Min(0) @Max(100)
    @Column
    private Integer entityPageUnitSize;
    @NotEmpty
    @Column
    private String deleteYn;
    @NotEmpty
    @Column
    private String testYn;
    @OneToMany()
    @JoinColumn(name = "BOARD_ID")
    private List<BoardEntity> entities;
}
