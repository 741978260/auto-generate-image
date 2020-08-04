package com.mumu.common.util;

import org.springframework.util.Base64Utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description
 * @Author Created by Mumu
 * @Date on 2020/8/4
 */
public class TextToImage {
    public static void main(String[] args) {
        String message = "张少侠";
        String image = createImage(message, 350, 350);
        System.out.println("S: " + image);
    }

    private static String createImage(String message, int width, int image_height) {
        String filePath = "D:\\test\\" + message + ".jpg";
        File outFile = new File(filePath);
        // 创建图片
        BufferedImage image = new BufferedImage(width, image_height,
                BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();
        g.setClip(0, 0, width, image_height);
        g.setColor(Color.white); // 背景色白色
        g.fillRect(0, 0, width, image_height);
        g.setColor(Color.black);//  字体颜色黑色
        g.setFont(new Font("宋体", Font.PLAIN, 50));// 设置画笔字体
        g.drawString(message, 100, 175);
        g.dispose();
        try {
            ImageIO.write(image, "jpg", outFile);// 输出png图片
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] data = null;
        try (InputStream in = new FileInputStream(filePath)) {
            data = new byte[in.available()];
            in.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = Base64Utils.encodeToString(data);
        return "data:image/jpg;base64," + result;
    }
}
