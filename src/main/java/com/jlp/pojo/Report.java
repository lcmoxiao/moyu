package com.jlp.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class Report {
    private Integer rid;

    private Date rtime;

    private String rreason;

    private Integer rsid;

}