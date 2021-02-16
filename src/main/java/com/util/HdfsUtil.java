package com.util;

/**
 * @Description Hdfs 文件工具类
 * @Author Hexiaoshu
 * @Date 2021/2/16
 * @modify
 */
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.config.HdfsConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;


@Slf4j
public class HdfsUtil {

    /**
     * 文件上传
     * @param config  配置
     * @param source  源文件
     * @param catalog 目标目录
     */
    public static void  upload(HdfsConfig config, InputStream source, String catalog) {
        try {
            FileSystem fileSystem = FileSystem.get(new URI(getHdfsUrl(config)), new Configuration(),config.getUsername());
            BufferedInputStream in = new BufferedInputStream(source);
            OutputStream out = fileSystem.create(new Path(catalog));
            IOUtils.copyBytes(in, out, 4096, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取目录
     * @param config 配置
     * @return List
     */
    public static List<Map<String,Object>> listCatalog(HdfsConfig config,String path){
        List<Map<String,Object>> list = new LinkedList<>();
        try {
            FileSystem fileSystem = FileSystem.get(new URI(getHdfsUrl(config)), new Configuration(),config.getUsername());
            FileStatus[] status = fileSystem.listStatus(new Path(path));
            for (FileStatus file:status) {
                Map<String,Object> map = new HashMap<>(2);
                map.put("isDirectory",file.isDirectory());
                map.put("path",file.getPath().toString());
                map.put("name",file.getPath().getName());
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 下载文件
     * @param config 配置
     * @param url    路径
     * @param target 目标地址
     */
    public static void download(HdfsConfig config, String url, String target) {
        try {
            FileSystem fileSystem = FileSystem.get(new URI(getHdfsUrl(config)), new Configuration(),config.getUsername());
            InputStream in = fileSystem.open(new Path(url));
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(target));
            IOUtils.copyBytes(in, out, 4096, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件删除
     * @param config 配置
     * @param target 目标文件
     * @return boolean
     */
    public static boolean delete(HdfsConfig config, String target) {
        boolean flag = false;
        try {
            FileSystem fileSystem = FileSystem.get(new URI(getHdfsUrl(config)), new Configuration(), config.getUsername());
            // 调用delete方法，删除指定的文件。参数:false:表示是否递归删除
            flag = fileSystem.delete(new Path(target), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 创建目录
     * @param config   config
     * @param catalog  catalog
     * @return boolean
     */
    public static boolean mkdir(HdfsConfig config, String catalog) {
        boolean flag = false;
        try {
            FileSystem 	fileSystem = FileSystem.get(new URI(getHdfsUrl(config)), new Configuration(), config.getUsername());
            flag = fileSystem.mkdirs(new Path(catalog));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    private static String getHdfsUrl(HdfsConfig config) {
        return String.format("hdfs://%s:%s", config.getHostname(), config.getPort());
    }

}
