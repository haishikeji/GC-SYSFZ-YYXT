package com.px.sd.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.px.common.annotation.Log;
import com.px.common.annotation.RepeatSubmit;
import com.px.common.core.controller.BaseController;
import com.px.common.core.domain.PageQuery;
import com.px.common.core.domain.R;
import com.px.common.core.page.TableDataInfo;
import com.px.common.core.validate.AddGroup;
import com.px.common.core.validate.EditGroup;
import com.px.common.enums.BusinessType;
import com.px.common.utils.poi.ExcelUtil;
import com.px.sd.domain.SdLab;
import com.px.sd.domain.bo.SdLabBo;
import com.px.sd.domain.vo.SdLabVo;
import com.px.sd.service.ISdLabService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 实验室管理
 *
 * @author 品讯科技
 * @date 2024-08
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/sd/sdLab")
public class SdLabController extends BaseController {

    private final ISdLabService iSdLabService;


    /**
     * 查询实验室管理列表
     */
    /*@SaCheckPermission("sd:sdLab:list")
    @GetMapping("/list")
    public TableDataInfo<SdLabVo> list(SdLabBo bo, PageQuery pageQuery) {
        return iSdLabService.queryPageList(bo, pageQuery);
    }*/

    @GetMapping("/test")
    public String test(){
        return "测试";
    }

    @SaCheckPermission("sd:sdLab:list")
    @GetMapping("/list")
    public TableDataInfo<SdLab> list(SdLabBo bo, PageQuery pageQuery) {
        return iSdLabService.selectPageSdLabList(bo, pageQuery);
    }

    /**
     * 导出实验室管理列表
     */
    /*@SaCheckPermission("sd:sdLab:export")
    @Log(title = "实验室管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SdLabBo bo, HttpServletResponse response) {
        List<SdLabVo> list = iSdLabService.queryList(bo);
        ExcelUtil.exportExcel(list, "实验室管理", SdLabVo.class, response);
    }*/

    @SaCheckPermission("sd:sdLab:export")
    @Log(title = "实验室管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SdLabBo bo, HttpServletResponse response) {
        List<SdLabVo> list = iSdLabService.getSdLabInfoVoList(bo);
        ExcelUtil.exportExcel(list, "实验室管理", SdLabVo.class, response);
    }

    /**
     * 获取实验室管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("sd:sdLab:query")
    @GetMapping("/{id}")
    public R<SdLabVo> getInfo(@NotNull(message = "主键不能为空")
                                              @PathVariable Long id) {
        //return R.ok(iSdLabService.queryById(id));
        return R.ok(iSdLabService.getSdLabInfo(id));
    }

    /**
     * 新增实验室管理
     */
    @SaCheckPermission("sd:sdLab:add")
    @Log(title = "实验室管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SdLabBo bo) {
        return toAjax(iSdLabService.insertByBo(bo));
    }

    /**
     * 修改实验室管理
     */
    @SaCheckPermission("sd:sdLab:edit")
    @Log(title = "实验室管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SdLabBo bo) {
        return toAjax(iSdLabService.updateByBo(bo));
    }

    /**
     * 删除实验室管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("sd:sdLab:remove")
    @Log(title = "实验室管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(iSdLabService.deleteWithValidByIds(Arrays.asList(ids), true));
    }

    @GetMapping(value = {"/getSdLabInfoList/{labName}","/getSdLabInfoList"})
    public R<List<Map<String, Object>>> getSdLabInfoList(@PathVariable(required = false) Object labName){
        return R.ok(iSdLabService.getSdLabInfoList(labName));
    }

    @GetMapping(value = {"/selectSdLabInfoList/{labName}","/selectSdLabInfoList"})
    public R<List<SdLab>> selectSdLabInfoList(@PathVariable(required = false) String labName){
        return R.ok(iSdLabService.selectSdLabInfoList(labName));
    }
}
