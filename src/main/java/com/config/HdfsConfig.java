package com.config;

/**
 * @Description
 * @Author Hexiaoshu
 * @Date 2021/2/16
 * @modify
 */
import java.io.Serializable;

/**
 *
 * @ClassName:  HdfsConfig
 * @Description: Hadoop Hdfs配置对象
 * @author:  -zzg
 * @date:   2019年7月2日 下午5:00:49
 *
 * @Copyright: 2019 www.digipower.cn
 * 注意：本内容仅限于内部使用，禁止用于其他的商业目的
 */
public class HdfsConfig implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = -3927708731917979149L;
    // hdfs 服务器地址
    private String hostname;
    // hdfs 服务器端口
    private String port;
    // hdfs 服务器账户
    private String username;

    // 构造函数
    public HdfsConfig() {
        super();
        // TODO Auto-generated constructor stub
    }

    public HdfsConfig(String hostname, String port, String username) {
        super();
        this.hostname = hostname;
        this.port = port;
        this.username = username;
    }

    //set 和 get
    public String getHostname() {
        return hostname;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }
    public String getPort() {
        return port;
    }
    public void setPort(String port) {
        this.port = port;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }



}