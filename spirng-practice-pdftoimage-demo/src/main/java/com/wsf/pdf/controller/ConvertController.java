package com.wsf.pdf.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/convert")
public class ConvertController {

    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/convertPdfToImage", method = RequestMethod.POST)
    public String convertPdfToImage(@RequestParam("file") MultipartFile multipartFile) throws IOException {

        log.info("file:{}", multipartFile);
        log.info("getName:{}", multipartFile.getName());
        log.info("getContentType:{}", multipartFile.getContentType());
        log.info("getOriginalFilename:{}", multipartFile.getOriginalFilename());
        log.info("getSize:{}", multipartFile.getSize());

        return "success";
    }
}
