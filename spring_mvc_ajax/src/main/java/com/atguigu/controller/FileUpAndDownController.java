package com.atguigu.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.UUID;

/*
* ResponseEntity：可以作为控制器方法的返回值，表示响应到浏览器的完整的响应报文
*
* 文件上传的要求：
* 1、form表单的请求方式必须为post
* 2、form表单必须设置属性enctype="multipart/form-data"
* */

@Controller
public class FileUpAndDownController {

    @RequestMapping("/test/up")
    public String testUp(MultipartFile photo, HttpSession session) throws IOException {
        //MultipartFile photo相当于<input type="file" name="photo">所对应的文件
        //获取文件的文件名
        String fileName = photo.getOriginalFilename();
        //获取上传的文件的后缀名
        String hzName = fileName.substring(fileName.lastIndexOf("."));
        //获取uuid
        String uuid = UUID.randomUUID().toString();
        //拼接一个新的文件名
        fileName = uuid + hzName;
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取当前工程下photo目录的真实路径
        String photoPath = servletContext.getRealPath("photo");
        //创建photoPath所对应的File对象
        File file = new File(photoPath);
        //判断file目录是否存在
        if(!file.exists()) {
            //不存在就创建目录
            file.mkdir();
        }
        //获取全路径
        String finalPath = photoPath + File.separator + fileName;
        //上传文件
        //将photo对象通过transferTo转移到finalPath位置
        photo.transferTo(new File(finalPath));
        return "success";
    }

    @RequestMapping("/test/down")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException {
        //获取ServletContext对象
        ServletContext servletContext = session.getServletContext();
        //获取服务器中文件的真实路径
        //getRealPath方法参数如果为空字符串表示工程在服务器上的路径，有字符串会拼接到路径
        // String realPath = servletContext.getRealPath("/img/1.png");
        String realPath = servletContext.getRealPath("img");
        // File.separator是文件分隔符
        realPath = realPath + File.separator + "1.png";
        //创建输入流
        InputStream is = new FileInputStream(realPath);
        //创建字节数组
        //is.available()获取当前字节输入流所对应的文件的所有的字节数
        byte[] bytes = new byte[is.available()];
        //将流读到字节数组中
        is.read(bytes);
        //创建HttpHeaders对象设置响应头信息
        MultiValueMap<String, String> headers = new HttpHeaders();
        //设置要下载方式以及下载文件的名字
        headers.add("Content-Disposition", "attachment;filename=1.png");
        //设置响应状态码
        HttpStatus statusCode = HttpStatus.OK;
        //创建ResponseEntity对象
        //三个参数分别为 响应体、响应头、状态码
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(bytes, headers, statusCode);
        //关闭输入流
        is.close();
        return responseEntity;
    }

}
