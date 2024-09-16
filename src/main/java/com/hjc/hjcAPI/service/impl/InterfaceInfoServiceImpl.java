package com.hjc.hjcAPI.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.hjc.hjcAPI.common.ErrorCode;
import com.hjc.hjcAPI.exception.BusinessException;
import com.hjc.hjcAPI.exception.ThrowUtils;
import com.hjc.hjcAPI.mapper.InterfaceInfoMapper;
import com.hjc.hjcAPI.model.entity.InterfaceInfo;
import com.hjc.hjcAPI.model.entity.InterfaceInfo;
import com.hjc.hjcAPI.model.entity.User;
import com.hjc.hjcAPI.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
* @author hou-jch
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-07-28 17:27:02
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {


    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
     Long id = interfaceInfo.getId();
     String description = interfaceInfo.getDescription();
     String url = interfaceInfo.getUrl();
     String requestHeader = interfaceInfo.getRequestHeader();
     String responseHeader = interfaceInfo.getResponseHeader();
     Integer status = interfaceInfo.getStatus();
     String method = interfaceInfo.getMethod();
     Long userId = interfaceInfo.getUserId();
     Date createTime = interfaceInfo.getCreateTime();
     Date updateTime = interfaceInfo.getUpdateTime();
     Integer isDelete = interfaceInfo.getIsDelete();

        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        // 创建时，参数不能为空
        if (add) {
            // 有参数则校验
            if (StringUtils.isEmpty(name) ) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }

        if (StringUtils.isNotBlank(name) && name.length() > 50) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名字过长");
        }
    }

}




