package com.lbh.auto.generator.code.enums;

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

    ENTITY("entity", "/templates/freemarker/entity.java"),
    MAPPER("mapper", "templates/freemarker/mapper.java"),
    MAPPER_XML("mapper_xml", "templates/freemarker/mapper.xml"),
    SERVICE("service", "/templates/freemarker/service.java"),
    SERVICEIMPL("serviceImpl", "/templates/freemarker/serviceImpl.java"),
    CONTROLLER("controller", "/templates/freemarker/controller.java"),
    ;

    private String name;
    private String path;
}
