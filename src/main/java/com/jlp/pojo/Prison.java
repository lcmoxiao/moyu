package com.jlp.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class Prison {
    private Integer pid;

    private String pip;

    private Date pstarttime;

    private Integer psid;

    private String preason;

}