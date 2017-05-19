package org.com.cn.xu.util.ibatis.plugin;

import java.io.InputStream;
import java.io.Serializable;

/**
 * 文件基础类
 * @Title: FileData.java
 * @Package com.framework.servlet.request
 * @Description:用于文件上传，下载
 * @author maxwell
 * @version V1.0
 * @date
 */
public class FileData implements Serializable{
 
	private static final long serialVersionUID = -2526953375941151109L;
	
	private Integer companyId;
	
	private String fileId;
	
	private String filename;
	
	private String ext;
	
	private Long length;
	
	private boolean success;
	private InputStream stream;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public InputStream getStream() {
		return stream;
	}
	public void setStream(InputStream stream) {
		this.stream = stream;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
}
