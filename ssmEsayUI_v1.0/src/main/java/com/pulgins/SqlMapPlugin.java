package com.pulgins;

import java.io.File;
import java.util.List;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
/**
 * 防止存在映射文件 再次追加的问题
 * @author Spole
 *
 */
public class SqlMapPlugin extends PluginAdapter {

	public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        DefaultShellCallback shellCallback = new DefaultShellCallback(true);
        File directory;
        try {
            directory = shellCallback.getDirectory(context.getSqlMapGeneratorConfiguration().getTargetProject(), context
                    .getSqlMapGeneratorConfiguration().getTargetPackage());
            File[] listFiles = directory.listFiles();
            String mappingFileName = introspectedTable.getMyBatis3JavaMapperType();//shang.dal.dao.StudentMapper
            mappingFileName = mappingFileName.substring(mappingFileName.lastIndexOf(".")+1);
            if(null!=listFiles && listFiles.length>0){
            	for(File file : listFiles){
            		System.out.println(mappingFileName+"||"+file.getName());
            		if(file.getName().contains(mappingFileName)){
            			file.delete();
            		}
            	}
            }
            File targetFile = new File(directory, mappingFileName);
            if (targetFile.exists()) {
                targetFile.delete();
            }
        } catch (ShellException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return true;
    }
	
	public boolean validate(List<String> warnings) {
		return true;
	}

}
