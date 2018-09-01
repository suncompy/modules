package com.lebaoxun.upload.utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * 二维码生成工具类
 */
public class EncodeUtil {

	private static Logger logger = LoggerFactory.getLogger(EncodeUtil.class);
	
    private static final int WIDTH = 430; // 二维码图片宽度
    private static final int HEIGHT = 430; // 二维码图片高度
    private static final String FORMAT = "gif";// 二维码的图片格式 gif
    private static final int BLACK = 0xFF000000;//用于设置图案的颜色  
    private static final int WHITE = 0xFFFFFFFF; //用于背景色  
    private static final String TEMP_FOLDER = "/www/temp/";  // 二维码绘制后的临时输出目录
    
    /**
     * 不带图的二维码
     * @throws IOException
     * @throws WriterException
     */
    public static String YUAN_ERCODE(String contents) throws IOException, WriterException{  
        //String contents = "http://vip.dddbi.com/?clubId=8888888"; // 二维码内容  
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");     
        hints.put(EncodeHintType.MARGIN, 0);//设置二维码边的空度，非负数  
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,//要编码的内容  
                BarcodeFormat.QR_CODE,  
                WIDTH, //码的宽度  
                HEIGHT, //码的高度  
                hints);//生成码时的一些配置,此项可选  
        BufferedImage image = toBufferedImage(bitMatrix);
        
        // 将绘制好的二维码图片流，生成二维码   
        String path = TEMP_FOLDER+"qrcode_"+ System.currentTimeMillis()+"."+FORMAT;
        File file = new File(TEMP_FOLDER);
        if(!file.isDirectory()){
        	file.mkdirs();
        }
        File outputFile = new File(path);//指定输出路径  
        
        if (!ImageIO.write(image, FORMAT, outputFile)) {  
            throw new IOException("Could not write an image of format " + FORMAT + " to " + outputFile); 
        }else{
            logger.info("无图标二维码"+outputFile+"生成成功！");
            return path;
        } 
    }  
    
    public static String Encode_QR_CODE(String contents,String imgUrl) throws IOException, WriterException{  
    	//String contents = "http://vip.dddbi.com/?clubId=8888888"; // 二维码内容  
    	//String format = "gif";// 二维码的图片格式 gif  
    	Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();  
    	// 指定纠错等级,纠错级别（L 7%、M 15%、Q 25%、H 30%）  
    	hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);  
    	// 内容所使用字符集编码  
    	hints.put(EncodeHintType.CHARACTER_SET, "utf-8");     
    	hints.put(EncodeHintType.MARGIN, 0);//设置二维码边的空度，非负数  
    	
    	BitMatrix bitMatrix = new MultiFormatWriter().encode(contents,//要编码的内容  
    			BarcodeFormat.QR_CODE,  
    			WIDTH, //条形码的宽度  
    			HEIGHT, //条形码的高度  
    			hints);//生成条形码时的一些配置,此项可选  
    	
    	//MatrixToImageWriter.writeToFile(bitMatrix, FORMAT, outputFile);  
    	BufferedImage image = toBufferedImage(bitMatrix);  
        //设置logo图标  
        //读取二维码图片，并构建绘图对象 
        Graphics2D g2 = image.createGraphics();  
        int matrixWidth = image.getWidth();  
        int matrixHeigh = image.getHeight();  
        //读取Logo URL图片 [俱乐部图像]
        //String imgUrl = "http://vip.dddbi.com/agent/mobile/img/logo.png";//"http://image.mxqh666.com/453174880/union/27541481/img1521996420977.png";
        URL url = new URL(imgUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        InputStream imagein = connection.getInputStream();
        BufferedImage logo = ImageIO.read(imagein);
        //开始绘制图片  
        g2.drawImage(logo,matrixWidth/5*2,matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5, null);//绘制       
        BasicStroke stroke = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
        g2.setStroke(stroke);// 设置笔画对象  
        //指定弧度的圆角矩形  
        RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth/5*2, matrixHeigh/5*2, matrixWidth/5, matrixHeigh/5,20,20);  
        g2.setColor(Color.white);  
        g2.draw(round);// 绘制圆弧矩形  
        //设置logo 有一道灰色边框  
        BasicStroke stroke2 = new BasicStroke(1,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);   
        g2.setStroke(stroke2);// 设置笔画对象  
        RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth/5*2+2, matrixHeigh/5*2+2, matrixWidth/5-4, matrixHeigh/5-4,20,20);  
        g2.setColor(new Color(128,128,128));  
        g2.draw(round2);// 绘制圆弧矩形  
        g2.dispose();  
        image.flush() ;
        
        // 将绘制好的二维码图片流，生成二维码  
        String path = TEMP_FOLDER+"encode_"+ System.currentTimeMillis()+FORMAT;
    	File outputFile = new File(path);//指定输出路径  
        if (!ImageIO.write(image, FORMAT, outputFile)) {  
            throw new IOException("Could not write an image of format " + FORMAT + " to " + outputFile);  
        }else{  
            logger.info("有图标二维码"+outputFile+"生成成功！"); 
            return path;
        }
    }
    
    public static BufferedImage toBufferedImage(BitMatrix matrix) {  
        int width = matrix.getWidth();  
        int height = matrix.getHeight();  
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        for (int x = 0; x < width; x++) {  
            for (int y = 0; y < height; y++) {  
                image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));  
            	/*if(x <= width/2 && y <= height/2){
            		image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));  
            	}else if(x >= width/2 && y >= height/2){
            		image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));  
            	}else if(x < width/2 && y > height/2){
            		image.setRGB(x, y,  (matrix.get(x, y) ? Color.BLUE.getRGB() : WHITE));  
            	}else if(x > width/2 && y < height/2){
            		image.setRGB(x, y,  (matrix.get(x, y) ? Color.BLUE.getRGB() : WHITE));  
            	}else{
            		image.setRGB(x, y,  (matrix.get(x, y) ? BLACK : WHITE));
            	}*/
            }  
        }  
        return image;  
    }
}
