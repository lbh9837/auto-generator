package com.lbh.auto.generator.code;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lbh
 * @version V1.0
 * @date 2022/3/24 0:13
 **/
public class SuZhouBankGenerator extends CodeGenerator {

    public SuZhouBankGenerator() {
        url = "jdbc:mysql://10.114.10.201:3309/data_asset_platform?serverTimezone=Asia/Shanghai&characterEncoding=utf8&useSSL=false&allowPublicKeyRetrieval=true&zeroDateTimeBehavior=CONVERT_TO_NULL";
        password = "Pactera2020$$++201";
        tablePrefix = "data_admin";
    }

    @Override
    protected List<TemplateCodeNode> getNodeList() {
        String tempPathPre = "/templates/freemarker/%s.ftl";
        String filePathPre = filePath + "/" + projectPath + "/";
        List<TemplateCodeNode> nodeList = new ArrayList<>();
        nodeList.add(new TemplateCodeNode(String.format(tempPathPre, "entity.java"), filePathPre, "%s", StringPool.DOT_JAVA));
        nodeList.add(new TemplateCodeNode(String.format(tempPathPre, "mapper.java"), filePathPre, "%sMapper", StringPool.DOT_JAVA));
        nodeList.add(new TemplateCodeNode(String.format(tempPathPre, "mapper.xml"), filePathPre, "%sMapper", StringPool.DOT_XML));
        nodeList.add(new TemplateCodeNode(String.format(tempPathPre, "service.java"), filePathPre, "%sService", StringPool.DOT_JAVA));
        nodeList.add(new TemplateCodeNode(String.format(tempPathPre, "serviceImpl.java"), filePathPre, "%sServiceImpl", StringPool.DOT_JAVA));
        nodeList.add(new TemplateCodeNode(String.format(tempPathPre, "controller.java"), filePathPre, "%sController", StringPool.DOT_JAVA));
        return nodeList;
    }

    @Override
    protected TemplateConfig templateConfig() {
        TemplateConfig tc = new TemplateConfig();
        tc.setMapper(null);
        tc.setService(null);
        tc.setController(null);
        tc.setEntity(null);
        tc.setXml(null);
        tc.setServiceImpl(null);
        tc.setEntityKt(null);
        return tc;
    }
}
