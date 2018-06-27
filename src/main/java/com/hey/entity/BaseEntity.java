package com.hey.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by heer on 2018/6/20.
 */
public class BaseEntity implements Serializable {

    public Long id;

    public String update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public BaseEntity() {
    }

    public BaseEntity(Long id, String update_time) {
        this.id = id;
        this.update_time = update_time;
    }
}
