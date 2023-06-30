package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * @description:
 * @author: zrs
 * @createDate: 2023/6/6 17:22
 */
public class TestVo {
    /**
     * 时间
     */
    @LogParam(title = "运行记事时间")
    @ApiModelProperty(value = "运行记事时间", required = false )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date runRecordTime;

    /**
     * 内容
     */
    @LogParam(title = "内容")
    @ApiModelProperty(value = "内容", required = false )
    private String content;

    /**
     * 巡视区域
     */
    @LogParam(title = "巡视区域")
    @ApiModelProperty(value = "巡视区域", required = false )
    private String patrolArea;

    /**
     * 工作票情况时间
     */
    @LogParam(title = "工作票情况时间")
    @ApiModelProperty(value = "工作票情况时间", required = false )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date workTicketTime;

    /**
     * 工作票编号
     */
    @LogParam(title = "工作票编号")
    @ApiModelProperty(value = "工作票编号", required = false )
    private String workTicketCode;

    /**
     * 工作票内容
     */
    @LogParam(title = "工作票内容")
    @ApiModelProperty(value = "工作票内容", required = false )
    private String workTicketContent;

    /**
     * 巡视结果
     */
    @LogParam(title = "巡视结果")
    @ApiModelProperty(value = "巡视结果", required = false )
    private String inspectionResults;

    /**
     * 巡查人
     */
    @LogParam(title = "巡查人")
    @ApiModelProperty(value = "巡查人", required = false )
    private String patrolUseid;

    /**
     * 主表id
     */
    @LogParam(title = "主表id")
    @ApiModelProperty(value = "主表id", required = false )
    private Integer powerSupplyId;

    /**
     * 类型 1运行记事2巡检记录3工作票情况
     */
    @LogParam(title = "类型 1运行记事2巡检记录3工作票情况")
    @ApiModelProperty(value = "类型 1运行记事2巡检记录3工作票情况", required = false )
    private Integer type;
}
