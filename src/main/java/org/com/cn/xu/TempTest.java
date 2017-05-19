package org.com.cn.xu;

import org.apache.log4j.Logger;
import org.com.cn.xu.util.EnumPropertiesUtils;
import org.com.cn.xu.util.FileUtils;
import org.com.cn.xu.util.JSonHandlerUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TempTest {
    @SuppressWarnings("unused")
    private static final Logger LOG = Logger.getLogger(TempTest.class);

    static String jsonFilePath = "src/main/resources/JsonConfig.json";
    static String sqlConfig = "sqlConfig";
    static String downloadPtSqlConfig = "downloadPtSqlConfig";
    static String downloadLocalSqlConfig = "downloadLocalSqlConfig";

    public static void testMethod() {
        try {
            JsonArray downLocalJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, downloadLocalSqlConfig);
            for (int i = 0; i < downLocalJsonArray.size(); i++) {
                JsonObject obj = downLocalJsonArray.get(i).getAsJsonObject();
                FileUtils.getFileName(obj.get(EnumPropertiesUtils.DOWN_FILE_SAVE_PATH.getValue() + i).getAsString(),
                        jsonFilePath, sqlConfig);
            }

            JsonArray downPtJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, downloadPtSqlConfig);
            for (int i = 0; i < downPtJsonArray.size(); i++) {
                JsonObject obj = downPtJsonArray.get(i).getAsJsonObject();
                FileUtils.getFileName(obj.get(EnumPropertiesUtils.DOWN_FILE_SAVE_PATH.getValue() + i).getAsString(),
                        jsonFilePath, sqlConfig);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public static void main(String[] args) {
        testMethod();
    }
}
