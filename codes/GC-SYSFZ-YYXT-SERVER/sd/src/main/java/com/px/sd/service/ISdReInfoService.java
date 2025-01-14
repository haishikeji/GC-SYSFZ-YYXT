package com.px.sd.service;

import com.px.sd.domain.vo.SdReInfoVo;
import com.px.sd.domain.bo.SdReInfoBo;
import com.px.common.core.page.TableDataInfo;
import com.px.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 预约登记信息Service接口
 *
 * @author 品讯科技
 * @date 2024-08
 */
public interface ISdReInfoService {

    /**
     * 查询预约登记信息
     */
    SdReInfoVo queryById(Long id);

    /**
     * 查询预约登记信息列表
     */
    TableDataInfo<SdReInfoVo> queryPageList(SdReInfoBo bo, PageQuery pageQuery);

    /**
     * 查询预约登记信息列表
     */
    List<SdReInfoVo> queryList(SdReInfoBo bo);

    /**
     * 新增预约登记信息
     */
    Boolean insertByBo(SdReInfoBo bo);

    /**
     * 修改预约登记信息
     */
    Boolean updateByBo(SdReInfoBo bo);

    /**
     * 校验并批量删除预约登记信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
