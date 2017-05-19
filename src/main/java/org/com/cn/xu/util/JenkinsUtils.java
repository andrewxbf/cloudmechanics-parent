package org.com.cn.xu.util;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import net.sf.json.JSONObject;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class JenkinsUtils {
    private static final Logger LOG = Logger.getLogger(JenkinsUtils.class);
    // // Credentials
    private static String userName = null;
    private static String password = null;
    // Jenkins url
    private static String jenkinsUrl = null;

    // // Build name
    // private static String jobName = null;
    // // Build token
    // // private static String buildToken = null;
    // private static String buildId = null;

    public static boolean invokeJenkinsUtils(String jenkinsUrl, String userName, String password) throws Exception {
        boolean isSuccess = false;

        JenkinsUtils.userName = userName;
        JenkinsUtils.password = password;
        // JenkinsUtils.jobName = jobName;
        // JenkinsUtils.buildToken = buildToken;
        // JenkinsUtils.buildId = buildId;
        // JenkinsUtils.jenkinsUrl = "http://" + hostIp + ":" +
        // String.valueOf(hostPort) + jobAbsPath;
        if (!jenkinsUrl.endsWith("/")) {
            jenkinsUrl = jenkinsUrl + "/";
        }
        JenkinsUtils.jenkinsUrl = jenkinsUrl;

        try {
            JSONObject obj = getLastBuildStatus();
            // 上次构建Id
            String lastHistoryBuildId = obj.getString("id");
            String newId = lastHistoryBuildId;
            // 本次构建操作
            buildJob();

            // 轮询获取本次新生成的构建Id,并确定构建结果产生（result不是null值）
            while (newId.equals(lastHistoryBuildId) || "null".equals(obj.getString("result"))) {
                // 线程等待10s，以便新的构建完成,降低while循环次数。
                Thread.currentThread();
                Thread.sleep(10000);

                obj = getLastBuildStatus();
                newId = obj.getString("id");
            }

            // 读取日志内容，判定本次构建是否成功结束。
            String jobName = jenkinsUrl.substring(jenkinsUrl.lastIndexOf("/job/") + 5, jenkinsUrl.length() - 1);
            if ("SUCCESS".equalsIgnoreCase(obj.getString("result"))) {
                isSuccess = true;
                LOG.info("------【" + jobName + "】项目构建成功！------");
            } else {
                throw new Exception("Jenkins构建【" + jobName + "】项目异常，详细信息请查看Jenkins日志！");
            }

            // getBuildLog();
        } catch (ClientProtocolException e) {

        } catch (IOException e) {

        }

        return isSuccess;
    }

    /*public static void main(String[] args) throws Exception {
        //System.out.println("start");
        try {

            String jsonFilePath = "src/main/resources/JsonConfig.json";
            String jenkinsConfig = "jenkinsConfig";

            JsonArray downJsonArray = JSonHandlerUtils.getJsonArray(jsonFilePath, jenkinsConfig);
            for (int i = 0; i < downJsonArray.size(); i++) {
                JsonObject obj = downJsonArray.get(i).getAsJsonObject();
                userName = obj.get(EnumPropertiesUtils.JKS_USER_NAME.getValue() + i).getAsString();
                password = obj.get(EnumPropertiesUtils.JKS_PASSWORD.getValue() + i).getAsString();
                jobName = obj.get(EnumPropertiesUtils.JKS_JOB_NAME.getValue() + i).getAsString();
                buildToken = obj.get(EnumPropertiesUtils.JKS_BUILD_TOKEN.getValue() + i).getAsString();
                buildId = obj.get(EnumPropertiesUtils.JKS_BUILD_ID.getValue() + i).getAsString();
                jenkinsUrl = "http://" + obj.get(EnumPropertiesUtils.JKS_HOST_IP.getValue() + i).getAsString() + ":"
                        + obj.get(EnumPropertiesUtils.JKS_HOST_PORT.getValue() + i).getAsInt()
                        + obj.get(EnumPropertiesUtils.JKS_JOB_ABS_PATH.getValue() + i).getAsString();

                // String result = createJob();// build the job
                // //System.out.println(result);
                // getJobs();
                // getBuilds();
                // build();
                getBuildLog();
            }
        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //System.out.println("end");
    }*/

    public static JSONObject getLastBuildStatus() throws ClientProtocolException, IOException {
        String urlString = jenkinsUrl + "lastBuild/api/json";
        LOG.info(urlString);

        URI uri = URI.create(urlString);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(
                userName, password));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet httpGet = new HttpGet(uri);
        // httpPost.setEntity(reqEntity);
        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpResponse response = httpClient.execute(host, httpGet, localContext);
        String result = EntityUtils.toString(response.getEntity());
        // System.out.println(result);
        JSONObject obj = JSONObject.fromObject(result);
        // System.out.println("=========>>>>" + obj.getString("id"));
        // System.out.println("=========>>>>" + obj.getString("result"));
        return obj;
    }

    public static String getBuilds() throws ClientProtocolException, IOException {
        String urlString = jenkinsUrl + "api/json?tree=builds[number]{0,10}";
        // System.out.println(urlString);

        URI uri = URI.create(urlString);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(
                userName, password));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet httpGet = new HttpGet(uri);
        // httpPost.setEntity(reqEntity);
        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpResponse response = httpClient.execute(host, httpGet, localContext);
        String result = EntityUtils.toString(response.getEntity());
        // System.out.println(result);
        return result;
    }

    public static String getJobs() throws ClientProtocolException, IOException {
        String urlString = jenkinsUrl + "/api/json?tree=jobs[name]{1,3},views[name,jobs[name]{2}]";
        // System.out.println(urlString);

        URI uri = URI.create(urlString);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(
                userName, password));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet httpGet = new HttpGet(uri);
        // httpPost.setEntity(reqEntity);
        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpResponse response = httpClient.execute(host, httpGet, localContext);
        String result = EntityUtils.toString(response.getEntity());
        // System.out.println(result);
        return result;
    }

    public static String updateJobConfig() throws ClientProtocolException, IOException {
        String urlString = jenkinsUrl + "/config.xml";
        // System.out.println(urlString);

        URI uri = URI.create(urlString);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(
                userName, password));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpPost httpPost = new HttpPost(uri);
        FileEntity entity = new FileEntity(new File("E:\\config.xml"));
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "multipart/form-data;charset=UTF-8");
        // httpPost.setEntity(reqEntity);
        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpResponse response = httpClient.execute(host, httpPost, localContext);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    public static String createJob() throws ClientProtocolException, IOException {
        String urlString = jenkinsUrl + "/createItem?name=TestIOSJob2";
        // System.out.println(urlString);
        URI uri = URI.create(urlString);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(
                userName, password));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpPost httpPost = new HttpPost(uri);
        FileEntity entity = new FileEntity(new File("E:\\config.xml"));
        httpPost.setEntity(entity);
        httpPost.setHeader("Content-Type", "application/xml;charset=UTF-8");
        // httpPost.setEntity(reqEntity);
        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpResponse response = httpClient.execute(host, httpPost, localContext);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    public static String getJobConfig() throws ClientProtocolException, IOException {
        String urlString = jenkinsUrl + "/config.xml";
        // System.out.println(urlString);
        URI uri = URI.create(urlString);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(
                userName, password));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet httpGet = new HttpGet(uri);
        // httpPost.setEntity(reqEntity);
        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpResponse response = httpClient.execute(host, httpGet, localContext);
        String result = EntityUtils.toString(response.getEntity());
        return result;
    }

    public static String buildJob() throws ClientProtocolException, IOException {
        String urlString = jenkinsUrl + "/build?delay=0sec";
        LOG.info(urlString);
        URI uri = URI.create(urlString);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(
                userName, password));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet httpGet = new HttpGet(uri);
        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpResponse response = httpClient.execute(host, httpGet, localContext);

        return EntityUtils.toString(response.getEntity());
    }

    public static String getBuildLog() throws ClientProtocolException, IOException {
        String defBuildId = "lastBuild";
        // if (StringUtils.isEmpty(buildId)) {
        // buildId = defBuildId;
        // }
        String urlString = jenkinsUrl + defBuildId + "/logText/progressiveText?start=0";
        // System.out.println(urlString);
        URI uri = URI.create(urlString);
        HttpHost host = new HttpHost(uri.getHost(), uri.getPort(), uri.getScheme());
        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(new AuthScope(uri.getHost(), uri.getPort()), new UsernamePasswordCredentials(
                userName, password));
        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(host, basicAuth);
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultCredentialsProvider(credsProvider).build();
        HttpGet httpGet = new HttpGet(uri);
        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);

        HttpResponse response = httpClient.execute(host, httpGet, localContext);

        LOG.debug(EntityUtils.toString(response.getEntity()));

        return EntityUtils.toString(response.getEntity());

    }
}
