package com.hjc.hjcAPI.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hjc.hjccommon.model.entity.UserInterfaceInfo;


/**
* @author hou-jch
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
* @createDate 2024-09-11 19:52:44
*/
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    void validUserInterfaceInfo(UserInterfaceInfo usernterfaceInfo, boolean add);


    boolean invokeCount(long interfaceInfoId, long userId);
}
