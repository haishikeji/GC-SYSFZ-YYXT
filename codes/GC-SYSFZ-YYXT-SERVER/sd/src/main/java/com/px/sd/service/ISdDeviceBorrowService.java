package com.px.sd.service;

import com.px.sd.domain.*;
import com.px.sd.domain.vo.SdDeviceBorrowVo;
import com.px.sd.domain.bo.SdDeviceBorrowBo;
import com.px.common.core.page.TableDataInfo;
import com.px.common.core.domain.PageQuery;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;

/**
 * 设备借用申请Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface ISdDeviceBorrowService {

    /**
     * 查询设备借用申请
     */
    SdDeviceBorrowVo queryById(Long id);

    /**
     * 查询设备借用申请列表
     */
    TableDataInfo<SdDeviceBorrowVo> queryPageList(SdDeviceBorrowBo bo, PageQuery pageQuery);

    /**
     * 查询设备借用申请列表
     */
    List<SdDeviceBorrowVo> queryList(SdDeviceBorrowBo bo);

    /**
     * 新增设备借用申请
     */
    Boolean insertByBo(SdDeviceBorrowBo bo) throws Exception;

    /**
     * 修改设备借用申请
     */
    Boolean updateByBo(SdDeviceBorrowBo bo);

    /**
     * 校验并批量删除设备借用申请信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 解析设备借用申请单，获取所有审批人，生成流程审批信息
     * */
    void analysisGenerateSdFlowApproveInfo(SdDeviceBorrow db) throws Exception;

    /**
     * 转换生成SdCommonFlowInfo对象数据，新增流程信息
     * */
    Boolean addSdCommonFlowInfo(SdDeviceBorrow db);

    /**
     * 取消设备借用申请
     */
    Boolean cancellationDeviceBorrow(Long id);

    /**
     * 获取可借用的设备
     * */
    List<SdDevice> getSdDevices(Long labId, String startDateStr, String endDateStr, Object deviceName) throws ParseException;

}
