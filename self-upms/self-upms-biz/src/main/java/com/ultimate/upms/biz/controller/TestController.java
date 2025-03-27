package com.ultimate.upms.biz.controller;

import org.apache.poi.xwpf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
/**
 * 描述：测试
 * 作者：kanwj
 * 日期：2025/3/3 11:21
 */
public class TestController {
    public static void main(String[] args) {
        try {
            // 创建 Word 文档
            XWPFDocument document = new XWPFDocument();

            // 创建一个段落
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();

            // 读取图片
            String imgPath = "D:/example.png";  // 本地图片路径
            InputStream imageStream = new FileInputStream(imgPath);

            // 添加图片到文档
            run.addPicture(
                    imageStream,
                    Document.PICTURE_TYPE_PNG, // 图片类型
                    imgPath, // 图片名称
                    500, // 图片宽度（EMU）
                    300  // 图片高度（EMU）
            );

            // 保存文档
            FileOutputStream out = new FileOutputStream("D:/output.docx");
            document.write(out);

            // 关闭流
            out.close();
            document.close();
            imageStream.close();

            System.out.println("图片插入成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
