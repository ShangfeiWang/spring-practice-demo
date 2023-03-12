package com.wsf.alipay.controller.vo;

import lombok.Data;

@Data
public class CloseOrderRequestVO {

    /**
     * 支付宝交易单号  tradeNo和outTradeNo 必须填一个
     */
    private String tradeNo;

    /**
     * 商户侧订单号
     */
    private String outTradeNo;

    /**
     * 操作员id
     */
    private String operatorId;
}
