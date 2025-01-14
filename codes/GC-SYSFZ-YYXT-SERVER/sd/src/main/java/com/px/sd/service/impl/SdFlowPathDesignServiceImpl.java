package com.px.sd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.px.common.utils.StringUtils;
import com.px.common.core.page.TableDataInfo;
import com.px.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.px.sd.domain.bo.SdFlowPathDesignBo;
import com.px.sd.domain.vo.SdFlowPathDesignVo;
import com.px.sd.domain.SdFlowPathDesign;
import com.px.sd.mapper.SdFlowPathDesignMapper;
import com.px.sd.service.ISdFlowPathDesignService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 流程设计Service业务层处理
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RequiredArgsConstructor
@Service
public class SdFlowPathDesignServiceImpl implements ISdFlowPathDesignService {

    private final SdFlowPathDesignMapper baseMapper;

    /**
     * 查询流程设计
     */
    @Override
    public SdFlowPathDesignVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询流程设计列表
     */
    @Override
    public TableDataInfo<SdFlowPathDesignVo> queryPageList(SdFlowPathDesignBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SdFlowPathDesign> lqw = buildQueryWrapper(bo);
        Page<SdFlowPathDesignVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询流程设计列表
     */
    @Override
    public List<SdFlowPathDesignVo> queryList(SdFlowPathDesignBo bo) {
        LambdaQueryWrapper<SdFlowPathDesign> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SdFlowPathDesign> buildQueryWrapper(SdFlowPathDesignBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SdFlowPathDesign> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getFlowApplyForId()), SdFlowPathDesign::getFlowApplyForId, bo.getFlowApplyForId());
        lqw.eq(StringUtils.isNotBlank(bo.getFlowDisable()), SdFlowPathDesign::getFlowDisable, bo.getFlowDisable());
        return lqw;
    }

    /**
     * 新增流程设计
     */
    @Override
    public Boolean insertByBo(SdFlowPathDesignBo bo) {
        SdFlowPathDesign add = BeanUtil.toBean(bo, SdFlowPathDesign.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改流程设计
     */
    @Override
    public Boolean updateByBo(SdFlowPathDesignBo bo) {
        SdFlowPathDesign update = BeanUtil.toBean(bo, SdFlowPathDesign.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SdFlowPathDesign entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除流程设计
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
