package com.raylinetech.ssh2.logistics.common.util;
import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Zip;
import org.apache.tools.ant.types.FileSet;
/**
 * 将文件打包成ZIP压缩文件
 * @author LanP
 * @since 2012-3-1 15:47
 */
public class ZipUtil {
 
    private File zipFile;  
    
    public ZipUtil(String pathName) {  
        zipFile = new File(pathName);  
    }  
      
    public void compress(String srcPathName) {  
        File srcdir = new File(srcPathName);  
        if (!srcdir.exists())  
            throw new RuntimeException(srcPathName + "不存在！");  
          
        Project prj = new Project();  
        Zip zip = new Zip();  
        zip.setProject(prj);  
        zip.setDestFile(zipFile);  
        FileSet fileSet = new FileSet();  
        fileSet.setProject(prj);  
        fileSet.setDir(srcdir);  
        //fileSet.setIncludes("**/*.java"); 包括哪些文件或文件夹 eg:zip.setIncludes("*.java");  
        //fileSet.setExcludes(...); 排除哪些文件或文件夹  
        zip.addFileset(fileSet);  
        zip.execute();  
    }  
	
	/**
	 * 将文件打包成ZIP压缩文件,main方法测试
	 * @param args
	 */
	public static void main(String[] args) {
		ZipUtil zca = new ZipUtil("/home/ma/software/tomcat/webapps/RLOM/downloadfile/package/test.zip");  
        zca.compress("/home/ma/software/tomcat/webapps/RLOM/downloadfile/20140512/");  
	}
}
