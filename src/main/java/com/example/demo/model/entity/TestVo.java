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
     * 审批人
     */
    @LogParam(title = "审批人")
    @ApiModelProperty(value = "审批人", required = false )
    private Long singleApprovalUid;

    /**
     * 审批时间
     */
    @LogParam(title = "审批时间")
    @ApiModelProperty(value = "审批时间", required = false )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date approvalTime;

    /**
     * 审批人公司
     */
    @LogParam(title = "审批人公司")
    @ApiModelProperty(value = "审批人公司", required = false )
    private Long approvalDepid;

    /**
     * 审批意见
     */
    @LogParam(title = "审批意见")
    @ApiModelProperty(value = "审批意见", required = false )
    private String approvalOpinion;

    /**
     * 审批是否结束
     */
    @LogParam(title = "审批是否结束")
    @ApiModelProperty(value = "审批是否结束", required = false )
    private String yesOrNoOver;

    /**
     * 下一办理人
     */
    @LogParam(title = "下一办理人")
    @ApiModelProperty(value = "下一办理人", required = false )
    private Long nextProcessedBy;

    /**
     * 是否同意
     */
    @LogParam(title = "是否同意")
    @ApiModelProperty(value = "是否同意", required = false )
    private String agreeOrNot;

    /**
     * 主表id
     */
    @LogParam(title = "主表id")
    @ApiModelProperty(value = "主表id", required = false )
    private Long responseId;
}
