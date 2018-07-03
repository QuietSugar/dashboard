package com.maybe.pojo;

public class Task {
    /**
     * 唯一ID
     */
    private String id;

    /**
     * 相关URL
     */
    private String url;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 唯一ID
     *
     * @return id 唯一ID
     */
    public String getId() {
        return id;
    }

    /**
     * 唯一ID
     *
     * @param id 唯一ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 相关URL
     *
     * @return url 相关URL
     */
    public String getUrl() {
        return url;
    }

    /**
     * 相关URL
     *
     * @param url 相关URL
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * 标题
     *
     * @return title 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 内容
     *
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     *
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 备注
     *
     * @return remarks 备注
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * 备注
     *
     * @param remarks 备注
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }
}