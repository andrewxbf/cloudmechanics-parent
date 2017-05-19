package org.com.cn.xu.vo;

import java.io.Serializable;

public class SqlConfigVO implements Serializable {

    private static final long serialVersionUID = 2851849200946061481L;

    private String sqlConfig;

    public String getSqlConfig() {
        return sqlConfig;
    }

    public void setSqlConfig(String sqlConfig) {
        this.sqlConfig = sqlConfig;
    }
}
