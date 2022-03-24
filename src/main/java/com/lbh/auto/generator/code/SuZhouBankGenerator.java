package com.lbh.auto.generator.code;

import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.lbh.auto.generator.code.enums.TemplateEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lbh
 * @version V1.0
 * @date 2022/3/24 0:13
 **/
public class SuZhouBankGenerator extends CodeGenerator {

    public SuZhouBankGenerator() {
//        url = "";
        tablePrefix = "";
    }

    @Override
    protected List<TemplateCodeNode> getNodeList() {
        List<TemplateCodeNode> nodeList = new ArrayList<>();
        nodeList.add(new TemplateCodeNode(TemplateEnum.ENTITY.getPath() + ".ftl", filePath + "/" + projectPath + "/model.java"));
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
