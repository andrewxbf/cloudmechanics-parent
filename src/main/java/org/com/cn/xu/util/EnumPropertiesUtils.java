package org.com.cn.xu.util;

public enum EnumPropertiesUtils {
    /*下载服务器通用配置字符串*/
    DOWN_HOST_IP("downHostIp"), DOWN_HOST_USER_NAME("downUserName"), DOWN_HOST_PASSWORD("downPassword"), DOWN_HOST_PORT(
            "downPort"), DOWN_FILE_NAME("downFileName"), DOWN_FILE_PATH("downFilePath"), DOWN_FILE_SAVE_PATH(
            "downFileSavePath"),

    /*上传服务器通用配置字符串*/
    UP_HOST_IP("upHostIp"), UP_HOST_USER_NAME("upUserName"), UP_HOST_PASSWORD("upPassword"), UP_HOST_PORT(
            "upPort"), UP_FILE_NAME("upFileName"), UP_FILE_PATH("upFilePath"), UP_SOURCE_FILE_PATH(
            "upSourceFilePath"),

    /*压缩包通用配置字符串*/
    ZIP_FILE_FORM("zipFileFrom"), ZIP_FILE_TO("zipFileTo"),

    /*Jenkins通用配置字符串*/
    JKS_URL("jksUrl"), JKS_USER_NAME("jksUserName"), JKS_PASSWORD("jksPassword");

    private String value;

    private EnumPropertiesUtils(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}