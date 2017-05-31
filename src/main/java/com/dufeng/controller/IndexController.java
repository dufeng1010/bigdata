package com.dufeng.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluebreezecf.tools.sparkjobserver.api.ISparkJobServerClient;
import com.bluebreezecf.tools.sparkjobserver.api.ISparkJobServerClientConstants;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobConfig;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobInfo;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobJarInfo;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobResult;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobServerClientException;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobServerClientFactory;

@Controller
public class IndexController {
    
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }
    
    @RequestMapping("/echarts")
    public String echarts() {
        return "echarts";
    }
}
