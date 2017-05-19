package org.com.cn.xu.util;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class JSonHandlerUtils {

    public static String getJsonObject(String jsonFilePath, String configProperties) throws Exception {
        FileInputStream input = null;
        InputStreamReader inputReader = null;
        String str = null;
        try {
            // 创建json解析器
            JsonParser parser = new JsonParser();
            // 使用解析器解析json数据，返回值是JsonElement，强制转化为其子类JsonObject类型
            input = new FileInputStream(jsonFilePath);
            inputReader = new InputStreamReader(input, "UTF-8");
            JsonObject object = (JsonObject) parser.parse(inputReader);

            str = object.get(configProperties).getAsString();

        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
            if (inputReader != null) {
                inputReader.close();
            }
        }

        return str;

    }

    public static JsonArray getJsonArray(String jsonFilePath, String configProperties) throws Exception {
        FileInputStream input = null;
        InputStreamReader inputReader = null;
        JsonArray jsonArray = null;

        try {
            // 创建json解析器
            JsonParser parser = new JsonParser();
            // 使用解析器解析json数据，返回值是JsonElement，强制转化为其子类JsonObject类型
            input = new FileInputStream(jsonFilePath);
            inputReader = new InputStreamReader(input, "UTF-8");

            JsonObject object = (JsonObject) parser.parse(inputReader);

            // 遍历JSON数组
            jsonArray = object.getAsJsonArray(configProperties);

        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
            if (inputReader != null) {
                inputReader.close();
            }
        }

        return jsonArray;
    }

    public static String getJsonFileAsString(String jsonFilePath) throws Exception {
        FileInputStream input = null;
        InputStreamReader inputReader = null;
        String jsonString = null;

        try {
            // 创建json解析器
            JsonParser parser = new JsonParser();
            // 使用解析器解析json数据，返回值是JsonElement，强制转化为其子类JsonObject类型
            input = new FileInputStream(jsonFilePath);
            inputReader = new InputStreamReader(input, "UTF-8");

            JsonObject object = (JsonObject) parser.parse(inputReader);

            jsonString = object.toString();
        } catch (Exception e) {
            throw e;
        } finally {
            if (input != null) {
                input.close();
            }
            if (inputReader != null) {
                inputReader.close();
            }
        }

        return jsonString;
    }
}
