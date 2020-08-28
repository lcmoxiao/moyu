package com.jlp.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Worker {
    private Integer uid;

    private String username;

    private String password;

    private String fakename;

    private Date ordertime;
}