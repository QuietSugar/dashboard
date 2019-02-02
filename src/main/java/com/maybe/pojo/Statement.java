package com.maybe.pojo;

public class Statement {
    /**
     * 唯一ID
     */
    private String id;

    /**
     * 内容
     */
    private String content;

    /**
     * 的详细说明
     */
    private String description;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 
     */
    private String commandId;

    /**
     * 热度
     */
    private Integer hot;

    /**
     * 唯一ID
     * @return id 唯一ID
     */
    public String getId() {
        return id;
    }

    /**
     * 唯一ID
     * @param id 唯一ID
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 内容
     * @return content 内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 的详细说明
     * @return description 的详细说明
     */
    public String getDescription() {
        return description;
    }

    /**
     * 的详细说明
     * @param description 的详细说明
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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

    /**
     * 
     * @return command_id 
     */
    public String getCommandId() {
        return commandId;
    }

    /**
     * 
     * @param commandId 
     */
    public void setCommandId(String commandId) {
        this.commandId = commandId == null ? null : commandId.trim();
    }

    /**
     * 热度
     * @return hot 热度
     */
    public Integer getHot() {
        return hot;
    }

    /**
     * 热度
     * @param hot 热度
     */
    public void setHot(Integer hot) {
        this.hot = hot;
    }
}
