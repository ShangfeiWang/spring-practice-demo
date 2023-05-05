package com.wsf.context;

import com.wsf.context.utils.PdfUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PDFTest {

    /**
     * 单页PDF形式的发票转为图片
     * 此处单元测试的注解是采用:org.junit.Test
     */
    @Test
    public void pdfInvoiceToImage() {
        String fileName = "滴滴电子发票1.pdf";
        String source = "C:\\Users\\王尚飞\\Desktop\\pdf\\" + fileName;
        String desFileName = fileName.substring(0, fileName.lastIndexOf("."));
        String desFilePath = "C:\\Users\\王尚飞\\Desktop\\pdf\\";
        String imageType = "png";
        Pair<Boolean, Object> pair = PdfUtil.pdfToImage(source, desFilePath, desFileName, imageType);
        System.out.println("PDF形式的发票转化为图片结果：" + pair.getLeft());
        if (!pair.getLeft()) {
            System.out.println("" + pair.getRight());
        } else {
            List<String> fileList = (List<String>) pair.getRight();
            System.out.println("转化的文件的内容：");
            fileList.forEach(System.out::println);
        }
    }

}
