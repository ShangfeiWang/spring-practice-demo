package com.wsf.alipay.controller.vo;

import lombok.Data;

import java.util.List;

@Data
public class RefundOrderRequestVO {

    /**
     * 商户的操作员编号
     */
    private String operatorId;

    /**
     * 商户订单号。
     * 订单支付时传入的商户订单号，商家自定义且保证商家系统中唯一。与支付宝交易号 trade_no 不能同时为空。
     */
    private String outTradeNo;

    /**
     * 查询选项。
     * 商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式。支持：refund_detail_item_list：退款使用的资金渠道；deposit_back_info：触发银行卡冲退信息通知；
     */
    private List<String> queryOptions;

    /**
     * 退款金额。
     * 需要退款的金额，该金额不能大于订单金额，单位为元，支持两位小数。
     * 注：如果正向交易使用了营销，该退款金额包含营销金额，支付宝会按业务规则分配营销和买家自有资金分别退多少，默认优先退买家的自有资金。如交易总金额100元，用户支付时使用了80元自有资金和20元无资金流的营销券，商家实际收款80元。如果首次请求退款60元，则60元全部从商家收款资金扣除退回给用户自有资产；如果再请求退款40元，则从商家收款资金扣除20元退回用户资产以及把20元的营销券退回给用户（券是否可再使用取决于券的规则配置）。
     */
    private String refundAmount;


    /**
     * 退款原因说明。
     * 商家自定义，将在会在商户和用户的pc退款账单详情中展示
     */
    private String refundReason;


    /**
     * 支付宝交易号。
     * 和商户订单号 out_trade_no 不能同时为空。
     */
    private String tradeNo;

}
