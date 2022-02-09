package com.example;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.example.distributedLock.DistributedLockApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * 代码生成器
 *
 * @author KPQ
 * @date 2022/2/9
 */
@SpringBootTest(classes = DistributedLockApp.class)
@RunWith(SpringRunner.class)
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
    private static final String tableNames = "actor";

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
                .setTemplate(getTemplateConfig());
//                .setCfg(getInjectionConfig());
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
                .setOpen(true).setSwagger2(false).setBaseResultMap(false)
                //是否覆盖已有的文件，设置service实现类的名称
                .setFileOverride(false).setServiceImplName("%sService");
        return globalConfig;
    }

    /**
     * 生成包名设置
     */
    private PackageConfig getPackageConfig() {
        //父级目录
        final String parentPath = "com.example.distributedLock";
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
        final String superEntityClass = "com.example.distributedLock.entity.base.BaseEntity";
        //超类当中的字段
        final String superEntityColumns = "last_update";
        StrategyConfig strategy = new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setSuperEntityClass(superEntityClass)
                .setSuperEntityColumns(superEntityColumns)
                .setEntityLombokModel(true)
                .setInclude(tableNames.split(","));
        return strategy;
    }

    /**
     * 模板配置
     */
    private TemplateConfig getTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig()
                .setXml(null).setService("").setController("")
                .setServiceImpl("D:\\opensource\\learningDemo\\spring-boot-module\\distributed_lock\\src\\main\\resources\\templates\\myServiceImpl.java");
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
