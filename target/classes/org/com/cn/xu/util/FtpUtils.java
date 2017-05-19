package org.com.cn.xu.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.TimeZone;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.log4j.Logger;
import org.com.cn.xu.vo.FtpFileVO;

public class FtpUtils {
    private static final Logger LOG = Logger.getLogger(FtpUtils.class);

    private FTPClient ftpClient;

    // FtpClient的Set 和 Get 函数
    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }

    public boolean connect(String host, int port, String username, String password) throws SocketException, IOException {
        boolean isLogin = false;
        this.ftpClient = new FTPClient();
        FTPClientConfig ftpClientConfig = new FTPClientConfig();
        ftpClientConfig.setServerTimeZoneId(TimeZone.getDefault().getID());
        this.ftpClient.setControlEncoding("GBK");
        this.ftpClient.configure(ftpClientConfig);
        try {
            if (port > 0) {
                this.ftpClient.connect(host, port);
            } else {
                this.ftpClient.connect(host);
            }
            // FTP服务器连接回答
            int reply = this.ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                this.ftpClient.disconnect();
                LOG.error("登录FTP服务失败！");
                return isLogin;
            }
            this.ftpClient.login(username, password);
            // 设置传输协议
            this.ftpClient.enterLocalPassiveMode();
            this.ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            LOG.info("恭喜" + username + "成功登陆FTP服务器");
            isLogin = true;
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error(username + "登录FTP服务失败！" + e.getMessage());
        }
        this.ftpClient.setBufferSize(1024 * 2);
        this.ftpClient.setDataTimeout(30 * 1000);
        return isLogin;
    }

    public boolean upload(String zipFilePath, FtpFileVO fileVO) throws SocketException, IOException {
        File file = new File(zipFilePath + "\\" + fileVO.getFileName());
        FileInputStream input = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(input);

        String[] ftpDirs = fileVO.getFtpPath().replace('\\', '/').replaceAll("/+", "/").split("/");
        for (String openPath : ftpDirs) {
            // openPath = new String(openPath.getBytes("GBK"), "iso-8859-1");
            if (!this.ftpClient.changeWorkingDirectory(openPath)) {
                this.ftpClient.makeDirectory(openPath);
                this.ftpClient.changeWorkingDirectory(openPath);
            }
        }
        try {
            // String fileName = new
            // String(fileVO.getFileName().getBytes("GBK"), "iso-8859-1");
            return this.ftpClient.storeFile(fileVO.getFileName(), bis);
        } finally {
            if (null != input) {
                input.close();
            }
            if (null != bis) {
                bis.close();
            }
            this.ftpClient.changeWorkingDirectory("/");
            this.ftpClient.logout();
            if (this.ftpClient.isConnected()) {
                try {
                    this.ftpClient.disconnect();
                } catch (IOException e) {
                    LOG.error(e);
                }
            }
        }

    }

    public static byte[] download(FtpFileVO fileVO, FTPClient client) throws SocketException, IOException {
        String[] ftpDirs = fileVO.getFtpPath().replace('\\', '/').replaceAll("/+", "/").split("/");
        boolean isDirectory = true;
        client.changeWorkingDirectory("/");
        for (String dir : ftpDirs) {
            // dir = new String(dir.getBytes("GBK"), "ISO8859-1");
            if (!client.changeWorkingDirectory(dir)) {
                isDirectory = false;
                break;
            }
        }
        if (!isDirectory) {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            try {
                client.retrieveFile(fileVO.getFileName(), output);
                byte[] arrayOfByte = output.toByteArray();

                return arrayOfByte;
            } finally {
                output.close();
                client.changeWorkingDirectory("/");
                client.logout();
                if (client.isConnected()) {
                    try {
                        client.disconnect();
                    } catch (IOException e) {
                        LOG.error(e);
                    }
                }

            }
        }
        throw new IOException("FTP服务器端不存在请求的文件路径：" + fileVO.getFtpPath());
    }

    /**
     * 功能说明：通过递归实现ftp目录文件夹下载
     * 
     * @param ftpfilepath
     *            当前ftp目录
     * @param localpath
     *            当前本地目录
     */
    public void downLoadDirectory(String ftpfilepath, String localpath) {
        FileOutputStream fos = null;

        try {
            // System.out.println(ftpfilepath);
            // 得到当前ftp目录下的文件列表
            FTPFile[] ff = ftpClient.listFiles(ftpfilepath);

            if (ff != null) {
                for (int i = 0; i < ff.length; i++) {
                    String localfilepath = localpath + ff[i].getName();
                    // 根据ftp文件生成相应本地文件
                    File localFile = new File(localfilepath);

                    // 如果是目录
                    if (ff[i].isDirectory()) {
                        // 如果本地文件夹不存在就创建
                        // localFile.mkdir();
                        // 转到ftp文件夹目录下
                        String ftpfp = ftpfilepath + ff[i].getName() + "/";
                        // 转到本地文件夹目录下
                        String localfp = localfilepath + "/";
                        // 递归调用
                        this.downLoadDirectory(ftpfp, localfp);

                    }
                    // 如果是文件
                    if (ff[i].isFile()) {
                        File lFile = new File(localpath);
                        if (ff[i].getName().toLowerCase().endsWith(".sql")) {
                            // 如果文件所在的文件夹不存在就创建
                            if (!lFile.exists()) {
                                lFile.mkdirs();
                                // return;
                            }
                            // lFile.mkdir();

                            // 目标ftp文件下载路径
                            String filepath = ftpfilepath + ff[i].getName();
                            fos = new FileOutputStream(localFile);
                            try {
                                // 从FTP服务器上取回一个文件
                                // ftpClient.retrieveFile(new
                                // String(filepath.getBytes("GBK"),
                                // "ISO-8859-1"), fos);
                                ftpClient.retrieveFile(filepath, fos);
                                fos.flush();
                            } catch (Exception e) {
                                // boo = false;
                                e.printStackTrace();
                            } finally {
                                if (null != fos) {
                                    try {
                                        fos.close();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        // else {
                        // // 判断当前文件夹是否为空，为空就删除当前文件夹
                        // if (lFile.exists() && lFile.listFiles().length <= 0)
                        // {
                        // System.out.println(lFile.getAbsoluteFile() +
                        // "--------------------no files!!!!!!!");
                        // lFile.delete();
                        // }
                        // }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (null != fos) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /*public static void main(String[] args) throws Exception {
        FtpUtils ftp = new FtpUtils();

        ftp.connect("192.168.60.207", 21, "liuxin", "liuxin123");

        ftp.downLoadDirectory("/河北/201703/数据库和脚本配置/", "D:\\temp\\河北17年3月云厅1.0里程碑升级包\\sql_update\\temp\\local\\");

        ftp.ftpClient.disconnect();

    }*/

    /*** 
    * @下载文件夹 
    * @param localDirectoryPath本地地址 
    * @param remoteDirectory 远程文件夹 
    * */
    // public boolean downLoadDirectory(String localDirectoryPath, String
    // remoteDirectory) {
    // try {
    // // String fileName = new File(remoteDirectory).getName();
    // // localDirectoryPath = localDirectoryPath + fileName + "\\";
    // new File(localDirectoryPath).mkdirs();
    // FTPFile[] allFile = this.ftpClient.listFiles(remoteDirectory);
    // for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
    // if (!allFile[currentFile].isDirectory()) {
    // downloadFile(allFile[currentFile].getName(), localDirectoryPath,
    // remoteDirectory);
    // }
    // }
    // for (int currentFile = 0; currentFile < allFile.length; currentFile++) {
    // if (allFile[currentFile].isDirectory()) {
    // String strremoteDirectoryPath = remoteDirectory +
    // allFile[currentFile].getName();
    // downLoadDirectory(localDirectoryPath, strremoteDirectoryPath);
    // }
    // }
    // } catch (IOException e) {
    // e.printStackTrace();
    // LOG.info("下载文件夹失败");
    // return false;
    // }
    // return true;
    // }

    /*** 
    * 下载文件 
    * @param remoteFileName 待下载文件名称 
    * @param localDires 下载到当地那个路径下 
    * @param remoteDownLoadPath remoteFileName所在的路径 
    * */
    // public boolean downloadFile(String remoteFileName, String localDires,
    // String remoteDownLoadPath) {
    // String strFilePath = localDires + remoteFileName;
    // BufferedOutputStream outStream = null;
    // boolean success = false;
    // try {
    // this.ftpClient.changeWorkingDirectory(remoteDownLoadPath);
    // outStream = new BufferedOutputStream(new FileOutputStream(strFilePath));
    // LOG.info(remoteFileName + "开始下载....");
    // success = this.ftpClient.retrieveFile(remoteFileName, outStream);
    // if (success == true) {
    // LOG.info(remoteFileName + "成功下载到" + strFilePath);
    // return success;
    // }
    // } catch (Exception e) {
    // e.printStackTrace();
    // LOG.error(remoteFileName + "下载失败");
    // } finally {
    // if (null != outStream) {
    // try {
    // outStream.flush();
    // outStream.close();
    // } catch (IOException e) {
    // e.printStackTrace();
    // }
    // }
    // }
    // if (success == false) {
    // LOG.error(remoteFileName + "下载失败!!!");
    // }
    // return success;
    // }

    /**
     * 删除文件
     * 
     * @param fileVO
     * @return
     * @throws SocketException
     * @throws IOException
     */
    public boolean deleteFile(FtpFileVO fileVO, FTPClient client) throws SocketException, IOException {

        if (null == fileVO) {
            return false;
        }
        if (StringUtil.isNullString(fileVO.getFtpPath())) {
            return false;
        }
        if (StringUtil.isNullString(fileVO.getFileName())) {
            return false;
        }

        boolean result = false;

        try {

            String ftpDir = fileVO.getFtpPath().replace("\\", "/").replaceAll("/+", "/");
            if (!ftpDir.endsWith("/")) {
                ftpDir += "/";
            }
            result = client.deleteFile(ftpDir + fileVO.getFileName());

        } finally {
            client.changeWorkingDirectory("/");
        }

        return result;
    }

}
