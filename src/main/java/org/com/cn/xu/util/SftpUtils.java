package org.com.cn.xu.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

public class SftpUtils {
    private static Logger log = Logger.getLogger(SftpUtils.class);

    private ChannelSftp sftp = null;
    private Session sshSession = null;

    /**
     * 连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    public ChannelSftp connect(String host, int port, String username, String password) {
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            sshSession = jsch.getSession(username, host, port);
            System.out.println("Session created.");
            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            System.out.println("Session connected.");
            System.out.println("Opening Channel.");
            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            sftp = (ChannelSftp) channel;
            System.out.println("Connected to " + host + ".");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return sftp;
    }

    /**
     * 上传文件
     *
     * @param directory  上传的目录
     * @param uploadFile 要上传的文件
     * @param sftp
     */
    public void upload(String directory, String uploadFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     *
     * @param directory    下载目录
     * @param downloadFile 下载的文件
     * @param saveFile     存在本地的路径
     * @param sftp
     * @throws IOException
     */
    public void download(String directory, String downloadFile, String saveFile, ChannelSftp sftp) throws IOException {
        // byte[] bytes = null;
        FileOutputStream output = null;
        BufferedOutputStream bos = null;
        // FileInputStream input = null;
        try {
            // directory = new String(directory.getBytes("GBK"), "iso-8859-1");
            // saveFile = new String(saveFile.getBytes("UTF-8"), "iso-8859-1");
            // saveFile = URLDecoder.decode(saveFile, "GBK");
            sftp.cd(directory);
            File file = new File(saveFile);
            // 目录不存在的情况下，创建目录。
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                // 创建文件
                file.createNewFile();
                // file = new File(saveFile);
            }

            output = new FileOutputStream(file);
            bos = new BufferedOutputStream(output);
            sftp.get(downloadFile, bos);

            /*// 将下载的文件转换为byte[]
            input = new FileInputStream(file);
            // 获取文件大小
            long length = file.length();
            if (length > Integer.MAX_VALUE) {
                // 文件太大，无法读取
                throw new IOException("File is to large " + file.getName());
            }
            // 创建一个数据来保存文件数据
            bytes = new byte[(int) length];
            // 读取数据到byte数组中
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length && (numRead = input.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset < bytes.length) {
                throw new IOException("Could not completely read file " + file.getName());
            }*/

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the input stream and return bytes
            if (null != output) {
                output.close();
            }
            if (null != bos) {
                bos.close();
            }
            /*if (null != input) {
                input.close();
            }*/
        }

        /*return bytes;*/
    }

