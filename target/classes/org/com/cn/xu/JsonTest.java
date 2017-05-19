package org.com.cn.xu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.com.cn.xu.vo.ActorVO;
import org.com.cn.xu.vo.JenkinsVO;
import org.json.JSONObject;

public class JsonTest {
    // String创建json
    /**
     * {
     　　　　　　"name":"王尼玛",
     　　　　　　"fans":[{
     　　　　　　　　　　　　"name":"小王",
     　　　　　　　　　　　　"age":"7"
     　　　　　　　　　　　},{
     　　　　　　　　　　　　"name":"小尼玛",
     　　　　　　　　　　　　"age":"10"
     　　　　　　　　　　　}]
     　　　　　　}
     */
    static void StringCreateJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", "王尼玛");

        // 粉丝是个数组,其实就是嵌套json
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("name", "小王");
        jsonObject1.put("age", 7);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", "小尼玛");
        jsonObject2.put("age", 10);

        // 从此处可以看出其实list和json也是互相转换的
        List<JSONObject> jsonObjects = new ArrayList<JSONObject>();
        jsonObjects.add(jsonObject1);
        jsonObjects.add(jsonObject2);
        jsonObject.put("fans", jsonObjects);

        System.out.println("jsonObject直接创建json:" + jsonObject);
    }

    // 第二种方法,用map方式
    static void mapCreateJson() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", "王尼玛");
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "小王");
        map1.put("age", 7);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "小尼玛");
        map2.put("age", 10);
        List<Map> jsonObjects = new ArrayList<Map>();
        jsonObjects.add(map1);
        jsonObjects.add(map2);
        map.put("fans", jsonObjects);
        System.out.println("集合中Map创建json对象:" + new JSONObject(map));
    }

    // 第三种,也是比较常用的,用bean转换,(这里用的是map作为子json,如果必须要创建复杂bean对象,建议用Gjson操作)
    static void beanCreateJson() {
        ActorVO actor = new ActorVO();
        // actor.setName("王尼玛");
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("name", "小王");
        map1.put("age", 7);
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("name", "小尼玛");
        map2.put("age", 10);
        List<Map> maps = new ArrayList<Map>();
        maps.add(map1);
        maps.add(map2);
        // actor.setFans(maps);
        System.out.println("java bean创建json对象:" + new JSONObject(actor));
    }

    public static void main(String[] args) throws IOException {
        // StringCreateJson();
        // mapCreateJson();
        // beanCreateJson();
        JSONObject jsonStr = null;
        ActorVO actor = new ActorVO();
        actor.setSqlConfig("D:/temp/河北17年3月云厅1.0里程碑升级包/sql_update/");
        List<JenkinsVO> jenkinsList = new ArrayList<JenkinsVO>();
        JenkinsVO jenkins = new JenkinsVO();
        jenkins.setJksUrl("http://192.168.29.102/jenkins/view/各地网厅成果/job/河北文书处理");
        jenkins.setJksUserName("");
        jenkins.setJksPassword("");
        jenkinsList.add(jenkins);

        jenkins = new JenkinsVO();
        jenkins.setJksUrl("http://192.168.29.102/jenkins/view/各地网厅成果/job/河北文书流转");
        jenkins.setJksUserName("");
        jenkins.setJksPassword("");

        jenkinsList.add(jenkins);

        actor.setJenkinsConfig(jenkinsList);
        jsonStr = new JSONObject(actor);

        System.out.println(jsonStr.toString());

        String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main"
                + File.separator + "resources" + File.separator + "JsonConfig.json";
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fos = null;
        String str = jsonStr.toString();
        try {

            byte bytes[] = new byte[512];
            bytes = str.getBytes();
            int b = bytes.length; // 是字节的长度，不是字符串的长度
            fos = new FileOutputStream(file);
            fos.write(bytes, 0, b);
            fos.flush();

        } catch (FileNotFoundException e) {

        } finally {
            if (null != fos) {
                fos.close();
            }
        }
    }
}
