package com.mumu.common.util;

import org.springframework.util.StringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

/**
 * @Description
 * @Author Created by Mumu
 * @Date on 2020/8/4
 */
public class PrintImage {
    private Font font = new Font("正楷", Font.BOLD, 40);
    private Graphics2D g = null;
    private int fontsize = 0;

    /**
     * 导入本地图片到缓冲区
     */
    public BufferedImage loadImageLocal(String imgName) {
        try {
            return ImageIO.read(new File(imgName));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 导入网络图片到缓冲区
     *
     * @param imgName
     * @return
     */
    public BufferedImage loadImageUrl(String imgName) {
        try {
            URL url = new URL(imgName);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 生成新图片到本地
     */
    public void writeImageLocal(String newImage, BufferedImage img) {
        if (!StringUtils.isEmpty(newImage) && !Objects.isNull(img)) {
            try {
                File file = new File(newImage);
                ImageIO.write(img, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 设置文字字体等
     *
     * @param fontStyle
     * @param fontSize
     */
    public void setFont(String fontStyle, int fontSize) {
        this.fontsize = fontSize;
        this.font = new Font(fontStyle, Font.PLAIN, fontSize);
    }

    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     */
    public BufferedImage modifyImage(BufferedImage img, Object content, int x, int y) {
        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
//            g.setBackground(Color.black);
//            g.setColor(new Color(120, 120, 110));//设置字体颜色
            g.setColor(Color.black);
            if (this.font != null)
                g.setFont(this.font);
            if (content != null) {
                g.translate(w / 2, h / 2);
                g.rotate(8 * Math.PI / 180);
                g.drawString(content.toString(), x, y);
            }
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return img;
    }

    /**
     * 修改图片,返回修改后的图片缓冲区（只输出一行文本）
     * <p>
     * 时间:2007-10-8
     *
     * @param img
     * @return
     */
    public BufferedImage modifyImageYe(BufferedImage img) {

        try {
            int w = img.getWidth();
            int h = img.getHeight();
            g = img.createGraphics();
            g.setBackground(Color.WHITE);
            g.setColor(Color.blue);//设置字体颜色
            if (this.font != null)
                g.setFont(this.font);
            g.drawString("www.hi.baidu.com?xia_mingjian", w - 85, h - 5);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return img;
    }

    public BufferedImage modifyImagetogeter(BufferedImage b, BufferedImage d) {

        try {
            int w = b.getWidth();
            int h = b.getHeight();
            g = d.createGraphics();
            g.drawImage(b, 100, 10, w, h, null);
            g.dispose();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return d;
    }

    public static void main(String[] args) {
        PrintImage tt = new PrintImage();
        BufferedImage d = tt.loadImageLocal("D:\\test\\test.jpg");
        String Cname = "萧十一郎";
        tt.modifyImage(d, Cname, 50, 50);
        tt.writeImageLocal("D:\\test\\cc.jpg", d);
        System.out.println("success");
    }


}
