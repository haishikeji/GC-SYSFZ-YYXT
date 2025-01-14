package com.px.sd.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.px.common.utils.StringUtils;
import com.px.common.core.page.TableDataInfo;
import com.px.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.px.sd.domain.bo.SdFlowApproveBo;
import com.px.sd.domain.vo.SdFlowApproveVo;
import com.px.sd.domain.SdFlowApprove;
import com.px.sd.mapper.SdFlowApproveMapper;
import com.px.sd.service.ISdFlowApproveService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 流程审批信息Service业务层处理
 *
 * @author 品讯科技
 * @date 2024-08
 */
@RequiredArgsConstructor
@Service
public class SdFlowApproveServiceImpl implements ISdFlowApproveService {

    private final SdFlowApproveMapper baseMapper;

    /**
     * 查询流程审批信息
     */
    @Override
    public SdFlowApproveVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询流程审批信息列表
     */
    @Override
    public TableDataInfo<SdFlowApproveVo> queryPageList(SdFlowApproveBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SdFlowApprove> lqw = buildQueryWrapper(bo);
        Page<SdFlowApproveVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 分页查询流程审批信息列表
     * */
    @Override
    public TableDataInfo<SdFlowApproveVo> selectPageSdFlowApproveList(SdFlowApproveBo bo, PageQuery pageQuery) {
        QueryWrapper<SdFlowApproveVo> wrapper = Wrappers.query();
        wrapper.like(StringUtils.isNotBlank(bo.getAppUserName()), "u.nick_name", bo.getAppUserName());
        Page<SdFlowApproveVo> result = baseMapper.selectPageSdFlowApproveList(pageQuery.build(), wrapper);
        return TableDataInfo.build(result);
    }

    /**
     * 查询流程审批信息列表
     */
    @Override
    public List<SdFlowApproveVo> queryList(SdFlowApproveBo bo) {
        LambdaQueryWrapper<SdFlowApprove> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询流程审批信息列表
     */
    @Override
    public List<SdFlowApproveVo> selectSdFlowApproveList(SdFlowApproveBo bo) {
        QueryWrapper<SdFlowApproveVo> wrapper = Wrappers.query();
        wrapper.like(StringUtils.isNotBlank(bo.getAppUserName()), "u.nick_name", bo.getAppUserName());
        return baseMapper.selectPageSdFlowApproveList(wrapper);
    }

    private LambdaQueryWrapper<SdFlowApprove> buildQueryWrapper(SdFlowApproveBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SdFlowApprove> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppApplyForType()), SdFlowApprove::getAppApplyForType, bo.getAppApplyForType());
        lqw.eq(StringUtils.isNotBlank(bo.getAppStatus()), SdFlowApprove::getAppStatus, bo.getAppStatus());
        lqw.eq(bo.getAppSort() != null, SdFlowApprove::getAppSort, bo.getAppSort());
        return lqw;
    }

    /**
     * 新增流程审批信息
     */
    @Override
    public Boolean insertByBo(SdFlowApproveBo bo) {
        SdFlowApprove add = BeanUtil.toBean(bo, SdFlowApprove.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改流程审批信息
     */
    @Override
    public Boolean updateByBo(SdFlowApproveBo bo) {
        SdFlowApprove update = BeanUtil.toBean(bo, SdFlowApprove.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SdFlowApprove entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除流程审批信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 根据id查询流程审批信息
     * */
    @Override
    public SdFlowApprove getSdFlowApproveInfo(Long id) {
        return baseMapper.getSdFlowApproveInfo(id);
    }

    @Override
    public List<Long> getSdFlowApproveIds(Long appUserId) {
        return baseMapper.getSdFlowApproveIds(appUserId);
    }
}
