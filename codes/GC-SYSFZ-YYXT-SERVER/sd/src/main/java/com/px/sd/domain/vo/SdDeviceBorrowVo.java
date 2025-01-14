package com.px.sd.domain.vo;

import java.util.Date;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.px.common.annotation.ExcelDictFormat;
import com.px.common.convert.ExcelDictConvert;
import lombok.Data;



/**
 * 设备借用申请视图对象 sd_device_borrow
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Data
@ExcelIgnoreUnannotated
public class SdDeviceBorrowVo {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 作业名称
     * */
    @ExcelProperty(value = "作业名称")
    private String borJobName;

    /**
     * 申请人id
     */
    private Long borApplicantId;

    /**
     * 申请人
     */
    @ExcelProperty(value = "申请人")
    private String borApplicant;

    /**
     * 班级
     */
    @ExcelProperty(value = "班级")
    private String borClass;

    /**
     * 专业
     */
    @ExcelProperty(value = "专业")
    private String borSpeciality;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String borPhone;

    /**
     * 借用开始时间
     */
    @ExcelProperty(value = "借用开始时间")
    private Date borStartTime;

    /**
     * 借用结束时间
     */
    @ExcelProperty(value = "借用结束时间")
    private Date borEndTime;

    /**
     * 借用实验室id
     */
    private Long labId;

    /**
     * 借用实验室
     */
    @ExcelProperty(value = "借用实验室")
    private String labName;

    /**
     * 借用设备id
     */
    private Long deviceId;

    /**
     * 借用设备
     */
    @ExcelProperty(value = "借用设备")
    private String deviceName;

    /**
     * 是否为贵重设备
     */
    @ExcelProperty(value = "是否为贵重设备", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sd_common_status")
    private String devicePrecious;

    /**
     * 辅导教师
     */
    @ExcelProperty(value = "辅导教师")
    private String borTutor;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String borNotes;

    /**
     * 流程状态
     */
    @ExcelProperty(value = "流程状态",converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sd_process_status")
    private String borStatus;

    private Date createTime;

}
