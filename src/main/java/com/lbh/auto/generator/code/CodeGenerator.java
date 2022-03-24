package com.lbh.auto.generator.code;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.base.CaseFormat;
import com.lbh.auto.generator.code.enums.TemplateEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mybatis-plus 代码生成器
 * @Author lbh
 * @Date 2020/10/26 23:23
 * @Version V1.0
 **/
public class CodeGenerator {

    protected String url = "jdbc:mysql://127.0.0.1:3306/data?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    protected String driverName = "com.mysql.cj.jdbc.Driver";
    protected String username = "root";
    protected String password = "123456";
    /**
     * 逻辑删除标识字段名称 （需要自定义配置）
     */
    protected String logicDeleteFieldName = "";
    protected String tablePrefix = "data";
    /**
     * 模块名
     **/
    protected String moduleName = "com.springboot";

    /**
     * 文件地址 （不可修改配置）
     */
    protected String filePath = System.getProperty("user.dir");
    /**
     * 项目路径 （不可修改配置）
     */
    protected String projectPath = "generator";
    protected String author = "linbeihua";

    public void autoGenerator(String tableName) {
        autoGenerator(tableName, null);
    }

    public void autoGenerator(String tableName, String entityName) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        mpg.setGlobalConfig(globalConfig(entityName));
        mpg.setDataSource(dataSourceConfig());
        mpg.setPackageInfo(packageConfig());
        mpg.setTemplate(templateConfig());
        mpg.setCfg(injectionConfig(entityName));
        mpg.setStrategy(strategyConfig(tableName));
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    protected GlobalConfig globalConfig(@Nullable String entityName) {
        GlobalConfig gc = new GlobalConfig();
        // 生成文件的输出目录
        gc.setOutputDir(projectPath);
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        gc.setBaseResultMap(true);
        // 开启 baseColumnList（XML columnList,生成基本的SQL片段）
        gc.setBaseColumnList(true);
        // 实体属性 Swagger2 注解
        gc.setSwagger2(true);
        // 开发人员
        gc.setAuthor(author);
        gc.setOpen(false);
        // 自定义文件命名，注意 %s 会自动填充表实体名称！（各层文件名称方式，例如： %Action 生成 UserAction）
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        gc.setServiceName("%sService");
        gc.setServiceImplName("%sServiceImpl");
        gc.setControllerName("%sController");
        if (StringUtils.isNotBlank(entityName)) {
            gc.setEntityName(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, entityName));
        }
        return gc;
    }

    /**
     * 数据源配置
     **/
    protected DataSourceConfig dataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(url);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setTypeConvert(new MySqlTypeConvert() {
            // 自定义数据库表字段类型转换【可选】
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                //	注意！！processTypeConvert存在默认类型转换，如果不是你要的效果请自定义返回，非如下直接返回
                return super.processTypeConvert(globalConfig, fieldType);
            }
        });
        return dsc;
    }

    protected PackageConfig packageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
        //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent(moduleName);
        // Controller包名
        pc.setController("controller");
        // Entity包名
        pc.setEntity("model");
        // Mapper包名
        pc.setMapper("mapper");
        // Mapper XML包名
        pc.setXml("mapper");
        // Service包名
        pc.setService("service");
        // Service Impl包名
        pc.setServiceImpl("service.impl");
        return pc;
    }

    /**
     * 自定义配置
     **/
    protected InjectionConfig injectionConfig(String entityName) {
        // 在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }

            @Override
            public void initTableMap(TableInfo tableInfo) {
                this.setMap(getCfgMap(tableInfo.getName().replaceFirst(tablePrefix, "")));
            }
        };

        List<TemplateCodeNode> nodeList = getNodeList();
        if (CollectionUtils.isNotEmpty(nodeList)) {
            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>();
            nodeList.forEach(node -> {
                // 自定义配置会被优先输出
                focList.add(createOutputFile(node.getTemplatePath(), node.getCodePath()));
            });
            cfg.setFileOutConfigList(focList);
        }
        return cfg;
    }

    protected List<TemplateCodeNode> getNodeList() {
        return null;
    }

    protected Map<String, Object> getCfgMap(String entityName) {
        Map<String, Object> cfgMap = new HashMap<>();
        cfgMap.put("mapperUrl", entityName.replace("_", "/"));
        return cfgMap;
    }

    protected FileOutConfig createOutputFile(String templatePath, String codePath) {
        return new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return codePath;
            }
        };
    }

    /**
     * 配置模板
     **/
    protected TemplateConfig templateConfig() {
        TemplateConfig tc = new TemplateConfig();
        //配置模板（任何一个模块如果设置 空 OR Null 将不生成该模块）
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        tc.setEntity(TemplateEnum.ENTITY.getPath());
        tc.setXml(TemplateEnum.MAPPER_XML.getPath());
        tc.setMapper(TemplateEnum.MAPPER.getPath());
        tc.setService(TemplateEnum.SERVICE.getPath());
        tc.setServiceImpl(TemplateEnum.SERVICEIMPL.getPath());
        tc.setController(TemplateEnum.CONTROLLER.getPath());
        return tc;
    }

    /**
     * 策略配置
     **/
    protected StrategyConfig strategyConfig(String tableName) {
        StrategyConfig strategy = new StrategyConfig();
        // 此处可以修改为您的表前缀（用来去掉不想要的表前缀）
        strategy.setTablePrefix(tablePrefix);
        // 数据库表映射到实体的命名策略（表名生成策略）
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntitySerialVersionUID(true);
        //是否生成实体时，生成字段注解
        strategy.setEntityTableFieldAnnotationEnable(true);
        // 【实体】是否为Lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        strategy.setInclude(tableName);
        // 生成 @RestController 控制器
        strategy.setRestControllerStyle(true);
        return strategy;
    }

    @Data
    @AllArgsConstructor
    static class TemplateCodeNode {
        private String templatePath;
        private String codePath;
    }
}
