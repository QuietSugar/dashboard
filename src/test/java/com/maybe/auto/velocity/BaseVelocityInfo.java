package com.maybe.auto.velocity;

import com.maybe.auto.util.VelocityUtil;
import org.apache.velocity.VelocityContext;

/**
 * Maybe has infinite possibilities
 *
 * 基础的模板信息，抽象类，提供抽象方法
 * 规范内容
 * 各自的实现类提供自己的构造，因为参数各不相同
 *
 * @author Created by HuoXu <2542610526@qq.com> on 2019/1/31
 */
public abstract class BaseVelocityInfo {
    /**
     * 生成的目标路径，应当是一个文件的绝对路径
     */
    private String outputFilePath;
    /**
     * 模板文件的路径
     */
    private String inputVmFilePath;

    public BaseVelocityInfo(String outputFilePath, String inputVmFilePath) {
        this.outputFilePath = outputFilePath;
        this.inputVmFilePath = inputVmFilePath;
    }

    public void run(VelocityContext context) throws Exception {
        VelocityUtil.generate(inputVmFilePath, outputFilePath, context);
    }

}
