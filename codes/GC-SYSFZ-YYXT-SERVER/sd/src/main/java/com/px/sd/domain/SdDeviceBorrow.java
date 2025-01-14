package com.px.sd.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import com.px.common.core.domain.BaseEntity;

/**
 * 设备借用申请对象 sd_device_borrow
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sd_device_borrow")
public class SdDeviceBorrow extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 作业名称
     * */
    private String borJobName;
    /**
     * 申请人
     */
    private Long borApplicantId;
    /**
     * 申请人
     */
    private String borApplicant;
    /**
     * 班级
     */
    private String borClass;
    /**
     * 专业
     */
    private String borSpeciality;
    /**
     * 联系电话
     */
    private String borPhone;
    /**
     * 借用开始时间
     */
    private Date borStartTime;
    /**
     * 借用结束时间
     */
    private Date borEndTime;
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
     * 是否为贵重设备
     */
    private String devicePrecious;
    /**
     * 辅导教师
     */
    private String borTutor;
    /**
     * 备注
     */
    private String borNotes;
    /**
     * 流程状态
     */
    private String borStatus;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
