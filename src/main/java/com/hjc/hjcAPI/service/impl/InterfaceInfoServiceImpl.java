package com.hjc.hjcAPI.service.impl;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hjc.hjcAPI.common.ErrorCode;
import com.hjc.hjcAPI.config.GatewayConfig;
import com.hjc.hjcAPI.exception.BusinessException;
import com.hjc.hjcAPI.exception.ThrowUtils;
import com.hjc.hjcAPI.mapper.InterfaceInfoMapper;
import com.hjc.hjcAPI.model.dto.interfaceInfo.InterfaceInfoInvokeRequest;
import com.hjc.hjcAPI.model.enums.InterfaceInfostatusEnum;
import com.hjc.hjcAPI.service.UserService;
import com.hjc.hjccommon.model.entity.InterfaceInfo;
import com.hjc.hjcAPI.service.InterfaceInfoService;
import com.hjc.hjccommon.model.entity.User;
import com.hjc.hjjcclientsdk.client.HouApiClient;
import com.hjc.hjjcclientsdk.util.SignUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author hou-jch
* @description 针对表【interface_info(接口信息)】的数据库操作Service实现
* @createDate 2024-07-28 17:27:02
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {
    @Resource
    private UserService userService;
    @Resource
    private GatewayConfig gatewayConfig;



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

    @Override
    public Object invoke(InterfaceInfoInvokeRequest interfaceInfoInvokeRequest, HttpServletRequest request)  {
        //获取id
        Long id = interfaceInfoInvokeRequest.getId();
        //获取请求参数
        String userRequestParams = interfaceInfoInvokeRequest.getUserRequestParams();
        InterfaceInfo oldInterfaceInfo = getById(id);
        ThrowUtils.throwIf(oldInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        Integer status = oldInterfaceInfo.getStatus();
        if(status == InterfaceInfostatusEnum.OFFONLINE.getValue()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口已下线");
        }
        //使用当前用户登录的密钥去调用接口
        User loginUser = userService.getLoginUser(request);
        //使用当前用户登录的密钥去调用接口
        String accessKey = loginUser.getAccessKey();
        String secretKey = loginUser.getSecretKey();
        String gatewayHost = gatewayConfig.getGatewayConfig();
//        HouApiClient houApiClienByMy = new HouApiClient(secretKey,accessKey,gatewayHost);

        Map<String,Object> paramMap = new HashMap<>();
        String jsonStr = userRequestParams;
        HttpResponse httpResponse = HttpRequest.post(gatewayHost +oldInterfaceInfo.getUrl()).addHeaders(getHeaderMap(jsonStr,accessKey,secretKey)).body(jsonStr).execute();
//        String result = HttpUtil.get(gatewayHost +oldInterfaceInfo.getUrl(), paramMap);
        System.out.println(httpResponse.body());
        return httpResponse.body();
    }
    private Map<String,String> getHeaderMap(String body,String accessKey,String secretKey){
        Map<String,String> paramMap = new HashMap<>();
//        paramMap.put("secretKey",secretKey);
        paramMap.put("accessKey",accessKey);
        paramMap.put("body",body);
        paramMap.put("nonce", RandomUtil.randomNumbers(4));
        paramMap.put("timeStamp",String.valueOf(System.currentTimeMillis()/1000));
        paramMap.put("sign", SignUtils.getSion(body,secretKey));
        return paramMap;
    }


}




