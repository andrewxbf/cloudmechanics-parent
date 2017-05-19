package org.com.cn.xu.vo;

import java.util.List;

public class ActorVO {
    private String sqlConfig;
    private List<JenkinsVO> jenkinsConfig;
    private List<DownloadPackageVO> downloadConfig;
    private List<DownPtSqlVO> downloadPtSqlConfig;
    private List<DownLocalSqlVO> downloadLocalSqlConfig;
    private List<UploadVO> uploadConfig;
    private List<ZipVO> zipConfig;

    public String getSqlConfig() {
        return sqlConfig;
    }

    public void setSqlConfig(String sqlConfig) {
        this.sqlConfig = sqlConfig;
    }

    public List<JenkinsVO> getJenkinsConfig() {
        return jenkinsConfig;
    }

    public void setJenkinsConfig(List<JenkinsVO> jenkinsConfig) {
        this.jenkinsConfig = jenkinsConfig;
    }

    public List<DownloadPackageVO> getDownloadConfig() {
        return downloadConfig;
    }

    public void setDownloadConfig(List<DownloadPackageVO> downloadConfig) {
        this.downloadConfig = downloadConfig;
    }

    public List<DownPtSqlVO> getDownloadPtSqlConfig() {
        return downloadPtSqlConfig;
    }

    public void setDownloadPtSqlConfig(List<DownPtSqlVO> downloadPtSqlConfig) {
        this.downloadPtSqlConfig = downloadPtSqlConfig;
    }

    public List<DownLocalSqlVO> getDownloadLocalSqlConfig() {
        return downloadLocalSqlConfig;
    }

    public void setDownloadLocalSqlConfig(List<DownLocalSqlVO> downloadLocalSqlConfig) {
        this.downloadLocalSqlConfig = downloadLocalSqlConfig;
    }

    public List<UploadVO> getUploadConfig() {
        return uploadConfig;
    }

    public void setUploadConfig(List<UploadVO> uploadConfig) {
        this.uploadConfig = uploadConfig;
    }

    public List<ZipVO> getZipConfig() {
        return zipConfig;
    }

    public void setZipConfig(List<ZipVO> zipConfig) {
        this.zipConfig = zipConfig;
    }

}
