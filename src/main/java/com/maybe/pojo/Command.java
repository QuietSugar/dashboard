package com.maybe.pojo;

import io.swagger.annotations.ApiModelProperty;

public class Command {
    /**
     * 唯一ID
     */
    @ApiModelProperty(value="ID",hidden=true)
    private String id;

    /**
     * 名字
     */
    @ApiModelProperty(value="命令名",example="docker")
    private String name;

    /**
     * 内容：命令的详细说明
     */
    private String content;

    /**
     * 类别：用于区分平台，windows，linux，mac等
     */
    private String type;

    /**
     * 支持的参数
     */
    private String parameter;

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
     * 标题
     * @return name 标题
     */
    public String getName() {
        return name;
    }

    /**
     * 标题
     * @param name 标题
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 内容：命令的详细说明
     * @return content 内容：命令的详细说明
     */
    public String getContent() {
        return content;
    }

    /**
     * 内容：命令的详细说明
     * @param content 内容：命令的详细说明
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * 类别：用于区分平台，windows，linux，mac等
     * @return type 类别：用于区分平台，windows，linux，mac等
     */
    public String getType() {
        return type;
    }

    /**
     * 类别：用于区分平台，windows，linux，mac等
     * @param type 类别：用于区分平台，windows，linux，mac等
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * 支持的参数
     * @return parameter 支持的参数
     */
    public String getParameter() {
        return parameter;
    }

    /**
     * 支持的参数
     * @param parameter 支持的参数
     */
    public void setParameter(String parameter) {
        this.parameter = parameter == null ? null : parameter.trim();
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