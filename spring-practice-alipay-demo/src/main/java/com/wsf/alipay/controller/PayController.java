package com.wsf.alipay.controller;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeCloseModel;
import com.alipay.api.domain.AlipayTradeFastpayRefundQueryModel;
import com.alipay.api.domain.AlipayTradePagePayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.request.AlipayTradeCloseRequest;
import com.alipay.api.request.AlipayTradeFastpayRefundQueryRequest;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradePagePayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.wsf.alipay.controller.vo.CloseOrderRequestVO;
import com.wsf.alipay.controller.vo.RefundOrderRequestVO;
import com.wsf.alipay.utils.OrderIdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class PayController {

    @Autowired
    private AlipayClient alipayClient;

    @RequestMapping("/payOrder")
    public void payOrder(HttpServletResponse httpServletResponse) throws IOException {

        AlipayTradePagePayModel model = new AlipayTradePagePayModel();
        model.setOutTradeNo(OrderIdUtils.generateOrderNo());
        model.setTotalAmount("99.99");
        model.setSubject("手机");

        AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
        //异步接收地址，仅支持http/https，公网可访问
        request.setNotifyUrl("");
        //同步跳转地址，仅支持http/https
        request.setReturnUrl("");
        request.setBizModel(model);

        AlipayTradePagePayResponse response = null;
        try {
            response = alipayClient.pageExecute(request);
            if (response.isSuccess()) {
                log.info("调用成功");
                log.info(JSON.toJSONString(request));
                String form = response.getBody();
                httpServletResponse.setContentType("text/html;charset=utf-8");
                log.info(JSON.toJSONString(response));
                httpServletResponse.getWriter().write(form);
                httpServletResponse.getWriter().flush();
                httpServletResponse.getWriter().close();

            } else {
                log.error("调用失败");
                log.error("错误信息 message:{}", response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * 查询订单的支付结果
     *
     * @param outTradeNo 商户侧订单号
     * @return 订单支付结果
     */
    @ResponseBody
    @RequestMapping("/queryPayResult")
    public String queryPayResult(String outTradeNo) {
        AlipayTradeQueryModel model = new AlipayTradeQueryModel();
        model.setOutTradeNo(outTradeNo);
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizModel(model);

        AlipayTradeQueryResponse response;
        try {
            response = alipayClient.execute(request);
            return JSON.toJSONString(response);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 关闭交易订单
     *
     * @param requestVO 请求参数
     * @return 结果
     */
    @ResponseBody
    @RequestMapping(value = "/closeOrder", method = RequestMethod.POST)
    public String closeOrder(@RequestBody CloseOrderRequestVO requestVO) {
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        AlipayTradeCloseModel model = new AlipayTradeCloseModel();
        model.setOutTradeNo(requestVO.getOutTradeNo());
        model.setOperatorId(requestVO.getOperatorId());
        model.setTradeNo(requestVO.getTradeNo());
        request.setBizModel(model);
        AlipayTradeCloseResponse response;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
                log.info("关闭成功 outTradeNo:{},tradeNo:{},msg:{}", response.getOutTradeNo(), response.getTradeNo(), response.getMsg());

            } else {
                System.out.println("调用失败");
                log.error("错误信息 message:{}", response.getBody());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 统一收单交易退款接口
     *
     * @param requestVO 请求参数
     * @return 结果
     */
    @ResponseBody
    @RequestMapping(value = "/refundOrder", method = RequestMethod.POST)
    public String refundOrder(@RequestBody RefundOrderRequestVO requestVO) {
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutTradeNo(requestVO.getOutTradeNo());
        model.setRefundAmount(requestVO.getRefundAmount());
        model.setRefundReason("退款了");
        model.setOperatorId(requestVO.getOperatorId());

        request.setBizModel(model);
        AlipayTradeRefundResponse response = null;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
                log.info("退款成功 outTradeNo:{},tradeNo:{},msg:{}", response.getOutTradeNo(), response.getTradeNo(), response.getMsg());
            } else {
                System.out.println("调用失败");
                log.error("错误信息 message:{}", response.getBody());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退款结果查询接口
     *
     * @param outTradeNo 商户侧订单号
     */
    @ResponseBody
    @RequestMapping(value = "/queryRefundResult", method = RequestMethod.GET)
    public void queryRefundResult(@RequestParam String outTradeNo) {
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

        AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();
        model.setOutTradeNo(outTradeNo);
        model.setOutRequestNo(outTradeNo);
        request.setBizModel(model);

        AlipayTradeFastpayRefundQueryResponse response;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                log.info("退款查询成功 outTradeNo:{},tradeNo:{},msg:{}", response.getOutTradeNo(), response.getTradeNo(), response.getMsg());
            } else {
                log.error("错误信息 message:{}", response.getBody());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

    }
}
