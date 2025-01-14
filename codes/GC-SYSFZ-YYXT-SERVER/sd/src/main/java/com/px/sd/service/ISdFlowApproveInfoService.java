package com.px.sd.service;

import com.px.sd.domain.*;
import com.px.sd.domain.vo.SdFlowApproveInfoVo;
import com.px.sd.domain.bo.SdFlowApproveInfoBo;
import com.px.common.core.page.TableDataInfo;
import com.px.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 审批记录信息Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface ISdFlowApproveInfoService {

    /**
     * 查询审批记录信息
     */
    SdFlowApproveInfoVo queryById(Long id);

    /**
     * 查询审批记录信息列表
     */
    TableDataInfo<SdFlowApproveInfoVo> queryPageList(SdFlowApproveInfoBo bo, PageQuery pageQuery);

    /**
     * 查询审批记录信息列表
     */
    List<SdFlowApproveInfoVo> queryList(SdFlowApproveInfoBo bo);

    /**
     * 新增审批记录信息
     */
    Boolean insertByBo(SdFlowApproveInfoBo bo);

    /**
     * 修改审批记录信息
     */
    Boolean updateByBo(SdFlowApproveInfoBo bo);

    /**
     * 校验并批量删除审批记录信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 实验室预约申请登记信息
     * 转换生成Sd对象数据
     * */
    SdReInfo convertSdReInfo(SdReservation rv);

    /**
     * 设备借用申请登记信息
     * 转换生成SdBoInfo对象数据
     * */
    SdBoInfo convertSdBoInfo(SdDeviceBorrow db);

    /**
     * 节点审批通过，修改相关数据
     * */
    SdCommonFlowInfo approveOk(SdCommonFlowInfo sdCommonFlowInfo);
    /**
     * 节点审批不通过，修改相关数据
     * */
    SdCommonFlowInfo approveNo(SdCommonFlowInfo sdCommonFlowInfo);
}
