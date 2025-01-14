package com.px.sd.listener;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.px.common.core.domain.model.LoginUser;
import com.px.common.excel.ExcelListener;
import com.px.common.excel.ExcelResult;
import com.px.common.exception.ServiceException;
import com.px.common.helper.LoginHelper;
import com.px.common.utils.ValidatorUtils;
import com.px.common.utils.spring.SpringUtils;
import com.px.sd.common.RoleUtils;
import com.px.sd.domain.SdDevice;
import com.px.sd.domain.SdLab;
import com.px.sd.domain.vo.SdDeviceImportVo;
import com.px.sd.service.ISdDeviceService;
import com.px.sd.service.ISdLabService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class SdDeviceImportListener extends AnalysisEventListener<SdDeviceImportVo> implements ExcelListener<SdDeviceImportVo> {

    private final Boolean isUpdateSupport;
    private int successNum = 0;
    private int failureNum = 0;
    private final StringBuilder successMsg = new StringBuilder();
    private final StringBuilder failureMsg = new StringBuilder();
    private final LoginUser loginUser;
    private final Boolean isAdmin;
    private final ISdDeviceService sdDeviceService;
    private final ISdLabService sdLabService;
    public SdDeviceImportListener(Boolean isUpdateSupport) {
        this.isUpdateSupport = isUpdateSupport;
        this.sdDeviceService = SpringUtils.getBean(ISdDeviceService.class);
        this.sdLabService = SpringUtils.getBean(ISdLabService.class);
        this.loginUser = LoginHelper.getLoginUser();
        this.isAdmin = RoleUtils.checkRoles(loginUser, RoleUtils.admin);
    }

    @Override
    public ExcelResult<SdDeviceImportVo> getExcelResult() {
        return new ExcelResult<SdDeviceImportVo>() {

            @Override
            public String getAnalysis() {
                if (failureNum > 0) {
                    failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
                    throw new ServiceException(failureMsg.toString());
                } else {
                    successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
                }
                return successMsg.toString();
            }

            @Override
            public List<SdDeviceImportVo> getList() {
                return null;
            }

            @Override
            public List<String> getErrorList() {
                return null;
            }
        };
    }

    @Override
    public void invoke(SdDeviceImportVo data, AnalysisContext context) {
        SdDevice device = new SdDevice();
        try {
            SdLab sdLab = sdLabService.selectByLabCode(data.getLabCode());
            //查找实验室是否存在
            if (sdLab != null){
                // 判断是否为管理员 || 校验当前用户是否可以对实验室导入设备权限；
                if (isAdmin || loginUser.getUserId().equals(sdLab.getUserId())){
                    //校验设备是否存在；
                    SdDevice sdDevice = sdDeviceService.selectByDeviceCode(data.getDeviceCode());
                    if (sdDevice == null){
                        ValidatorUtils.validate(device);
                        device = BeanUtil.toBean(data, SdDevice.class);
                        device.setLabId(sdLab.getId());
                        sdDeviceService.insertSdDevice(device);
                        successNum++;
                        successMsg.append("<br/>").append(successNum).append("、设备编码 ").append(data.getDeviceCode()).append(" 导入成功");
                    }else if(isUpdateSupport){
                        ValidatorUtils.validate(device);
                        device = BeanUtil.toBean(data, SdDevice.class);
                        device.setLabId(sdLab.getId());
                        device.setId(sdDevice.getId());
                        sdDeviceService.updateSdDevice(device);
                        successNum++;
                        successMsg.append("<br/>").append(successNum).append("、设备编码 ").append(data.getDeviceCode()).append(" 更新成功");
                    }else {
                        failureNum++;
                        failureMsg.append("<br/>").append(failureNum).append("、设备编码 ").append(data.getDeviceCode()).append(" 已存在");
                    }
                }else {
                    failureNum++;
                    failureMsg.append("<br/>").append(failureNum).append("、实验室编码 ").append(data.getLabCode()).append(" 您无权限操作");
                }
            }else {
                failureNum++;
                failureMsg.append("<br/>").append(failureNum).append("、实验室编码 ").append(data.getLabCode()).append(" 不存在");
            }
        }catch (Exception e){
            failureNum++;
            String msg = "<br/>" + failureNum + "、设备编码 " + data.getDeviceCode() + " 导入失败：";
            failureMsg.append(msg).append(e.getMessage());
            log.error(msg, e);
        }

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
