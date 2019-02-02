package com.maybe.auto.util;

import com.maybe.util.FileUtil;
import org.apache.commons.lang.WordUtils;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Maybe has infinite possibilities
 *
 * 自动生成总览
 *
 * @author Created by HuoXu <2542610526@qq.com> on 2019/2/1
 */
public class GeneratorUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorUtil.class);
    private static final Boolean REPLACE_NEWLINE = true;


    private static final String XML_FILE_NAME = "/generatorConfig.xml";
    private static final String PROPERTIES_FILE_NAME = "/generatorConfig.properties";

    /**
     * 运行 MybatisGenerator
     * 会生成4个文件  实体类，实体类Example，Mapper文件，Mapper的xml文件
     */
    public static void runMybatisGenerator() throws Exception {

        LOGGER.info("========== 开始运行MybatisGenerator ==========");
        //由于xml不会覆盖，而是追加，所以先删除xml文件
        String xmlFilePath = getMapperXmlFilePath();
        FileUtil.tryDeleteFile(xmlFilePath);
        List<String> warnings = new ArrayList<>();
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(GeneratorUtil.class.getResourceAsStream(XML_FILE_NAME));
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            LOGGER.info(warning);
        }

        //替换生成的文件的换行符
        if (REPLACE_NEWLINE) {
            FileUtil.replaceNewlineWithLF(xmlFilePath);
            FileUtil.replaceNewlineWithLF(getModelPath());
            FileUtil.replaceNewlineWithLF(getExamplePath());
            FileUtil.replaceNewlineWithLF(getMapperFilePath());
        }
        LOGGER.info("========== 开始运行MybatisGenerator ==========");
    }


    private static String databaseName;
    private static Properties properties;
    /**
     * 类似  src/main/java
     */
    private static String absoluteProjectPath;

    static {
        try {
            properties = new Properties();
            properties.load(GeneratorUtil.class.getResourceAsStream(PROPERTIES_FILE_NAME));
            String projectPath = properties.getProperty("target.project");
            databaseName = WordUtils.capitalize(FileUtil.lineToHump(properties.getProperty("database.name")));
            String project = Paths.get(GeneratorUtil.class.getResource("/").toURI()).getParent().getParent().toString();
            absoluteProjectPath = Paths.get(project, projectPath.split("/")).toString();
        } catch (Exception e) {
            LOGGER.error("初始化失败", e);
        }
    }

    /**
     * 实体类的路径
     */
    private static String getModelPath() {
        String modelPath = properties.getProperty("model.package");
        return getFilePath(modelPath, ".java");
    }

    /**
     * Example类的路径
     */
    private static String getExamplePath() {
        String modelPath = properties.getProperty("model.package");
        return getFilePath(modelPath, "Example.java");
    }

    /**
     * Mapper文件的路径
     */
    private static String getMapperFilePath() {
        String xmlPackageName = properties.getProperty("dao.package");
        return getFilePath(xmlPackageName, "Mapper.java");
    }

    /**
     * xml 文件的路径
     */
    private static String getMapperXmlFilePath() {
        String xmlPackageName = properties.getProperty("xml.mapper.package");
        return getFilePath(xmlPackageName, "Mapper.xml");
    }

    /**
     * 文件的路径
     */
    private static String getFilePath(String packageName, String fileName) {
        String[] split = packageName.split("\\.");
        String xmlDir = Paths.get(absoluteProjectPath, split).toString();
        return Paths.get(xmlDir, databaseName + fileName).toString();
    }
}
