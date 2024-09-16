package com.hjc.hjcAPI.model.dto.interfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * 调用请求
 *
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */

    private Long id;



    /**
     * 请求参数
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;



}