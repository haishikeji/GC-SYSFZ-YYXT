package com.px.sd.service;

import com.px.sd.domain.SdTimeIntervals;
import com.px.sd.domain.vo.SdTimeIntervalsVo;
import com.px.sd.domain.bo.SdTimeIntervalsBo;
import com.px.common.core.page.TableDataInfo;
import com.px.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 预约时段管理Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface ISdTimeIntervalsService {

    /**
     * 查询预约时段管理
     */
    SdTimeIntervalsVo queryById(Long id);

    /**
     * 查询预约时段管理列表
     */
    TableDataInfo<SdTimeIntervalsVo> queryPageList(SdTimeIntervalsBo bo, PageQuery pageQuery);

    /**
     * 查询预约时段管理列表
     */
    List<SdTimeIntervalsVo> queryList(SdTimeIntervalsBo bo);

    /**
     * 新增预约时段管理
     */
    Boolean insertByBo(SdTimeIntervalsBo bo) throws Exception;

    /**
     * 修改预约时段管理
     */
    Boolean updateByBo(SdTimeIntervalsBo bo) throws Exception;

    /**
     * 校验并批量删除预约时段管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 分页查询预约时段管理列表
     * */
    TableDataInfo<SdTimeIntervals> selectPageSdTimeIntervalsList(SdTimeIntervalsBo bo, PageQuery pageQuery);


    /**
     * 根据实验室获取所有时段
     * */
    List<SdTimeIntervals> getLabTimeIntervals(Long labId);

    /**
     * 获取可预约的时段
     * */
    List<SdTimeIntervals> getTimeIntervals(Map<String, Object> params);

}
