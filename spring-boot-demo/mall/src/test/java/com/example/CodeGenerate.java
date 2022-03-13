package com.example;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.example.mall.MallApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * 遇到问题多查看官方文档：https://www.mybatis-plus.com/guide/generator.html
 * <p>
 * 代码生成器
 *
 * @author KPQ
 * @date 2022/2/9
 */
@SpringBootTest(classes = MallApp.class)
@Slf4j
public class CodeGenerate {

    @Autowired
    private DataSourceProperties dataSource;

    /**
     * 项目路径
     */
    private static final String projectPath = System.getProperty("user.dir");

    /**
     * 要生成的表名称
     */
    private static final String tableNames = "sys_user,role,resource,user_role,role_resource";

    @Test
    public void generate() {
        //最终的代码生成
        config().execute();
    }

    private AutoGenerator config() {
        AutoGenerator generator = new AutoGenerator()
                .setDataSource(getDataSourceConfig())
                .setGlobalConfig(getGlobalConfig())
                .setStrategy(getStrategyConfig())
                .setPackageInfo(getPackageConfig())
                .setTemplate(getTemplateConfig())
                //设置模板引擎
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setCfg(getInjectionConfig());
        return generator;
    }


    /**
     * 数据源配置：连接数据库
     */
    private DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig().
                setUrl(dataSource.getUrl())
                .setDriverName(dataSource.getDriverClassName())
                .setUsername(dataSource.getUsername())
                .setPassword(dataSource.getPassword());
    }

    /**
     * 全局信息设置：设置文件输出路径、作者信息、是否覆盖文案金等
     */
    private GlobalConfig getGlobalConfig() {
        final String outPutDir = projectPath + "/src/main/java";
        final String author = "kpq";
        GlobalConfig globalConfig = new GlobalConfig()
                .setOutputDir(outPutDir).setAuthor(author)
                //是否打开输出目录
                .setOpen(false).setSwagger2(true).setBaseResultMap(false)
                //是否覆盖已有的文件，设置service实现类的名称
                .setFileOverride(true).setServiceImplName("%sService");
        return globalConfig;
    }

    /**
     * 生成包名设置
     */
    private PackageConfig getPackageConfig() {
        //父级目录
        final String parentPath = "com.example.mall";
        //设置service实现类所在的包名
        final String servicePackage = "service";
        PackageConfig packageConfig = new PackageConfig()
                .setParent(parentPath).setModuleName("").setService("")
                .setServiceImpl(servicePackage);
        return packageConfig;
    }

    /**
     * 策略配置
     */
    private StrategyConfig getStrategyConfig() {
        //实体类要继承的超类
        final String superEntityClass = "com.example.mall.entity.BaseEntity";
        //超类当中的字段
        StrategyConfig strategy = new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass(superEntityClass)
                .setSuperEntityColumns("id","create_time","update_time")
                .setEntityLombokModel(true)
                .setInclude(tableNames.split(","));
        return strategy;
    }

    /**
     * 模板配置：配置自定义模板
     */
    private TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig()
                .setXml(null).setService("").setController("")
                .setServiceImpl("templates/customizeServiceImpl.java");
        return templateConfig;
    }

    private InjectionConfig getInjectionConfig() {
        final String templatePath = "/templates/mapper.xml.ftl";
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + getPackageConfig().getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

}
