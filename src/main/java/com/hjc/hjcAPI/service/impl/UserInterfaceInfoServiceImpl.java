package com.hjc.hjcAPI.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hjc.hjcAPI.common.ErrorCode;
import com.hjc.hjcAPI.exception.BusinessException;
import com.hjc.hjcAPI.mapper.UserInterfaceInfoMapper;
import com.hjc.hjcAPI.model.entity.InterfaceInfo;
import com.hjc.hjcAPI.model.entity.UserInterfaceInfo;
import com.hjc.hjcAPI.service.UserInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
* @author hou-jch
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-09-11 19:52:44
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userinterfaceInfo, boolean add) {

        if (userinterfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // 创建时，参数不能为空
        if (add) {
            // 有参数则校验
            if (userinterfaceInfo.getInterfaceInfoId()<=0 && userinterfaceInfo.getUserId()<=0) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口或用户   不存在");
            }
        }

        if (userinterfaceInfo.getLeftNum() < 0 ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "剩余调用次数不能小于0");
        }
    }

    /**
     * 调用接口统计
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceInfoId, long userId) {
        if (interfaceInfoId <= 0 || userId <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        UpdateWrapper<UserInterfaceInfo> interfaceInfoUpdateWrapper = new UpdateWrapper<>();
        interfaceInfoUpdateWrapper.eq("interfaceInfoId",interfaceInfoId);
        interfaceInfoUpdateWrapper.eq("userId",userId);
        interfaceInfoUpdateWrapper.setSql("leftNum = leftNum - 1,totalNum = totalNum + 1");
        return this.update(interfaceInfoUpdateWrapper);
    }
}




