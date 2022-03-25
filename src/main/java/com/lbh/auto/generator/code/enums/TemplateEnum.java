package com.lbh.auto.generator.code.enums;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author lbh
 * @version V1.0
 * @date 2022/3/23 20:46
 **/
@Getter
@AllArgsConstructor
public enum TemplateEnum {

    ENTITY("entity", "/templates/freemarker/entity.java", "ftl", StringPool.DOT_JAVA),
    MAPPER("mapper", "templates/freemarker/mapper.java", "ftl", StringPool.DOT_JAVA),
    MAPPER_XML("mapper_xml", "templates/freemarker/mapper.xml", "ftl", StringPool.DOT_XML),
    SERVICE("service", "/templates/freemarker/service.java", "ftl", StringPool.DOT_JAVA),
    SERVICEIMPL("serviceImpl", "/templates/freemarker/serviceImpl.java", "ftl", StringPool.DOT_JAVA),
    CONTROLLER("controller", "/templates/freemarker/controller.java", "ftl", StringPool.DOT_JAVA),
    ;

    private String name;
    private String path;
    private String suffix;
    private String type;
}