    /**
     * 批量下载文件
     *
     * @param remotPath：远程下载目录(以路径符号结束,可以为相对路径eg:/assess/sftp/jiesuan_2/2014/)
     * @param localPath：本地保存目录(以路径符号结束,D:\Duansha\sftp\)
     * @param fileFormat：下载文件格式(以特定字符开头,为空不做检验)
     * @param fileEndFormat：下载文件格式(文件格式)
     * @param del：下载后是否删除sftp文件
     * @return
     * @throws IOException
     */
    public List<String> batchDownLoadFile(String remotePath, String localPath) throws IOException {
        List<String> filenames = new ArrayList<String>();
        try {
            // connect();

            Vector<?> v = listFiles(remotePath, sftp);
            // sftp.cd(remotePath);
            if (v.size() > 0) {
                // System.out.println("本次处理文件个数不为零,开始下载...fileSize=" +
                // v.size());
                Iterator<?> it = v.iterator();
                while (it.hasNext()) {
                    LsEntry entry = (LsEntry) it.next();
                    String filename = entry.getFilename();
                    if (".".equals(filename) || "..".equals(filename)) {
                        continue;
                    }
                    SftpATTRS attrs = entry.getAttrs();
                    String localfilepath = localPath + filename;
                    // File localFile = new File(localfilepath);

                    // 如果是目录
                    if (attrs.isDir()) {
                        // 如果本地文件夹不存在就创建
                        // localFile.mkdir();
                        // 转到ftp文件夹目录下
                        String ftpfp = remotePath + filename + "/";
                        // 转到本地文件夹目录下
                        String localfp = localfilepath + File.separator;
                        // 递归调用
                        this.batchDownLoadFile(ftpfp, localfp);

                    }

                    // 如果是文件
                    if (!attrs.isDir()) {
                        // String localFileName = localPath + filename;
                        if (filename.toLowerCase().endsWith(".sql")) {
                            downloadFile(remotePath, filename, localPath, filename);
                        }
                        // else {
                        // // 判断当前文件夹是否为空，为空就删除当前文件夹
                        // File lFile = new File(localPath);
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
            if (log.isInfoEnabled()) {
                log.info("download file is success:remotePath=" + remotePath + " and localPath=" + localPath
                        + ",file size is " + v.size());
            }
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            sftp.disconnect();
            sftp.exit();
            // this.disconnect();
        }
        return filenames;
    }

    /**
     * 下载单个文件
     *
     * @param remotPath：远程下载目录(以路径符号结束)
     * @param remoteFileName：下载文件名
     * @param localPath：本地保存目录(以路径符号结束)
     * @param localFileName：保存文件名
     * @return
     * @throws IOException
     */
    public boolean downloadFile(String remotePath, String remoteFileName, String localPath, String localFileName)
            throws IOException {
        FileOutputStream fieloutput = null;
        try {
            sftp.cd(remotePath);
            String pathTmp = localPath + localFileName;
            File file = new File(pathTmp);
            // mkdirs(pathTmp);
            // 目录不存在的情况下，创建目录。
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                // 创建文件
                file.createNewFile();
                // file = new File(saveFile);
            }
            fieloutput = new FileOutputStream(file);
            sftp.get(remotePath + remoteFileName, fieloutput);
            if (log.isInfoEnabled()) {
                log.info("===DownloadFile:" + remoteFileName + " success from sftp.");
            }
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        } finally {
            if (null != fieloutput) {
                try {
                    fieloutput.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    /*public static void main(String[] args) {
        SftpUtils sftp = new SftpUtils();
        String remotePath = "/home/TyCpptUpdate/V1.0.019_CP160461(最终成果)/sqlUpdate/";
        // String remotePath =
        // "/home/TyCpptUpdate/V1.0.026_CP170073(最终成果)/sql_update/";
        String localPath = "D:\\temp\\河北17年3月云厅1.0里程碑升级包\\sql_update\\temp\\pt\\";

        try {
            sftp.connect("192.168.29.102", 22, "nfpt", "nfpt");
            sftp.batchDownLoadFile(remotePath, localPath);
        } catch (IOException e) {

        } finally {
            sftp.disconnect();
        }
    }*/

    /**
     * 删除文件
     *
     * @param directory  要删除文件所在目录
     * @param deleteFile 要删除的文件
     * @param sftp
     */
    public void delete(String directory, String deleteFile, ChannelSftp sftp) {
        try {
            sftp.cd(directory);
            sftp.rm(deleteFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 列出目录下的文件
     *
     * @param directory 要列出的目录
     * @param sftp
     * @return
     * @throws SftpException
     */
    public Vector<?> listFiles(String directory, ChannelSftp sftp) throws SftpException {
        // sftp.setFilenameEncoding("GBK");
        return sftp.ls(directory);
    }

    /**
     * 关闭连接
     */
    public void disconnect() {
        if (this.sftp != null) {
            if (this.sftp.isConnected()) {
                this.sftp.disconnect();
                if (log.isInfoEnabled()) {
                    log.info("sftp is closed already");
                }
            }
        }
        if (this.sshSession != null) {
            if (this.sshSession.isConnected()) {
                this.sshSession.disconnect();
                if (log.isInfoEnabled()) {
                    log.info("sshSession is closed already");
                }
            }
        }
    }

}