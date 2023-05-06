package com.wsf.pdf.controller;

import com.wsf.pdf.utils.PdfUtil;
import com.wsf.pdf.utils.ZipUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/convert")
public class ConvertController {

    @ResponseBody
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello() {
        return "hello";
    }


    @RequestMapping(value = "/convertPdfToImage", method = RequestMethod.POST)
    public ResponseEntity<byte[]> convertPdfToImage(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws IOException {

        String originalName = multipartFile.getOriginalFilename();
        String name = originalName.substring(0, originalName.lastIndexOf("."));
        String parentPath = "/Users/admin/temp";

        List<File> fileList = PdfUtil.pdfToImage2(multipartFile.getInputStream(), parentPath, name, "png");
        String downloadFilePath;
        String downloadFileName;
        if (fileList.size() > 1) {
            downloadFileName = name + "-" + System.currentTimeMillis() + ".zip";
            downloadFilePath = parentPath + "/" + downloadFileName;
            ZipUtils.zipFile(downloadFilePath, fileList);
        } else {
            File file = fileList.get(0);
            downloadFilePath = file.getAbsolutePath();
            downloadFileName = file.getName();
        }

        FileInputStream fileInputStream = new FileInputStream(new File(downloadFilePath));
        //创建输入流
        //创建字节数组
        byte[] bytes = new byte[fileInputStream.available()];
        //将流读到字节数组中
        fileInputStream.read(bytes);
        //创建HttpHeaders对象，设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置下载方式和下载文件的名称   attachment表示以附件的形式下载
        headers.add("Content-Disposition", "attachment;filename=" + URLEncoder.encode(downloadFileName, "UTF-8"));
        headers.add("Content-Type", "text/plain;charset=UTF-8");
        //设置响应状态码
        HttpStatus status = HttpStatus.OK;
        //创建ResponseEntity对象
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, status);
        //关闭输入流
        fileInputStream.close();
        return responseEntity;
    }
}
