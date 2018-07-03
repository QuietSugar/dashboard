package com.maybe.temp;

import com.maybe.pojo.Task;
import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/7/3
 */
public class Temp {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = null;
        JsonGenerator jsonGenerator = null;
        objectMapper = new ObjectMapper();
        try {
            jsonGenerator = objectMapper.getJsonFactory().
                    createJsonGenerator(System.out, JsonEncoding.UTF8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Task> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Task task = new Task();

            String num = String.valueOf(i);
            task.setId(num);
            task.setContent("内容" + num);
            task.setTitle("标题" + num);
            task.setUrl("www.baidu.com");
            task.setRemarks("备注" + num);
            list.add(task);
        }
        String json = objectMapper.writeValueAsString(list);

        System.out.println(json);
    }

}
