package com.controller;

import com.common.Result;
import com.config.HdfsConfig;
import com.util.HdfsUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/2/16
 * @modify
 */
@RequestMapping("/file")
@RestController
public class FileController {

    /**
     * 文件上传
     * @param files   文件数据
     * @param catalog 上传目录
     * @return Result
     */
    @PostMapping("/upload")
    public Result upload(MultipartFile[] files,String catalog){
        boolean flag=false;
        String failName="";
        HdfsConfig config = new HdfsConfig("192.168.2.207", "9000", "hdfs");
        for (MultipartFile file : files) {
            try {
                HdfsUtil.upload(config, file.getInputStream(), String.format("%s/%s",catalog,file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
                failName=file.getOriginalFilename();
                flag=true;
            }
        }
        if (flag){
            return Result.error(failName+"上传失败");
        }
        return Result.ok("ok");
    }

    /**
     * 目录列表
     * @param path 基础路径 eg:"/"
     * @return Result
     */
    @GetMapping("/listCatalog")
    public Result listCatalog(String path){
        HdfsConfig config = new HdfsConfig("192.168.2.207", "9000", "hdfs");
        List<Map<String, Object>> list = HdfsUtil.listCatalog(config,path);
        return Result.ok(list);
    }

    /**
     * 下载文件
     * @param url 文件地址
     * @return Result
     */
    @PostMapping("/download")
    public Result download(String url){
        HdfsConfig config = new HdfsConfig("192.168.2.207", "9000", "hdfs");
        String s = "D://" + url.substring(url.lastIndexOf("/") + 1);
        HdfsUtil.download(config,url,s);
        return Result.ok("ok");
    }

    /**
     * 文件或目录删除
     * @param target 目标文件 eg "/opt" | "/opt/QQ图片20201107204347.jpg"
     * @return Result
     */
    @DeleteMapping("/delete")
    public Result delete(String target){
        HdfsConfig config = new HdfsConfig("192.168.2.207", "9000", "hdfs");
        return Result.ok(HdfsUtil.delete(config, target));
    }

    /**
     * 创建目录
     * @param map catalog 目录名称
     * @return Result
     */
    @PostMapping("/mkdir")
    public Result mkdir(@RequestBody Map<String,String> map){
        HdfsConfig config = new HdfsConfig("192.168.2.207", "9000", "hdfs");
        return Result.ok(HdfsUtil.mkdir(config, map.get("catalog")));
    }

}
