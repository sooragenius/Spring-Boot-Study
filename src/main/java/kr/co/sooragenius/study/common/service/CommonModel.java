package kr.co.sooragenius.study.common.service;

import lombok.*;

import javax.persistence.Column;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@ToString
public class CommonModel {
    // 최초 생성자
    @Column
    private String frstRegisterId;
    // 최초 생성일
    @Column
    private String frstRegisterPnttm;
    // 마지막 수정 아이디
    @Column
    private String lastUpdusrId;
    // 마지막 수정 시점
    @Column
    private String lastUpdusrPnttm;


}
