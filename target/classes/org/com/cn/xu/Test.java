package org.com.cn.xu;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        System.out.println(System.getProperty("os.name"));

        // String jsonStr =
        // "{\"actions\":[{\"causes\":[{\"shortDescription\":\"Started by user anonymous\",\"userId\":null,\"userName\":\"anonymous\"}]},{},{},{},{}],\"artifacts\":[],\"building\":false,\"description\":null,\"displayName\":\"#537\",\"duration\":28867,\"estimatedDuration\":49389,\"executor\":null,\"fullDisplayName\":\"河北文书流转 #537\",\"id\":\"537\",\"keepLog\":false,\"number\":537,\"queueId\":4248,\"result\":\"SUCCESS\",\"timestamp\":1493173656027,\"url\":\"http://192.168.29.102/jenkins/view/%E5%90%84%E5%9C%B0%E7%BD%91%E5%8E%85%E6%88%90%E6%9E%9C/job/%E6%B2%B3%E5%8C%97%E6%96%87%E4%B9%A6%E6%B5%81%E8%BD%AC/537/\",\"builtOn\":\"\",\"changeSet\":{\"items\":[],\"kind\":\"svn\",\"revisions\":[{\"module\":\"https://192.168.2.127/svn/wsbsdt/trunk/03代码类/01源代码/branches/HbWslz\",\"revision\":17908}]},\"culprits\":[],\"mavenArtifacts\":{},\"mavenVersionUsed\":\"3.1.1\"}";
        //
        // JSONObject obj = JSONObject.fromObject(jsonStr);
        // System.out.println(obj);

        // System.out.println(obj.get("id"));

        System.out.println("".equals("1"));

        System.out.println(System.getProperty("user.dir"));

        // System.out.println("file encode is " +
        // FileUtils.getFileEncode("E:\\bscl_alter.sql"));

        String jenkinsUrl = "http://192.168.29.102/jenkins/view/各地网厅成果/job/河北文书处理/";
        System.out.println(jenkinsUrl.substring(jenkinsUrl.lastIndexOf("/job/") + 5, jenkinsUrl.length() - 1));

        System.out.println("!@#!$#@$#$@%@$#@==>>" + Thread.currentThread().getContextClassLoader().getResource(""));

        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        filePath = filePath.substring(filePath.indexOf("/") + 1, filePath.lastIndexOf("/target/") + 1) + "JenkinsServerConfig.json";
        System.out.println("@@@@@@@@@@@@@-->>" + filePath);

        String jsonStr = "{\"a\":\"1\"}{\"b\":\"2\"}{\"c\":\"3\"}";
        jsonStr = jsonStr.replaceAll("[}][{]", ",");
        System.out.println(jsonStr);


        String srcStr = "[{]";
        String replaceStr = "\n{";
        String line = "222{aaabbb}";
        line = line.replaceAll(srcStr, replaceStr);
        System.out.println(line);

    }
}
