package com.maybe.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Maybe has infinite possibilities
 *
 * @author Created by sugar on 2018/7/3
 */
@RestController
@RequestMapping(value = "test", method = {RequestMethod.POST})
public class TestController {
    @RequestMapping("/list")
    public Map list(
            @RequestParam(value = "start", defaultValue = "0") Integer start,
            @RequestParam(value = "length", defaultValue = "10") Integer length,
            @RequestParam(value = "draw", defaultValue = "0") Integer draw
    ) throws Exception {
        //总记录数
        final long totalNum = 37;
        long end = start + length;

        end = end < totalNum ? end : totalNum;

        List<Map<String, String>> dataList = new ArrayList<>();
        for (int i = start; i < end; i++) {
            //value是   行数,列数
            Map<String, String> row = new HashMap<>(4);
            row.put("column1", i + ",1");
            row.put("column2", i + ",2");
            row.put("column3", i + ",3");
            row.put("column4", i + ",4");
            dataList.add(row);
        }
        Map<Object, Object> info = new HashMap<>(length);
        info.put("data", dataList);
        info.put("total", totalNum);
        info.put("draw", draw);
        return info;
    }
}
