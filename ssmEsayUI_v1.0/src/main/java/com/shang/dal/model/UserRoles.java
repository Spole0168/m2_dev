package com.shang.dal.model;

import java.io.Serializable;
import java.util.Date;

public class UserRoles implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * VARCHAR(40) 必填<br>
     * 主键
     */
    private String id;

    /**
     * VARCHAR(40)<br>
     * 用户登录名
     */
    private String userId;

    /**
     * VARCHAR(40)<br>
     * 角色id
     */
    private String roleId;

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
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