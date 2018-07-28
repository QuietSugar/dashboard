package com.maybe.pojo;

public class CommandDemo {
    /**
     * 唯一ID
     */
    private String id;

    /**
     * 命令表的ID
     */
    private String commandId;

    /**
     * 内容：demo的详细说明
     */
    private String content;

    /**
     * 备注
     */
    private String remarks;

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
     * 命令表的ID
     * @return command_id 命令表的ID
     */
    public String getCommandId() {
        return commandId;
    }

    /**
     * 命令表的ID
     * @param commandId 命令表的ID
     */
    public void setCommandId(String commandId) {
        this.commandId = commandId == null ? null : commandId.trim();
    }

    /**
     * 内容：demo的详细说明
     * @return content 内容：demo的详细说明
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容：demo的详细说明
     * @param content 内容：demo的详细说明
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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