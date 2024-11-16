package com.hjc.hjcAPI.service.impl.inner;
import com.hjc.hjcAPI.exception.BusinessException;
import com.hjc.hjcAPI.common.ErrorCode;
import com.hjc.hjcAPI.mapper.InterfaceInfoMapper;
import com.hjc.hjccommon.model.entity.InterfaceInfo;
import com.hjc.hjccommon.service.InnerInterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

@DubboService
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;
    @Override
    public InterfaceInfo getInterfaceInfo(String path, String method) {
        // 参数校验
        if (StringUtils.isAnyBlank(path, method)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 创建查询条件包装器
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", path);
        queryWrapper.eq("method", method);

        // 使用 InterfaceInfoMapper 的 selectOne 方法查询接口信息
        return interfaceInfoMapper.selectOne(queryWrapper);
    }
}
