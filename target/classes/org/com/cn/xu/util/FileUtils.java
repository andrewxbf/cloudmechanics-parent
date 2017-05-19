package org.com.cn.xu.util;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.ParsingDetector;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.com.cn.xu.exception.BizException;

public class FileUtils {
    private static final Logger LOG = Logger.getLogger(FileUtils.class);

    public FileUtils() {
    }

    /**   
      *  新建目录   
      *  @param  folderPath  String  如  c:/fqf   
      *  @return  boolean   
      */
    public void newFolder(String folderPath) {
        try {
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    /**   
      *  新建文件   
      *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt   
      *  @param  fileContent  String  文件内容   
      *  @return  boolean   
      */
    public void newFile(String filePathAndName, String fileContent) {

        try {
            String filePath = filePathAndName;
            filePath = filePath.toString(); // 取的路径及文件名
            File myFilePath = new File(filePath);
            /**如果文件不存在就建一个新文件*/
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath); // 用来写入字符文件的便捷类,
                                                                // 在给出 File
                                                                // 对象的情况下构造一个
                                                                // FileWriter 对象
            PrintWriter myFile = new PrintWriter(resultFile); // 向文本输出流打印对象的格式化表示形式,使用指定文件创建不具有自动行刷新的新
                                                              // PrintWriter。
            String strContent = fileContent;
            myFile.println(strContent);
            resultFile.close();

        } catch (Exception e) {
            System.out.println("新建文件操作出错");
            e.printStackTrace();

        }

    }

    /**   
      *  删除文件   
      *  @param  filePathAndName  String  文件路径及名称  如c:/fqf.txt   
      *  @param
      *  @return  boolean   
      */
    public void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            java.io.File myDelFile = new java.io.File(filePath);
            myDelFile.delete();

        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();

        }

    }

    /**   
      *  删除文件夹   
      *  @param    folderPath 文件夹路径及名称  如c:/fqf
      *  @param
      *  @return  boolean   
      */
    public void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹

        } catch (Exception e) {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();

        }

    }

    /**   
      *  删除文件夹里面的所有文件   
      *  @param  path  String  文件夹路径  如  c:/fqf   
      */
    public void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
            }
        }
    }

    /**   
      *  复制单个文件   
      *  @param  oldPath  String  原文件路径  如：c:/fqf.txt   
      *  @param  newPath  String  复制后路径  如：f:/fqf.txt   
      *  @return  boolean   
      */
    public void copyFile(String oldPath, String newPath) {
        try {
            // int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                InputStream inStream = new FileInputStream(oldPath); // 读入原文件
                FileOutputStream fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                // int length;
                while ((byteread = inStream.read(buffer)) != -1) {
                    // bytesum += byteread; //字节数 文件大小
                    // System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();

        }

    }

    /**   
      *  复制整个文件夹内容   
      *  @param  oldPath  String  原文件路径  如：c:/fqf   
      *  @param  newPath  String  复制后路径  如：f:/fqf/ff   
      *  @return  boolean   
      */
    public void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }

    }

    /**   
      *  移动文件到指定目录   
      *  @param  oldPath  String  如：c:/fqf.txt   
      *  @param  newPath  String  如：d:/fqf.txt   
      */
    public void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);

    }

    /**   
      *  移动文件到指定目录   
      *  @param  oldPath  String  如：c:/fqf.txt   
      *  @param  newPath  String  如：d:/fqf.txt   
      */
    public void moveFolder(String oldPath, String newPath) {
        copyFolder(oldPath, newPath);
        delFolder(oldPath);

    }

    // public static void main(String[] args) {
    // FileUtils file = new FileUtils();
    // // file.newFolder("newFolder22222");
    // file.delAllFile("E:/1");
    // }

    // 拷贝文件
    @SuppressWarnings("unused")
    private void copyFile2(String source, String dest) {
        try {
            File in = new File(source);
            File out = new File(dest);
            FileInputStream inFile = new FileInputStream(in);
            FileOutputStream outFile = new FileOutputStream(out);
            byte[] buffer = new byte[10240];
            int i = 0;
            while ((i = inFile.read(buffer)) != -1) {
                outFile.write(buffer, 0, i);
            }// end while
            inFile.close();
            outFile.close();
        }// end try
        catch (Exception e) {

        }// end catch
    }// end copyFile

    public static void mergeSqlFile(String beforeFile, String afterFile) throws Exception {
        BufferedReader br = null;
        BufferedWriter bw = null;
        String str = null;

        try {
            File file = new File(beforeFile);
            if (!file.exists()) {
                file.createNewFile();
            }

            // 判断读取文件的编码字符集
            String fileEncode = getFileEncode(afterFile);
            LOG.info(fileEncode);

            br = new BufferedReader(new InputStreamReader(new FileInputStream(afterFile), fileEncode));

            // 这里的true，是以追加的方式写
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), fileEncode));
            boolean firstLine = false;

            while ((str = br.readLine()) != null) {
                StringBuffer sb = new StringBuffer("");

                if (!firstLine) {
                    firstLine = true;
                    sb.append("\n");
                }
                sb.append("\n" + str);
                bw.write(sb.toString());
                // 使用BufferedWriter时，要flush 不然没有数据
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != br) {
                    br.close();
                }
                if (null != bw) {
                    bw.close();
                }
            } catch (IOException e) {
                throw new Exception();
            }
        }

    }

    public static void getFileName(String filePath, String jsonFilePath, String sqlConfig) throws Exception {
        File f = new File(filePath);
        if (!f.exists()) {
            LOG.info(filePath + " not exists");
            return;
        }

        File fa[] = f.listFiles();
        for (int i = 0; i < fa.length; i++) {
            File fs = fa[i];
            if (fs.isDirectory()) {
                // System.out.println(fs.getName() + " [目录]");
                String newFilePath = filePath + fs.getName() + File.separator;
                getFileName(newFilePath, jsonFilePath, sqlConfig);
            } else {
                // System.out.println(filePath + fs.getName());

                String dbUserName = null;
                // 根据数据库用户名 移动到指定文件夹
                if (fs.getName().contains("_")) {
                    dbUserName = fs.getName().substring(0, fs.getName().lastIndexOf("_")).toUpperCase();
                    LOG.debug(dbUserName);
                } else {
                    LOG.error(filePath + fs.getName() + "文件名称不符合规范！");
                    throw new Exception(fs.getName() + "文件名称不符合规范！");
                }
                String folderPath = JSonHandlerUtils.getJsonObject(jsonFilePath, sqlConfig);
                moveFile2(filePath + fs.getName(), folderPath + dbUserName);

            }
        }

        // 删除原来的文件夹
        new FileUtils().delFolder(filePath);
    }

    public static void moveFile2(String aFilePath, String bFilePath) {
        try {
            // File aFile = new File("E:\\zuidaima_com_a\\zuidaima_com.txt");
            File aFile = new File(aFilePath);
            if (!aFile.exists()) {
                aFile.getParentFile().mkdirs();
                aFile.createNewFile();
            }

            // File bFile = new File("E:\\zuidaima_com_b\\" + aFile.getName());
            String moveToPath = bFilePath + File.separator + aFile.getName();
            File bFile = new File(moveToPath);
            if (!bFile.exists()) {
                bFile.getParentFile().mkdirs();
                // bFile.createNewFile();
            } else {
                // 将sql文件拼接到平台sql文件尾
                mergeSqlFile(aFilePath, moveToPath);
                // 删除拼接sql的源文件
                new FileUtils().delFile(moveToPath);
            }

            // 移动合并后的sql文件到指定路径
            if (aFile.renameTo(bFile)) {
                LOG.info("File is moved successful!");
            } else {
                LOG.error("File is failed to move!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /** 
     * 利用第三方开源包cpdetector获取文件编码格式 
     *  
     * @param path 
     *            要判断文件编码格式的源文件的路径 
     * @author huanglei 
     * @version 2012-7-12 14:05 
     */
    public static String getFileEncode(String path) {
        /* 
         * detector是探测器，它把探测任务交给具体的探测实现类的实例完成。 
         * cpDetector内置了一些常用的探测实现类，这些探测实现类的实例可以通过add方法 加进来，如ParsingDetector、 
         * JChardetFacade、ASCIIDetector、UnicodeDetector。 
         * detector按照“谁最先返回非空的探测结果，就以该结果为准”的原则返回探测到的 
         * 字符集编码。使用需要用到三个第三方JAR包：antlr.jar、chardet.jar和cpdetector.jar 
         * cpDetector是基于统计学原理的，不保证完全正确。 
         */
        CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
        /* 
         * ParsingDetector可用于检查HTML、XML等文件或字符流的编码,构造方法中的参数用于 
         * 指示是否显示探测过程的详细信息，为false不显示。 
         */
        detector.add(new ParsingDetector(false));
        /* 
         * JChardetFacade封装了由Mozilla组织提供的JChardet，它可以完成大多数文件的编码 
         * 测定。所以，一般有了这个探测器就可满足大多数项目的要求，如果你还不放心，可以 
         * 再多加几个探测器，比如下面的ASCIIDetector、UnicodeDetector等。 
         */
        detector.add(JChardetFacade.getInstance());// 用到antlr.jar、chardet.jar
        // ASCIIDetector用于ASCII编码测定
        detector.add(ASCIIDetector.getInstance());
        // UnicodeDetector用于Unicode家族编码的测定
        detector.add(UnicodeDetector.getInstance());
        java.nio.charset.Charset charset = null;
        File f = new File(path);
        try {
            charset = detector.detectCodepage(f.toURI().toURL());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (charset != null)
            return charset.name();
        else
            return null;
    }

    public void closeResource(OutputStreamWriter fos){
        try {
            if (null != fos) {
                fos.close();
            }
        } catch (IOException ioe) {
            LOG.error(ioe.getMessage());
            throw new BizException("资源关闭失败！", null);
        }
    }

    public String getFilePath(String jsonFileName){
        String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();
        filePath = filePath.substring(filePath.indexOf("/") + 1, filePath.lastIndexOf("/target/"));
        filePath = filePath + "/src/main/resources/" + jsonFileName;
        return filePath;
    }

}