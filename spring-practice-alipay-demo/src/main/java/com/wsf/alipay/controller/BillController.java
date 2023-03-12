package com.wsf.alipay.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayDataDataserviceBillDownloadurlQueryModel;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.wsf.alipay.controller.vo.BillDownloadRequestVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/bill")
public class BillController {

    @Autowired
    private AlipayClient alipayClient;

    /**
     * 账单查询 查询对账单下载地址
     *
     * @param requestVO 请求参数
     * @return 地址
     */
    @RequestMapping("/downloadBill")
    public String downloadBill(@RequestBody BillDownloadRequestVO requestVO) {
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();

        AlipayDataDataserviceBillDownloadurlQueryModel model = new AlipayDataDataserviceBillDownloadurlQueryModel();
        model.setBillDate(requestVO.getBillDate());
        model.setBillType(requestVO.getBillType());
        model.setSmid(requestVO.getSmid());
        request.setBizModel(model);
        AlipayDataDataserviceBillDownloadurlQueryResponse response;
        try {
            response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
            } else {
                System.out.println("调用失败");
                log.error("错误信息 message:{}", response.getMsg());
            }
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return null;
    }
}
