package com.pulgins;

import java.io.File;
import java.util.List;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.Plugin;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.exception.ShellException;
import org.mybatis.generator.internal.DefaultShellCallback;
/**
 * 防止存在映射文件 再次追加的问题
 * @author Spole
 *
 */
public class SqlMapPlugin extends PluginAdapter {
	public static final String startRow = "startRow";
	public static final String pageSize = "pageSize";
	
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
	
	 @Override
	    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
	        // startRow = (curPage - 1) * pageSize
		 	// pageSize
	        addLimit(topLevelClass, introspectedTable, startRow);
	        addLimit(topLevelClass, introspectedTable, pageSize);
	        // add the method that get the only Criteria
	        addCriteriaGetter(topLevelClass, introspectedTable);
	        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
	    }

	    @Override
	    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
	        XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
	        isNotNullElement.addAttribute(new Attribute("test", startRow+" != null and "+startRow+" >= 0")); //$NON-NLS-1$ //$NON-NLS-2$
	        isNotNullElement.addElement(new TextElement("limit ${startRow} , ${"+pageSize+"}"));
	        element.addElement(isNotNullElement);
	        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	    }

	    @Override
	    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
	        XmlElement isNotNullElement = new XmlElement("if"); //$NON-NLS-1$
	        isNotNullElement.addAttribute(new Attribute("test", "startRow != null and startRow >= 0")); //$NON-NLS-1$ //$NON-NLS-2$
	        isNotNullElement.addElement(new TextElement("limit ${startRow} , ${pageSize}"));
	        element.addElement(isNotNullElement);
	        return super.sqlMapUpdateByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
	    }

	    @Override
	    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn,
	            IntrospectedTable introspectedTable, Plugin.ModelClassType modelClassType) {

	        return super.modelGetterMethodGenerated(method, topLevelClass, introspectedColumn, introspectedTable, modelClassType);
	    }

	    private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {
	        CommentGenerator commentGenerator = context.getCommentGenerator();
	        Field field = new Field();
	        field.setVisibility(JavaVisibility.PROTECTED);
	        field.setType(FullyQualifiedJavaType.getIntInstance());
	        field.setName(name);
	        field.setInitializationString("-1");
	        commentGenerator.addFieldComment(field, introspectedTable);
	        topLevelClass.addField(field);
	        char c = name.charAt(0);
	        String camel = Character.toUpperCase(c) + name.substring(1);
	        Method method = new Method();
	        method.setVisibility(JavaVisibility.PUBLIC);
	        method.setName("set" + camel);
	        method.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), name));
	        method.addBodyLine("this." + name + "=" + name + ";");
	        commentGenerator.addGeneralMethodComment(method, introspectedTable);
	        topLevelClass.addMethod(method);
	        method = new Method();
	        method.setVisibility(JavaVisibility.PUBLIC);
	        method.setReturnType(FullyQualifiedJavaType.getIntInstance());
	        method.setName("get" + camel);
	        method.addBodyLine("return " + name + ";");
	        commentGenerator.addGeneralMethodComment(method, introspectedTable);
	        topLevelClass.addMethod(method);
	    }

	    private void addCriteriaGetter(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
	        CommentGenerator commentGenerator = context.getCommentGenerator();
	        Method method = new Method();
	        method.setVisibility(JavaVisibility.PUBLIC);
	        method.setName("getCriteria");
	        method.setReturnType(new FullyQualifiedJavaType("Criteria"));
	        method.addBodyLine("if (oredCriteria.size() != 0) {return oredCriteria.get(0);}");
	        method.addBodyLine("Criteria criteria = createCriteriaInternal();");
	        method.addBodyLine("oredCriteria.add(criteria);");
	        method.addBodyLine("return criteria;");
	        commentGenerator.addGeneralMethodComment(method, introspectedTable);
	        topLevelClass.addMethod(method);
	    }

		
	
	public boolean validate(List<String> warnings) {
		return true;
	}

}
