package com.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author hexiaoshu
 * @Description: 字符处理工具类
 */
public class StringUtil {


    public static void main(String[] args) throws IOException {
        File file = new File("D:\\jmeter_testfile\\茅台抢购csv_data.txt");
        List<String> contents =readFileContent(new FileInputStream(file), 5);
        List<String> newContents = new ArrayList<>();
        contents.forEach(c->{
            String replace = c.replace("\t", ",");
            newContents.add(replace);
        });
        File file2 = new File("D:\\jmeter_testfile\\茅台抢购csv_data2.txt");
        writeFileContent(newContents,new FileOutputStream(file2),5);
    }


    public static List<String> readFileContent(InputStream is, int c) {
        BufferedReader reader;
        List<String> listContent = new ArrayList<>();
        try {
            BufferedInputStream bis = new BufferedInputStream(is);
            reader = new BufferedReader(new InputStreamReader(bis, StandardCharsets.UTF_8), c==0?5 * 1024 * 1024:c);
            String tempString;
            while ((tempString = reader.readLine()) != null) {
                listContent.add(tempString);
            }
            is.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listContent;
    }

    public static void writeFileContent(List<String> contest,OutputStream os,int c){
        BufferedWriter bw;
        try {
            BufferedOutputStream bos = new BufferedOutputStream(os);
            bw = new BufferedWriter(new OutputStreamWriter(bos, StandardCharsets.UTF_8), c==0?5 * 1024 * 1024:c);
            for(String str:contest){
                bw.write(str);
                bw.newLine();
                bw.flush();
            }
            bw.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    /**
     * 判断字符串为空
     * @param str str
     * @return boolean
     */
    public static boolean isEmpty(String str){
        boolean flag = false;
        if(str==null||str.trim().length()==0){
            flag=true;
        }
        return  flag;
    }


    /**
     * 判断字符串非空
     * @param str str
     * @return boolean
     */
    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }

    /**
     * 判断对象为空
     * @param obj 对象名
     * @return 是否为空
     */
    public static boolean objIsEmpty(Object obj){
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return "".equals(((String) obj).trim());
        }
        return false;
    }

    /**
     * 判断对象不为空
     * @param obj 对象名
     * @return 是否不为空
     */
    public static boolean objIsNotEmpty(Object obj)
    {
        return !objIsEmpty(obj);
    }


    /**
     * 字符串去 空格
     * @param str
     * @return
     */
    public static String removeStrSpace(String str){
        if (isNotEmpty(str)){
            return str.replaceAll(" ", "");
        }else {
            return null;
        }
    }
    /**
     * UUID获取
     * @return uuid
     */
    public static String getuuid(){
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 	作用：产生随机字符串，不长于32位
     */
    public static String createNoncestr(int length){
        String chars = "abcdefghijklmnopqrstuvwxyz0123456789";
        String str ="";
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            int index = rand.nextInt(chars.length());
            str += chars.substring(index, index + 1);
        }
        return str;
    }

    /**
     * 获取ip地址
     * @param request request
     * @return IP
     */
    public static String getIp(HttpServletRequest request){
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)){
            ip = request.getRemoteAddr();
        }
        return ip.equals("0:0:0:0:0:0:0:1")?"127.0.0.1":ip;
    }

    /**
     * 	作用：map转xml
     */
    public static String arrayToXml(Map<String,String> paramMap){
        String xml = "<xml>";
        for (String key : paramMap.keySet()) {//
            //值是否只有字母和数字
            if(paramMap.get(key).matches("^[\\da-zA-Z]*$")){
                xml += "<" + key + ">" + paramMap.get(key) + "</" + key + ">";
            }else{
                xml += "<" + key + "><![CDATA[" + paramMap.get(key) + "]]></" + key + ">";
            }
        }
        xml += "</xml>";
        return xml;
    }

    /**
     * json转map
     * @param json
     * @return
     */
    public static Map<String, Object> jsonStrToMap(String json){
        String mapStr = JSON.toJSONString(json);
        return JSON.parseObject(mapStr, Map.class);
    }

    /**
     * 判断String 是否是json格式
     * @param string String
     * @return boolear
     */
    public static boolean isJson(String string){
        try {
            JSONObject jsonObject = JSONObject.parseObject(string);
            return  true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 通过文件全路径，获取文件的MIME类型
     * @param filePath 路径
     * @return contentType
     */
    public static String getContentType(String filePath){
        String type = null;
        Path path = Paths.get(filePath);
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }


    /**
     * 生成随机密码， 包含数字，字母，大小写
     * @param length 密码长度
     * @return pwd
     */
    public static String randomGenerate(int length) {
        List<String> list = new ArrayList<>(CHARS.length);
        for (int i = 0; i < CHARS.length; i++) {
            list.add(String.valueOf(CHARS[i]));
        }
        Collections.shuffle(list);
        int count = 0;
        StringBuffer sb = new StringBuffer();
        Random random = new Random(System.nanoTime());
        while (count < length) {
            int i = random.nextInt(list.size());
            sb.append(list.get(i));
            count++;
        }
        return sb.toString();
    }
    private static final char[] CHARS = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };


    /**
     * 分页参数处理
     */
    private static final String CURRENT_PAGE="currentPage";
    private static final String PAGE_SIZE="pageSize";
    public static Map<String,Integer> getPageParam(Map<String,Object> paramMap){
        int startPage;
        int size;
        if (paramMap.get(CURRENT_PAGE)!=null){
            String pageNum = (String) paramMap.get("currentPage");
            startPage=Integer.parseInt(pageNum);
        }else {
            startPage=1;
        }
        if (paramMap.get(PAGE_SIZE)!=null){
            String pageSize = (String) paramMap.get("pageSize");
            size=Integer.parseInt(pageSize);
        }else {
            size=10;
        }
        Map<String,Integer> resultMap = new HashMap<>(16);
        resultMap.put("currentPage",startPage);
        resultMap.put("pageSize",size);
        return resultMap;
    }

    /**
     * 拼接 % %,模糊查询参数
     * @param str 拼接字符
     * @return
     */
    public static String appendLike(String str){
        if (isNotEmpty(str)){
            StringBuilder builder = new StringBuilder(str);
            builder.insert(0,"%");
            builder.insert(builder.length(),"%");
            return builder.toString();
        }else {
            return null;
        }
    }

    /**
     * 产生4位随机数(0000-9999)
     * @return 4位随机数
    */
    public static String getFourRandom(){
        Random random = new Random();
        String fourRandom = random.nextInt(10000) + "";
        int randLength = fourRandom.length();
        if(randLength<4){
            for(int i=1; i<=4-randLength; i++) {
                fourRandom = "0" + fourRandom;
            }
        }
        return fourRandom;
    }

    /**
     * 	把请求要素按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param para 请求要素
     * @param sort 是否需要根据key值作升序排列
     * @param encode 是否需要URL编码
     * @return 拼接成的字符串
     */
    public static String formatBizQueryParaMap(Map<String,String> para,boolean sort, boolean encode){
        List<String> keys = new ArrayList<String>(para.keySet());
        if (sort){
            Collections.sort(keys);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = para.get(key);
            if (encode) {
                try {
                    value = URLEncoder.encode(value, "utf-8");
                } catch (UnsupportedEncodingException e) {
                }
            }
            if (i == keys.size() - 1) {
                sb.append(key).append(QSTRING_EQUAL).append(value);
            } else {
                sb.append(key).append(QSTRING_EQUAL).append(value).append(QSTRING_SPLIT);
            }
        }
        return sb.toString();
    }
    /** = */
    private static final String QSTRING_EQUAL = "=";
    /** & */
    private static final String QSTRING_SPLIT = "&";


    /**
     * 字符串在某集合出现的次数
     * @param list
     * @param str
     * @return
     */
    public static int strListRepeCount(List<String> list,String str){
        return (int) list.stream().filter(e -> e.equals(str)).count();
    }


}
