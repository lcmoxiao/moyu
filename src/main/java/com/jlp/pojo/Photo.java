package com.jlp.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class Photo {
    private Integer pid;

    private Date ppublishtime;

    private String ppubliship;

    private String ppublishname;

    private String pcontent;

    private Integer pfatherid;

    private String pgreat;

}