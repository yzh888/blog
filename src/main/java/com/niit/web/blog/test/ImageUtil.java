package com.niit.web.blog.test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * @author zh_yan
 * @ClassName image
 * @Description TODO
 * @Date 2019/11/19
 * @Version 1.0
 **/
public class ImageUtil {
    public static BufferedImage createImage(int width,int height,String s){
       BufferedImage img=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics=(Graphics2D) img.getGraphics();
        graphics.setBackground(Color.GRAY);
        graphics.fillRect(0,0,width,height);
        Color[] colors = new Color[] { Color.BLUE, Color.GRAY, Color.GREEN, Color.RED, Color.BLACK, Color.ORANGE,
                Color.CYAN };
        graphics.setPaint(Color.ORANGE);
        Font font=new Font("Serif",Font.BOLD,40);
        graphics.setFont(font);
        graphics.drawString(s,width/6,height/3);
        return img;
    }

    public static void main(String[] args) throws Exception{
        BufferedImage  img=ImageUtil.createImage(350,100, StringUtil.getcode());
        File file = new File("D:\\code.jpg");

        ImageIO.write(img, "jpg", file);
    }

}
