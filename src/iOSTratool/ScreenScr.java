package iOSTratool;
/**
 * Created by zhangzhixiong on 17/3/17.
 **/

import java.io.*;

import MainiOS.MainSetup;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import traverse.FileOperation;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.io.File;
import java.io.IOException;


public class ScreenScr  {
    /**
     * 绘制一个由 x 和 y 坐标数组定义的闭合多边形
     * @param driver  appiumdriver
     * @param x   x坐标
     * @param y   y坐标
     * @param width   长
     * @param height  宽
     * @throws IOException
     */

    //截图
    public static void getScreen(TakesScreenshot driver,int x,int y,int width,int height) throws IOException, InterruptedException {

        File scrfile=driver.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrfile, new File(MainSetup.cyrPatn+"/Png/img"+ScreenTime()+".png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("GetScreenshot Fail");
        }finally{
            int[] xPoints = {x*2,x*2,(x+width)*2,(x+width)*2};
            int[] yPoints = {y*2,(y+height)*2,(y+height)*2,y*2};
            String path =MainSetup.cyrPatn+"/Png/img"+ScreenTime()+".png";
            Thread.sleep(1000);
            drawPolygon(path,xPoints,yPoints,path);
        }
    }

    /**
     * 绘制一个由 x 和 y 坐标数组定义的闭合多边形
     * @param srcImagePath 源图片路径
     * @param xPoints   x坐标数组
     * @param yPoints   y坐标数组
     * @param nPoints   坐标点的个数
     * @param polygonColor  线条颜色
     * @param imageFormat   图像写入格式
     * @param toPath    图像写入路径
     * @throws IOException
     */
    public static void drawPolygon(String srcImagePath, int[] xPoints, int[] yPoints, String toPath) throws IOException {
        FileOutputStream fos = null;
        String imageFormat = "png";
        Color polygonColor = Color.blue;
        int nPoints =4;
        try {
            //获取图片
            BufferedImage image = ImageIO.read(new File(srcImagePath));
            //根据xy点坐标绘制闭合多边形
            Graphics2D g2d = image.createGraphics();
            //绘制的线条加粗4个像素点
            g2d.setStroke(new BasicStroke(4f));
            g2d.setColor(polygonColor);
            g2d.drawPolygon(xPoints, yPoints, nPoints);
            fos = new FileOutputStream(toPath);
            ImageIO.write(image, imageFormat, fos);
            g2d.dispose();
        } catch (Exception e) {
            FileOperation.contentToTxt(MainSetup.filetext,"编辑图片失败");
            PrintWriter p = new PrintWriter(new FileOutputStream(MainSetup.cyrPatn+"/error.txt"));
            e.printStackTrace(p);
        }finally{
            if(fos!=null){
                fos.close();
            }
        }
    }

    /**
     * 获取当前的时间
     */
    public static String ScreenTime() throws IOException {
        Date date=new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmss");
        String filetime=format.format(date);
        return filetime;
    }

}

