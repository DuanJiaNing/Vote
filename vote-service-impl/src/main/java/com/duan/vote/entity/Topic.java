package com.duan.vote.entity;


import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created on 2019/10/25.
 *
 * @author DuanJiaNing
 */
@Data
public class Topic implements Serializable {

    private static final long serialVersionUID = -256865085363011189L;
    private Integer id;
    private Integer userId;
    private String title;
    private String notes;
    private Timestamp insertTime;
}
