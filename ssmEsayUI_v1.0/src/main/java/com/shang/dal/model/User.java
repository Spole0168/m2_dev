package com.shang.dal.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * VARCHAR(40) 必填<br>
     * 主键
     */
    private String id;

    /**
     * VARCHAR(50)<br>
     * 用户登录名 唯一性
     */
    private String username;

    /**
     * VARCHAR(500)<br>
     * 用户密码
     */
    private String password;

    /**
     * VARCHAR(15)<br>
     * 手机号  唯一性
     */
    private String tel;

    /**
     * VARCHAR(60)<br>
     * 用户邮箱  唯一性
     */
    private String email;

    /**
     * VARCHAR(50)<br>
     * 用户名
     */
    private String name;

    /**
     * CHAR(1) 默认值[0]<br>
     * 是否有效 0-有效，1-删除 
     */
    private String isValid;

    /**
     * VARCHAR(20)<br>
     * 创建者
     */
    private String creator;

    /**
     * TIMESTAMP(19) 默认值[CURRENT_TIMESTAMP] 必填<br>
     * 创建时间
     */
    private Date createTime;

    /**
     * VARCHAR(20)<br>
     * 最后修改者
     */
    private String updator;

    /**
     * TIMESTAMP(19)<br>
     * 最后修改时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid == null ? null : isValid.trim();
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator == null ? null : updator.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}