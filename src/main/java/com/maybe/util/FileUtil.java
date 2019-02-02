package com.maybe.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Maybe has infinite possibilities
 *
 * 文件相关操作的工具
 *
 * @author Created by HuoXu <2542610526@qq.com> on 2019/2/1
 */
public class FileUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

    /**
     * 替换文本文件的换行符为  LF
     */
    public static void replaceNewlineWithLF(String path) throws Exception {

        // 读
        File file = new File(path);
        FileReader in = new FileReader(file);
        BufferedReader bufIn = new BufferedReader(in);
        // 内存流, 作为临时流
        CharArrayWriter tempStream = new CharArrayWriter();
        // 替换
        String line = null;
        while ((line = bufIn.readLine()) != null) {
            // 将该行写入内存
            tempStream.write(line);
            // 添加换行符
            tempStream.append("\n");
        }
        // 关闭 输入流
        bufIn.close();
        // 将内存中的流 写入 文件
        FileWriter out = new FileWriter(file);
        tempStream.writeTo(out);
        out.close();
    }

    /**
     * @param filePath 文件路径
     */
    public static void tryDeleteFile(String filePath) {
        //尝试删除已存在的文件
        File file = new File(filePath);
        if (file.exists()) {
            boolean delete = file.delete();
            if (delete) {
                LOGGER.debug("删除成功，文件：{}", file.getName());
            } else {
                LOGGER.debug("删除失败，文件：{}", file.getName());
            }
        } else {
            LOGGER.debug("文件不存在");
        }
    }


    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     * 下划线转驼峰
     */
    public static String lineToHump(String str) {
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }
}
