package org.com.cn.xu.vo;

import java.io.Serializable;

public class FtpFileVO implements Serializable {

    private static final long serialVersionUID = 3019347688948175338L;
    private String fileName;

    private String ftpPath;

    private byte[] fileData;

    private int fileSize;

    private boolean isDirectory;

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return the ftpPath
     */
    public String getFtpPath() {
        return ftpPath;
    }

    /**
     * @param ftpPath the ftpPath to set
     */
    public void setFtpPath(String ftpPath) {
        this.ftpPath = ftpPath;
    }

    /**
     * @return the fileData
     */
    public byte[] getFileData() {
        return fileData;
    }

    /**
     * @param fileData the fileData to set
     */
    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }

    /**
     * @return the fileSize
     */
    public int getFileSize() {
        return fileSize;
    }

    /**
     * @param fileSize the fileSize to set
     */
    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * @return the isDirectory
     */
    public boolean isDirectory() {
        return isDirectory;
    }

    /**
     * @param isDirectory the isDirectory to set
     */
    public void setDirectory(boolean isDirectory) {
        this.isDirectory = isDirectory;
    }

}