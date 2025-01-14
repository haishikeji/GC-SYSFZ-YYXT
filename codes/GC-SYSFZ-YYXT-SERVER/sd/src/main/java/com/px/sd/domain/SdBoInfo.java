package com.px.sd.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import com.px.common.core.domain.BaseEntity;

/**
 * 设备借用登记信息对象 sd_bo_info
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sd_bo_info")
public class SdBoInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 设备借用申请单id
     */
    private Long boBorrowId;
    /**
     * 作业名称
     * */
    private String boJobName;
    /**
     * 申请人id
     */
    private Long userId;
    /**
     * 申请人
     */
    private String userName;
    /**
     * 申请时间
     */
    private Date boApplyForTime;
    /**
     * 借用实验室id
     */
    private Long labId;
    /**
     * 借用实验室
     */
    private String labName;
    /**
     * 借用设备id
     */
    private Long deviceId;
    /**
     * 借用设备
     */
    private String deviceName;
    /**
     * 借用开始时间
     */
    private Date boStartTime;
    /**
     * 借用结束时间
     */
    private Date boEndTime;

    /**
     * 归还状态
     */
    private String boReturnType;
    /**
     * 备注
     */
    private String reNotes;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
