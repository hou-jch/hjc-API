package com.hjc.hjcAPI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.hjcAPI.model.dto.interfaceInfo.InterfaceInfoInvokeRequest;
import com.hjc.hjccommon.model.entity.InterfaceInfo;
import com.hjc.hjccommon.model.entity.User;

import javax.servlet.http.HttpServletRequest;


/**
* @author hou-jch
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-07-28 17:27:02
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    Object invoke(InterfaceInfoInvokeRequest interfaceInfoInvokeRequest, HttpServletRequest request);


}
