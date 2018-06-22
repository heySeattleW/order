package com.hey.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by heer on 2018/6/20.
 */
public class BaseEntity implements Serializable {

    public Long id;

    public Timestamp update_time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Timestamp update_time) {
        this.update_time = update_time;
    }
}
