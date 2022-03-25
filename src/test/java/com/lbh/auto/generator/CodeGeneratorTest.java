package com.lbh.auto.generator;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.google.common.collect.Lists;
import com.lbh.auto.generator.code.CodeGenerator;
import com.lbh.auto.generator.code.SuZhouBankGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author lbh
 * @version V1.0
 * @date 2022/3/23 21:09
 **/
public class CodeGeneratorTest {

    private CodeGenerator codeGenerator;

    @BeforeEach
    public void before() {
        codeGenerator = new CodeGenerator();
    }

    @Test
    public void testAutoGenerator() {
        List<String> tableList = Lists.newArrayList("test_user");

        if (CollectionUtils.isNotEmpty(tableList)) {
            tableList.forEach(table -> codeGenerator.autoGenerator(table));
        }
    }

    @Test
    public void testSuZhouAutoGenerator() {
        codeGenerator = new SuZhouBankGenerator();
        List<String> tableList = Lists.newArrayList("data_api");

        if (CollectionUtils.isNotEmpty(tableList)) {
            tableList.forEach(table -> codeGenerator.autoGenerator(table));
        }
    }
}
