package com.niit.web.blog.controller;

import com.niit.web.blog.test.ImageUtil;
import com.niit.web.blog.test.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 请求接口
 * @author
 * @ClassName CodeController
 * @Description TODO
 * @Date 2019/11/19:16:09
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/code"})
public class CodeController1 extends HttpServlet {
    private static final long serialVersionUID = 3398560501558431737L;
   /* @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调用字符串生成方法，生成随机字符串
        String codeString = StringUtil.getcode();
        //2.存入session，同时会返回给客户端cookie
        req.getSession().setAttribute("code",codeString);
        HttpSession session=req.getSession();
        session.setAttribute("code",codeString);

        //3.调用生成图形的工具方法，传入图片的高宽，需要写入的字符串，得到图形
        BufferedImage img = ImageUtil.createImage(150,100,codeString);
        //4.将图片先暂存到服务器指定路径
        File file = new File("D:/verify.jpg");
        ImageIO.write(img,"jpg",file);
        //5.通过输出流，把图片返回客户端
        InputStream inputStream = new FileInputStream(file);
        byte[] b= new  byte[(int) file.length()];
        inputStream.read(b);
        resp.setContentType("image/jpg");
        //6.通过输出流，将数组内容传送到客户端
      *//*  OutputStream outputStream = resp.getOutputStream();
        outputStream.write(b);
        //7.关闭流
        outputStream.close();
        inputStream.close();*//*
        ServletOutputStream out=resp.getOutputStream();
        ImageIO.write(img,"jpg",out);
        out.close();
    }*/
   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取随机验证码
       String code= StringUtil.getcode();
       //存入session
       HttpSession session=req.getSession();
       session.setAttribute("code",code);
       resp.setHeader("Access-Token",session.getId());
       /*int width=130;
       int height=35;*/
       BufferedImage img = ImageUtil.createImage(200, 100, code);
      /* Random random=new Random();
       Color color=new Color(130,182,45);*/
       //创建图片缓存区，传参为宽高和图片类型
      /* BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);*/
       //获取画笔并绘制42444
     /*  Graphics g=bi.getGraphics();
       g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));
       g.setColor(color);
       g.fillRect(0,0,width,height);
       g.setColor(Color.DARK_GRAY);*/
       //向客户端输出图片
      /* g.drawString(code,width/4,height/2);*/
       //取得res87ponse的字节流

       resp.setContentType("image/jpg");
       ServletOutputStream out=resp.getOutputStream();
       ImageIO.write(img,"jpg",out);
       out.close();
}
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String inputCode = req.getParameter("")
    }

}