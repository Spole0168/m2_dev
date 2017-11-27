package com.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageUtils {
	
		public void getCheckCodeImage(HttpServletRequest request, HttpServletResponse response){
			
			String[] codeSequence = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J","K", "L",   
			    		"M", "N",  "P", "Q", "R", "S", "T", "U", "V", "W","X", "Y",   
			    		"Z",  "1", "2", "3", "4", "5", "6", "7", "8", "9","0" }; 
			int width = 100;
	        int height = 37;
	        Random random = new Random();
	        //设置response头信息
	        //禁止缓存
	        response.setHeader("Pragma", "No-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", 0);

	        //生成缓冲区image类
	        BufferedImage image = new BufferedImage(width, height, 1);
	        //产生image类的Graphics用于绘制操作
	        Graphics g = image.getGraphics();
	        //Graphics类的样式
	        g.setColor(this.getRandColor(200, 250));
	        g.setFont(new Font("Times New Roman",0,28));
	        g.fillRect(0, 0, width, height);
	        //绘制干扰线
	        for(int i=0;i<40;i++){
	            g.setColor(this.getRandColor(130, 200));
	            int x = random.nextInt(width);
	            int y = random.nextInt(height);
	            int x1 = random.nextInt(12);
	            int y1 = random.nextInt(12);
	            g.drawLine(x, y, x + x1, y + y1);
	        }

	        //绘制字符
	        String strCode = "";
	        for(int i=0;i<6;i++){
	            int num = random.nextInt(35);  
	            strCode += codeSequence[num];  
	            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
	            g.drawString(codeSequence[num], 13*i+6, 28);
	        }
	        g.dispose();
	        //将字符保存到session中用于前端的验证  strCode

	        try {
				ImageIO.write(image, "JPEG", response.getOutputStream());
				response.getOutputStream().flush();
			} catch (IOException e) {
				return;
			}

		}
		private Color getRandColor(int fc,int bc){
		        Random random = new Random();
		        if(fc>255)
		            fc = 255;
		        if(bc>255)
		            bc = 255;
		        int r = fc + random.nextInt(bc - fc);
		        int g = fc + random.nextInt(bc - fc);
		        int b = fc + random.nextInt(bc - fc);
		        return new Color(r,g,b);
		}
}
