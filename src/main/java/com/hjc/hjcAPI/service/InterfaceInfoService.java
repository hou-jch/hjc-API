package com.hjc.hjcAPI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.hjcAPI.model.entity.InterfaceInfo;


/**
* @author hou-jch
* @description 针对表【interface_info(接口信息)】的数据库操作Service
* @createDate 2024-07-28 17:27:02
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);
}
