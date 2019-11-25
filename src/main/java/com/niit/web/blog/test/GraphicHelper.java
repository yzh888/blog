package com.niit.web.blog.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @author zh_yan
 * @ClassName GraphicHelper
 * @Description TODO
 * @Date 2019/11/17
 * @Version 1.0
 **/
public class GraphicHelper {
    /**
     * 以字符串形式返回生成的验证码，同时输出一个图片
     *
     * @param width
     *            图片的宽度
     * @param height
     *            图片的高度
     * @param imgType
     *            图片的类型
     * @param output
     *            图片的输出流(图片将输出到这个流中)
     * @return 返回所生成的验证码(字符串)
     */
    public static String create(final int width, final int height, final String imgType, OutputStream output) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();

        /*BufferedImage.TYPE_INT_RGB表示将8位RGB颜色分量打包为整数像素的图像。*/
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        /*创建图形上下文以绘制到离屏图像*/
        Graphics graphic = image.getGraphics();
        /*设置画笔颜色*/
        graphic.setColor(Color.getColor("F8F8F8"));
        /*填充一个矩形（实心）*/
        graphic.fillRect(0, 0, width, height);

        Color[] colors = new Color[] { Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE,
                Color.CYAN };
        // 在 "画板"上生成干扰线条 ( 50 是线条个数)
        for (int i = 0; i < 50; i++) {
            graphic.setColor(colors[random.nextInt(colors.length)]);
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);
            final int w = random.nextInt(20);
            final int h = random.nextInt(20);
            final int signA = random.nextBoolean() ? 1 : -1;
            final int signB = random.nextBoolean() ? 1 : -1;
            /*x,y指的是左上角的坐标,x2,y2是指矩形的宽和高*/
            graphic.drawLine(x, y, x + w * signA, y + h * signB);
        }

        // 在 "画板"上绘制字母
        /*设置字体，new Font(字体， 粗细， 大小*/
        graphic.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        for (int i = 0; i < 6; i++) {
            final int temp = random.nextInt(26) + 97;
            String s = String.valueOf((char) temp);
            sb.append(s);
            graphic.setColor(colors[random.nextInt(colors.length)]);
            /* 绘制一段文本，其中 (x, y) 坐标指的是文本序列的 左下角 的位置*/
            graphic.drawString(s, i * (width / 6), height - (height / 3));
        }
        /*销毁程序中指定的图形界面资源*/
        graphic.dispose();
        try {
            /*使用BufferedImage格式将jepg类型的图片写入output中*/
            ImageIO.write(image, imgType, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*返回字符串*/
        return sb.toString();
    }
}