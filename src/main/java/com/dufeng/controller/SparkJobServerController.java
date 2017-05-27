package com.dufeng.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bluebreezecf.tools.sparkjobserver.api.ISparkJobServerClient;
import com.bluebreezecf.tools.sparkjobserver.api.ISparkJobServerClientConstants;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobResult;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobServerClientException;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobServerClientFactory;

@Controller
public class SparkJobServerController {

    @RequestMapping("/sparkjobserver")
    public String orders() {
        try {
            ISparkJobServerClient client = SparkJobServerClientFactory.getInstance().createSparkJobServerClient("http://hadoop255:8090");

            //GET /jars
            /*List<SparkJobJarInfo> jarInfos = client.getJars();
            for(SparkJobJarInfo jarInfo : jarInfos) {
                System.out.println(jarInfo);
            }*/

            //POST /jars/<appName>
            //client.uploadSparkJobJar(new File("/home/yandufeng/Desktop/spark-test.jar"), "spark-test");

            //GET /contexts
            /*List<String> contexts = client.getContexts();
            for(String cxt : contexts) {
                System.out.println(cxt);
            }*/

            //POST /contexts/<name> -- Create context with name ctxTest and null parameter
            //client.createContext("ctxTest", null);

            //POST /contexts/<name> -- Create context with parameters
            /*Map<String, String> params = new HashMap<String, String>();
            params.put(ISparkJobServerClientConstants.PARAM_MEM_PER_NODE, "512m");
            params.put(ISparkJobServerClientConstants.PARAM_NUM_CPU_CORES, "10");
            client.createContext("ctxTest2", params);*/

            //DELETE /contexts/<name>
            //client.deleteContext("ctxTest");

            //GET /jobs
            /*List<SparkJobInfo> jobInfos = client.getJobs();
            for(SparkJobInfo jobInfo : jobInfos) {
                System.out.println(jobInfo);
            }*/

            //POST /jobs --- Create a new job
            Map<String, String> params = new HashMap<String, String>();
            params.put(ISparkJobServerClientConstants.PARAM_APP_NAME, "ff");
            params.put(ISparkJobServerClientConstants.PARAM_CLASS_PATH, "spark.jobserver.JSparkHiveJob");
            params.put(ISparkJobServerClientConstants.PARAM_SYNC, "true");
            params.put(ISparkJobServerClientConstants.PARAM_CONTEXT, "fenghive");
            //1.start a spark job asynchronously and just get the status information
            SparkJobResult result = client.startJob("sql=\" select web_ord_id from ld.orders limit 20 \"", params);
            System.out.println(result);

            //2.start a spark job synchronously and wait until the result
           /* params.put(ISparkJobServerClientConstants.PARAM_CONTEXT, "cxtTest2");
            params.put(ISparkJobServerClientConstants.PARAM_SYNC, "true");
            result = client.startJob("input.string= fdsafd dfsf blullkfdsoflaw fsdffdsfsfs", params);
            System.out.println(result);*/
            
            //GET /jobs/<jobId>---Gets the result or status of a specific job
           /* result = client.getJobResult("fdsfsfdfwfef");
            System.out.println(result);*/
            
            //GET /jobs/<jobId>/config - Gets the job configuration
            /*SparkJobConfig jobConfig = client.getConfig("fdsfsfdfwfef");
            System.out.println(jobConfig);*/
        } catch (SparkJobServerClientException e) {
            e.printStackTrace();
        }
        
        return "index";
    }
}