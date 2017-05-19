package org.com.cn.xu;

import org.com.cn.xu.util.AutoUpgradePackageUtils;

public class AutoUpgradePackageService {

    public static void invokeMain() throws Exception {
//    }public static void main(String[] args) throws Exception {
        /**
         * 一：调用Jenkins的API接口，实现自动构建打包；
         * 二：Jenkins打包完成后，从服务器指定路径下载生成的包到本地指定路径下；
         * 三：从服务器上获取项目组和平台的SQL脚本文件，并进行合并（从207上把指定路径下所有的sql文件下载到本地，
         *     然后从102上去取平台的sql脚本，进行同数据库用户的脚本文件合并操作。合并原则：平台脚本在最前面，
         *     合并后，在指定路径下生成新的脚本文件，并把合并源文件删除。）；
         * 四：将项目构建包和合并后的sql脚本文件按照指定路径生成ZIP压缩包；
         * 五：将生成的压缩包上传到服务器上指定路径下，供现场升级发布使用。
         * */
        // Auto package PAR/WAR
        // AutoUpgradePackageUtils.doBuildPackage();

        // DOWNLOAD PAR FROM 192.168.29.102-LINUX
        AutoUpgradePackageUtils.downPackage();

        // DOWNLOAD SQL FROM 192.168.60.207-FTP(WIN)
        // 删除历史文件，重新生成
        AutoUpgradePackageUtils.deleteSqlFiles();
        // 102上sql文件下载
        AutoUpgradePackageUtils.downPtSql();
        // 207上sql文件下载
        AutoUpgradePackageUtils.downLocalSql();
        // 合并
        AutoUpgradePackageUtils.mergeSameSqlFile();

        // ZIP
        AutoUpgradePackageUtils.doZip();

        // UPLOAD
        AutoUpgradePackageUtils.uploadZipPackage();

        // 确保进程结束（目前下载过程无法正常结束，加这句确保进程结束）
        System.exit(0);
    }

}
