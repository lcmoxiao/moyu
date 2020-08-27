package com.jlp.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Braggart {

    private Integer bid;

    private Date bpublishtime;

    private String bpubliship;

    private String bpublishname;

    private String bcontent;

    private Integer bfatherid;

    private Integer bgreat;


}