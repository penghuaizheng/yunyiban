package com.huashan.yebserver.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.huashan.yebserver.utils.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequestMapping("/yebserver")
@CrossOrigin
public class CaptchaController {
    @Autowired
    private DefaultKaptcha defaultKaptch;
    @Autowired
    private RedisUtil redisUtil;
    @ApiOperation(value = "验证码")
    @GetMapping(value = "/captcha",produces="image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        //通过流的形式传播图片。
        //定义response输出类型为Imag/jpeg类型
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, mustrevalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");
        //-------------------生成验证码 begin --------------------------
        //业务逻辑：
        //获取验证码的文本内容
        String text = defaultKaptch.createText();
        System.out.println(text);
        //有了验证码，那么肯定要和用户输入的进行比较。
        //会把验证码文本内容放到session里面，从session中获取进行比较
//        request.getSession().setAttribute("captcha",text);
        String remoteAddr = request.getRemoteAddr();
        redisUtil.set(remoteAddr+":captcha",text,60);
        //得到图形验证码
        BufferedImage image = defaultKaptch.createImage(text);
        ServletOutputStream outputStream = null;
        try{
            outputStream = response.getOutputStream();
            //输出流输出图片
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally{
            if(null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //-------------------生成验证码 end --------------------------
    }
}