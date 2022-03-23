package com.lbh.auto.generator;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: mybatis-plus 代码生成器
 * @Author lbh
 * @Date 2020/10/26 23:23
 * @Version V1.0
 **/
public class MysqlGenerator {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/data?characterEncoding=utf8&useSSL=false&serverTimezone=UTC&rewriteBatchedStatements=true";
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";
    /**
     * 逻辑删除标识字段名称 （需要自定义配置）
     */
    private static final String LOGIC_DELETE_FIELD_NAME = "";
    private static final String TABLE_PREFIX = "admin";
    /**
     * 模块名
     **/
    private static final String MODULE_NAME = "com.springboot";

    /**
     * 自定义模板 （不可修改配置）
     */
    private static final String TEMPLATE_ENTITY_JAVA = "/templates/freemarker/entity.java";
    private static final String TEMPLATE_CONTROLLER = "/templates/freemarker/controller.java";
    private static final String TEMPLATE_SERVICE = "/templates/freemarker/service.java";
    private static final String TEMPLATE_SERVICE_IMPL = "/templates/freemarker/serviceImpl.java";
    private static final String TEMPLATE_XML = "templates/freemarker/mapper.xml";
    private static final String TEMPLATE_MAPPER = "templates/freemarker/mapper.java";

    /**
     * 文件地址 （不可修改配置）
     */
    private static final String FILE_PATH = System.getProperty("user.dir");
    /**
     * 项目路径 （不可修改配置）
     */
    private static final String PROJECT_PATH = "code";
    private static final String AUTHOR = "linbeihua";

    private static final List<String> TABLE_LIST = Lists.newArrayList("test_user");

    public static void main(String[] args) {
//        autoGenerator("admin_application_info", "");
        if (CollectionUtils.isNotEmpty(TABLE_LIST)) {
            TABLE_LIST.forEach(table -> {
                autoGenerator(table, "");
            });
        }
    }

    private static void autoGenerator(String tableName, String entityName) {
        if (StringUtils.isBlank(entityName)) {
            entityName = tableName;
        }
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator(createDataSourceConfig());
        mpg.global(createGlobalConfig());
        mpg.packageInfo(createPackageConfig());
        mpg.template(createTemplateConfig());
        mpg.injection(injectionConfig(entityName));
        mpg.strategy(strategyConfig(tableName));
        mpg.execute(new FreemarkerTemplateEngine());
    }

    /**
     * 创建全局配置
     **/
    private static GlobalConfig createGlobalConfig() {
        return new GlobalConfig.Builder()
                .outputDir(PROJECT_PATH) //生成文件的输出目录
                .fileOverride() // 是否覆盖已有文件
                .author(AUTHOR) // 开发人员
                .disableOpenDir()
                .enableSwagger()
                .dateType(DateType.TIME_PACK) // 时间类型对应策略
                .build();
    }

    /**
     * 创建数据源配置
     **/
    private static DataSourceConfig createDataSourceConfig() {
        return new DataSourceConfig.Builder(URL, USERNAME, PASSWORD).build();
    }

    /**
     * 创建包级配置
     **/
    private static PackageConfig createPackageConfig() {
        return new PackageConfig.Builder()
                .parent(MODULE_NAME) //父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
                .controller("controller") // Controller包名
                .entity("model") // Entity包名
                .mapper("mapper") // Mapper包名
                .xml("mapper") // Mapper XML包名
                .service("service") // Service包名
                .serviceImpl("service.impl") // Service Impl包名
                .build();
    }

    /**
     * 注入配置
     */
    private static InjectionConfig.Builder injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
        });
    }

    /**
     * 自定义配置
     **/
    private static InjectionConfig injectionConfig(String entityName) {
        // 在.ftl(或者是.vm)模板中，通过${cfg.abc}获取属性
        Map<String, Object> map = new HashMap<>();
        map.put("mapperUrl", entityName.replace("_", "/"));

        // 设置自定义输出文件, key为文件名称，value为文件路径, 输出目录为 other
//        Map<String, String> customFile = new HashMap<>();
//        customFile.put("mapper.xml", "/templates/mapper.xml.ftl");

//        return injectionConfig().customMap(map).customFile(customFile).build();
        return injectionConfig().customMap(map).build();
    }

    /**
     * 配置模板
     **/
    private static TemplateConfig createTemplateConfig() {
        //配置模板（任何一个模块如果设置 空 OR Null 将不生成该模块）
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        return new TemplateConfig.Builder()
                .entity(TEMPLATE_ENTITY_JAVA)
                .service(TEMPLATE_SERVICE)
                .serviceImpl(TEMPLATE_SERVICE_IMPL)
                .mapper(TEMPLATE_MAPPER)
                .mapperXml(TEMPLATE_XML)
                .controller(TEMPLATE_CONTROLLER)
                .build();
    }

    /**
     * 策略配置
     **/
    private static StrategyConfig strategyConfig(String tableName) {
        StrategyConfig strategyConfig = new StrategyConfig.Builder()
                .addTablePrefix(TABLE_PREFIX)
                .addInclude(tableName)// 此处可以修改为您的表前缀（用来去掉不想要的表前缀）
                .build();

        strategyConfig.entityBuilder()
                .naming(NamingStrategy.underline_to_camel)
                .columnNaming(NamingStrategy.underline_to_camel);

        return strategyConfig;
    }
}
