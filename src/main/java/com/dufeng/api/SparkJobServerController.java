package com.dufeng.api;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluebreezecf.tools.sparkjobserver.api.ISparkJobServerClient;
import com.bluebreezecf.tools.sparkjobserver.api.ISparkJobServerClientConstants;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobResult;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobServerClientException;
import com.bluebreezecf.tools.sparkjobserver.api.SparkJobServerClientFactory;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@org.springframework.web.bind.annotation.RestController
public class SparkJobServerController {

    @RequestMapping("/sparkjobserver")
    public String orders(@RequestParam(value = "sql", required = true) String sql) {
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
            SparkJobResult result = client.startJob("sql=\"" + sql + "\"", params);
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
            
            /*String str = result.getResult().substring(1);
            List<String> list = regex(str);*/
            
            System.out.println(result.getResult());
            
            Type listType = new TypeToken<List<String>>() {}.getType();
            List<String> list = new Gson().fromJson(result.getResult(), listType);
            
            StringBuilder categories = new StringBuilder().append("[");
            StringBuilder orders = new StringBuilder().append("[");
            for(String s : list) {
                List<String> orderStat = new Gson().fromJson(s, listType);
                categories.append("\"" + orderStat.get(0) + "\",");
                orders.append("\"" + orderStat.get(1) + "\",");
            }
            
            String c = categories.substring(0, categories.length() - 1) + "]";
            String o = orders.substring(0, orders.length() - 1) + "]";
            
            return "{\"categories\": " + c + ", \"data\": " + o + "}";
        } catch (SparkJobServerClientException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    /*public static void main(String[] args) {
        String str = "[\"[wap,5506]\", \"[weixin,36471]\", \"[pc,8318]\", \"[dm,1284]\", \"[ott,781]\", \"[app,745970]\", \"[null,1269]\", \"[sms,41]\", \"[null,505574]\"]";
        str = str.substring(1);
        List<String> list = test(str);
        for(int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }*/
    
    private static List<String> regex(String str) {
        List<String> list=new ArrayList<String>();  
        Pattern p = Pattern.compile("(\\[[^\\]]*\\])");  
        Matcher m = p.matcher(str);  
        while(m.find()){
            if (m.group().substring(1, m.group().length()-1).contains("null")) {
                break;
            }
            list.add(m.group().substring(1, m.group().length()-1));  
        }  
        return list;  
    }
}
