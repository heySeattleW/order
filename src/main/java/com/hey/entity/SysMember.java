package com.hey.entity;

import java.sql.Timestamp;

/**
 * Created by hey on 2018/6/23.
 */
public class SysMember extends BaseEntity {

    private Long sysId;

    private String sysName;

    private String sysPass;

    private int sysPower;

    public Long getSysId() {
        return sysId;
    }

    public void setSysId(Long sysId) {
        this.sysId = sysId;
    }

    public String getSysName() {
        return sysName;
    }

    public void setSysName(String sysName) {
        this.sysName = sysName;
    }

    public String getSysPass() {
        return sysPass;
    }

    public void setSysPass(String sysPass) {
        this.sysPass = sysPass;
    }

    public int getSysPower() {
        return sysPower;
    }

    public void setSysPower(int sysPower) {
        this.sysPower = sysPower;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"sysId\":")
                .append(sysId);
        sb.append(",\"sysName\":\"")
                .append(sysName).append('\"');
        sb.append(",\"sysPass\":\"")
                .append(sysPass).append('\"');
        sb.append(",\"sysPower\":")
                .append(sysPower);
        sb.append('}');
        return sb.toString();
    }

    public SysMember(Long id, String update_time, Long sysId, String sysName, String sysPass, int sysPower) {
        super(id, update_time);
        this.sysId = sysId;
        this.sysName = sysName;
        this.sysPass = sysPass;
        this.sysPower = sysPower;
    }

    public SysMember() {
    }
}
