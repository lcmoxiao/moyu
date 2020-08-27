package com.jlp.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Movie {
    private Integer mid;

    private Date mpublishtime;

    private String mpubliship;

    private String mpublishname;

    private String mcontent;

    private Integer mfatherid;

    private Integer mgreat;


}