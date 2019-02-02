package com.maybe.auto.util;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

/**
 * Maybe has infinite possibilities
 *
 * Velocity工具类
 *
 * @author Created by HuoXu <2542610526@qq.com> on 2019/2/1
 */
public class VelocityUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(VelocityUtil.class);

    /**
     * 根据模板生成文件
     *
     * @param inputVmFilePath 模板文件路径
     * @param outputFilePath  输出文件路径
     * @param context         VelocityContext
     */
    public static void generate(String inputVmFilePath, String outputFilePath, VelocityContext context) throws Exception {
        if (StringUtils.isBlank(inputVmFilePath) || StringUtils.isBlank(outputFilePath)) {
            LOGGER.error("模板和目标文件路径都不可为空");
        }
        try {
            Properties properties = new Properties();
            properties.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, getPath(inputVmFilePath));
            Velocity.init(properties);
            //VelocityEngine engine = new VelocityEngine();
            Template template = Velocity.getTemplate(getFile(inputVmFilePath), "utf-8");
            File outputFile = new File(outputFilePath);
            FileWriterWithEncoding writer = new FileWriterWithEncoding(outputFile, "utf-8");
            template.merge(context, writer);
            writer.close();
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * 根据文件绝对路径获取所处目录
     */
    private static String getPath(String filePath) {
        if (StringUtils.isBlank(filePath)) {
            LOGGER.error("模板和目标文件路径都不可为空");
            return null;
        }
        return filePath.substring(0, filePath.lastIndexOf("/") + 1);
    }

    /**
     * 根据文件绝对路径获取文件
     */
    private static String getFile(String filePath) {
        String file = "";
        if (StringUtils.isNotBlank(filePath)) {
            file = filePath.substring(filePath.lastIndexOf("/") + 1);
        }
        return file;
    }

}
