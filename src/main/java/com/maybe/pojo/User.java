package com.maybe.pojo;

public class User {
    /**
     * 唯一序号
     */
    private String id;

    /**
     * 名字
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 角色
     */
    private String role;

    /**
     * 介绍
     */
    private String introduction;

    /**
     * avatar
     */
    private String avatar;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 唯一序号
     * @return id 唯一序号
     */
    public String getId() {
        return id;
    }

    /**
     * 唯一序号
     * @param id 唯一序号
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 名字
     * @return name 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 名字
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 密码
     * @return password 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 角色
     * @return role 角色
     */
    public String getRole() {
        return role;
    }

    /**
     * 角色
     * @param role 角色
     */
    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    /**
     * 介绍
     * @return introduction 介绍
     */
    public String getIntroduction() {
        return introduction;
    }

    /**
     * 介绍
     * @param introduction 介绍
     */
    public void setIntroduction(String introduction) {
        this.introduction = introduction == null ? null : introduction.trim();
    }

    /**
     * avatar
     * @return avatar avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * avatar
     * @param avatar avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 备注
     * @return remarks 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}