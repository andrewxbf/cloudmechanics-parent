package org.com.cn.xu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.com.cn.xu.AutoUpgradePackageService;
import org.com.cn.xu.vo.FtpFileVO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jcraft.jsch.ChannelSftp;

public class AutoUpgradePackageUtils {
    private static final Logger LOG = Logger.getLogger(AutoUpgradePackageService.class);

//    static String jsonFilePath = "src/main/resources/JsonConfig.json";
    static String jsonFilePath = new FileUtils().getFilePath("JsonConfig.json");
    static String downCofing = "downloadServerConfig";
    static String upCofing = "uploadServerConfig";
    static String zipCofing = "zipServerConfig";
    static String jenkinsConfig = "jenkinsServerConfig";
    static String sqlConfig = "sqlConfig";
    static String downloadPtSqlConfig = "ptSqlServerConfig";
    static String downloadLocalSqlConfig = "localSqlServerConfig";

    /**
     * @Title: doBuildPackage 
     * @Description: 调用Jenkins的API接口构建出war(par)包
     * @author xbf 
     * @throws Exception  
     * @throws
     */
    public static void doBuildPackage() throws Exception {
        try {
            JsonArray jksJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, jenkinsConfig);

            for (int i = 0; i < jksJsonArray.size(); i++) {
                JsonObject obj = jksJsonArray.get(i).getAsJsonObject();

                JenkinsUtils.invokeJenkinsUtils(obj.get(EnumPropertiesUtils.JKS_URL.getValue()).getAsString(),
                        obj.get(EnumPropertiesUtils.JKS_USER_NAME.getValue()).getAsString(),
                        obj.get(EnumPropertiesUtils.JKS_PASSWORD.getValue()).getAsString());
            }
            // 全部构建结束
            LOG.info("------全部项目构建结束！------");
        } catch (Exception e) {
            LOG.info(e.getMessage());
            throw new Exception();
        }
    }

    /**
     * @Title: downPackage 
     * @Description: 下载构建后的war(par)包
     * @author xbf 
     * @throws Exception 
     * @throws
     */
    public static void downPackage() throws Exception {
        Map<String, String> map = new HashMap<String, String>(32);
        ChannelSftp channelSftp = null;
        SftpUtils sftp = new SftpUtils();

        try {
            JsonArray downJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, downCofing);
            for (int i = 0; i < downJsonArray.size(); i++) {
                JsonObject obj = downJsonArray.get(i).getAsJsonObject();

                if (map.isEmpty()
                        || !map.containsKey(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString())) {
                    channelSftp = sftp.connect(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString(), obj
                            .get(EnumPropertiesUtils.DOWN_HOST_PORT.getValue()).getAsInt(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_USER_NAME.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_PASSWORD.getValue()).getAsString());

                    map.put(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString());
                }

                sftp.download(obj.get(EnumPropertiesUtils.DOWN_FILE_PATH.getValue()).getAsString(),
                        obj.get(EnumPropertiesUtils.DOWN_FILE_NAME.getValue()).getAsString(),
                        obj.get(EnumPropertiesUtils.DOWN_FILE_SAVE_PATH.getValue()).getAsString(), channelSftp);
            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
            throw new Exception();
        } finally {
            sftp.disconnect();
        }
    }

    /**
     * @Title: uploadZipPackage 
     * @Description: 上传压缩好的ZIP包到服务器
     * @author xbf 
     * @throws Exception 
     * @throws
     */
    public static void uploadZipPackage() throws Exception {
        Map<String, String> map = new HashMap<String, String>(32);
        boolean flag = true;
        FtpUtils ftpUtils = new FtpUtils();

        try {
            JsonArray upJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, upCofing);
            for (int i = 0; i < upJsonArray.size(); i++) {
                JsonObject obj = upJsonArray.get(i).getAsJsonObject();
                FtpFileVO fvo = new FtpFileVO();
                if (map.isEmpty() || !map.containsKey(obj.get(EnumPropertiesUtils.UP_HOST_IP.getValue()).getAsString())) {
                    ftpUtils.connect(obj.get(EnumPropertiesUtils.UP_HOST_IP.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.UP_HOST_PORT.getValue()).getAsInt(),
                            obj.get(EnumPropertiesUtils.UP_HOST_USER_NAME.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.UP_HOST_PASSWORD.getValue()).getAsString());

                    map.put(obj.get(EnumPropertiesUtils.UP_HOST_IP.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.UP_HOST_IP.getValue()).getAsString());
                }

                fvo.setFtpPath(obj.get(EnumPropertiesUtils.UP_FILE_PATH.getValue()).getAsString());
                fvo.setFileName(obj.get(EnumPropertiesUtils.UP_FILE_NAME.getValue()).getAsString());

                flag = ftpUtils.upload(obj.get(EnumPropertiesUtils.UP_SOURCE_FILE_PATH.getValue()).getAsString(), fvo);

                if (!flag) {
                    LOG.info("============》》》》压缩文件上传失败！");
                    break;
                }

                // 删除本地产生的历史文件
                new FileUtils().delFolder(obj.get(EnumPropertiesUtils.UP_SOURCE_FILE_PATH.getValue()).getAsString());
            }

            if (flag) {
                LOG.info("============》》》》Congratulation, it's OK! ZIP压缩包已经上传到服务器！请检查！");
            } else {
                LOG.info("============》》》》Sorry, it's failed！ZIP压缩包上传失败了！");

            }
        } catch (Exception e) {
            LOG.info(e.getMessage());
            throw new Exception();
        }

    }

    /**
     * @Title: doZip 
     * @Description: 将获取的资源压缩成ZIP包
     * @author xbf 
     * @throws Exception 
     * @throws
     */
    public static void doZip() throws Exception {
        List<File> fileList = null;

        try {
            JsonArray zipJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, zipCofing);
            for (int i = 0; i < zipJsonArray.size(); i++) {
                JsonObject obj = zipJsonArray.get(i).getAsJsonObject();

                fileList = new ArrayList<File>();
                File file = new File(obj.get(EnumPropertiesUtils.ZIP_FILE_FORM.getValue()).getAsString());
                fileList.add(file);

                ZipUtils.zipFile(obj.get(EnumPropertiesUtils.ZIP_FILE_TO.getValue()).getAsString(), fileList);
            }

        } catch (Exception e) {
            LOG.info(e.getMessage());
            throw new Exception();
        }
    }

    public static void deleteSqlFiles() throws Exception {
        String folderPath = JSonHandlerUtils.getJsonObject(jsonFilePath, sqlConfig);
        // 删除已下载的历史记录，重新下载
        new FileUtils().delFolder(folderPath);
    }

    public static void downPtSql() throws Exception {
        Map<String, String> map = new HashMap<String, String>(32);
        SftpUtils sftp = new SftpUtils();

        try {
            JsonArray downJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, downloadPtSqlConfig);
            for (int i = 0; i < downJsonArray.size(); i++) {
                JsonObject obj = downJsonArray.get(i).getAsJsonObject();

                if (map.isEmpty()
                        || !map.containsKey(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString())) {
                    sftp.connect(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_PORT.getValue()).getAsInt(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_USER_NAME.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_PASSWORD.getValue()).getAsString());

                    map.put(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString());
                }

                // 下载
                sftp.batchDownLoadFile(obj.get(EnumPropertiesUtils.DOWN_FILE_PATH.getValue()).getAsString(),
                        obj.get(EnumPropertiesUtils.DOWN_FILE_SAVE_PATH.getValue()).getAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sftp.disconnect();
        }
    }

    public static void downLocalSql() throws Exception {

        Map<String, String> map = new HashMap<String, String>(32);
        FtpUtils ftpUtils = new FtpUtils();

        try {
            JsonArray downJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, downloadLocalSqlConfig);
            for (int i = 0; i < downJsonArray.size(); i++) {
                JsonObject obj = downJsonArray.get(i).getAsJsonObject();
                if (map.isEmpty()
                        || !map.containsKey(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString())) {
                    ftpUtils.connect(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_PORT.getValue()).getAsInt(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_USER_NAME.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_PASSWORD.getValue()).getAsString());

                    map.put(obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString(),
                            obj.get(EnumPropertiesUtils.DOWN_HOST_IP.getValue()).getAsString());
                }

                ftpUtils.downLoadDirectory(obj.get(EnumPropertiesUtils.DOWN_FILE_PATH.getValue()).getAsString(), obj
                        .get(EnumPropertiesUtils.DOWN_FILE_SAVE_PATH.getValue()).getAsString());

            }

        } catch (Exception e) {
            LOG.info(e.getMessage());
            throw new Exception();
        }

    }

    public static void mergeSameSqlFile() {
        try {
            JsonArray downLocalJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, downloadLocalSqlConfig);
            for (int i = 0; i < downLocalJsonArray.size(); i++) {
                JsonObject obj = downLocalJsonArray.get(i).getAsJsonObject();
                FileUtils.getFileName(obj.get(EnumPropertiesUtils.DOWN_FILE_SAVE_PATH.getValue()).getAsString(),
                        jsonFilePath, sqlConfig);
            }

            JsonArray downPtJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, downloadPtSqlConfig);
            for (int i = 0; i < downPtJsonArray.size(); i++) {
                JsonObject obj = downPtJsonArray.get(i).getAsJsonObject();
                FileUtils.getFileName(obj.get(EnumPropertiesUtils.DOWN_FILE_SAVE_PATH.getValue()).getAsString(),
                        jsonFilePath, sqlConfig);
            }

            LOG.info("----------SQL文件合并完成！----------");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
