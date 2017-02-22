package com.pengpeng.android.client.mvp.model;

/**
 * @author zhanghongfei
 * @version V:2.0.0
 * @Title TestBean
 * @Package com.pengpeng.android.client.mvp.model
 * @Description:
 * @date 2017/2/22 11:58
 */

public class TestBean {
    private String id;
    private String name;
    private String email;
    private int deptId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }
}
