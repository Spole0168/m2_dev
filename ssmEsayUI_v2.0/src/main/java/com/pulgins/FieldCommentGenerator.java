package com.pulgins;

import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.internal.DefaultCommentGenerator;
/**
 * 增加实体类注释 内容数据库表字段的备注
 * @author Spole
 *
 */
public class FieldCommentGenerator extends DefaultCommentGenerator {

	@Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * " + getColumnDbTypeInfo(introspectedColumn) + "<br>");
        field.addJavaDocLine(" * " + (introspectedColumn.getRemarks() != null ? introspectedColumn.getRemarks() : ""));
        field.addJavaDocLine(" */");
    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
    }
    
    /**
     * 获得数据库中字段 的属性 和 类型信息
     * @param introspectedColumn
     * @return
     */
    private String getColumnDbTypeInfo(IntrospectedColumn introspectedColumn) {
        StringBuilder sb = new StringBuilder();
        sb.append(introspectedColumn.getJdbcTypeName());
        if (introspectedColumn.getLength() > 0) {
            sb.append("(");
            sb.append(introspectedColumn.getLength());
            if (introspectedColumn.getScale() > 0) {
                sb.append(",").append(introspectedColumn.getScale());
            }
            sb.append(")");
        }
        if (introspectedColumn.getDefaultValue() != null) {
            sb.append(" 默认值[" + introspectedColumn.getDefaultValue() + "]");
        }
        if (!introspectedColumn.isNullable()) {
            sb.append(" 必填");
        }
        return sb.toString();
    }
}
