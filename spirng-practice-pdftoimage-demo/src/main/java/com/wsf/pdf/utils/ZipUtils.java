package com.wsf.pdf.utils;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.List;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author admin
 * 文件压缩
 */
public class ZipUtils {

    public static void zipFile(String zipPath, List<File> fileList) throws IOException {
        // 获取文件压缩包输出流
        try (OutputStream outputStream = new FileOutputStream(zipPath);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(outputStream, new Adler32());
             ZipOutputStream zipOut = new ZipOutputStream(checkedOutputStream)) {
            for (File file : fileList) {
                // 获取文件输入流
                InputStream fileIn = new FileInputStream(file);
                // 使用 common.io中的IOUtils获取文件字节数组
                byte[] bytes = IOUtils.toByteArray(fileIn);
                // 写入数据并刷新
                zipOut.putNextEntry(new ZipEntry(file.getName()));
                zipOut.write(bytes, 0, bytes.length);
                zipOut.flush();
                fileIn.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
