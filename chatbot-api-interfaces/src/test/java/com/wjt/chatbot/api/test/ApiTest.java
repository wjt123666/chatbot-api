package com.wjt.chatbot.api.test;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * @ClassName ApiTest
 * @Description:
 * @Author 86178
 * @Date 2025/3/15 001515:00
 * @Version 1.0
 */
@SpringBootTest
public class ApiTest {

    @Test
    void query_unanswered_questions() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet get = new HttpGet("https://api.zsxq.com/v2/groups/51115228854524/topics?scope=unanswered_questions&count=20");

        //get.addHeader("cookie", "zsxq_access_token=1A6C7039-62E2-4960-8D88-E6FF9FE60F8A_61AB07A50CD0270B; zsxqsessionid=17c08d6b83510e811512cc26eea2f41a; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22814522255514152%22%2C%22first_id%22%3A%22194438c77dd11bb-03d3aecf78074e-26011851-1327104-194438c77de14c2%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTk0NDM4Yzc3ZGQxMWJiLTAzZDNhZWNmNzgwNzRlLTI2MDExODUxLTEzMjcxMDQtMTk0NDM4Yzc3ZGUxNGMyIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiODE0NTIyMjU1NTE0MTUyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22814522255514152%22%7D%2C%22%24device_id%22%3A%22194438c77dd11bb-03d3aecf78074e-26011851-1327104-194438c77de14c2%22%7D");
        get.addHeader("cookie",
                "zsxq_access_token=1A6C7039-62E2-4960-8D88-E6FF9FE60F8A_61AB07A50CD0270B; zsxqsessionid=17c08d6b83510e811512cc26eea2f41a; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22814522255514152%22%2C%22first_id%22%3A%22194438c77dd11bb-03d3aecf78074e-26011851-1327104-194438c77de14c2%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTk0NDM4Yzc3ZGQxMWJiLTAzZDNhZWNmNzgwNzRlLTI2MDExODUxLTEzMjcxMDQtMTk0NDM4Yzc3ZGUxNGMyIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiODE0NTIyMjU1NTE0MTUyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22814522255514152%22%7D%2C%22%24device_id%22%3A%22194438c77dd11bb-03d3aecf78074e-26011851-1327104-194438c77de14c2%22%7D");
        get.addHeader("Content-Type","application/json;charset=utf8");

        CloseableHttpResponse response = httpClient.execute(get);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        }else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    @Test
    void answer() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("https://api.zsxq.com/v2/topics/4848115882422528/answer");
        post.addHeader("cookie",
                "zsxq_access_token=1A6C7039-62E2-4960-8D88-E6FF9FE60F8A_61AB07A50CD0270B; zsxqsessionid=17c08d6b83510e811512cc26eea2f41a; abtest_env=product; sensorsdata2015jssdkcross=%7B%22distinct_id%22%3A%22814522255514152%22%2C%22first_id%22%3A%22194438c77dd11bb-03d3aecf78074e-26011851-1327104-194438c77de14c2%22%2C%22props%22%3A%7B%22%24latest_traffic_source_type%22%3A%22%E7%9B%B4%E6%8E%A5%E6%B5%81%E9%87%8F%22%2C%22%24latest_search_keyword%22%3A%22%E6%9C%AA%E5%8F%96%E5%88%B0%E5%80%BC_%E7%9B%B4%E6%8E%A5%E6%89%93%E5%BC%80%22%2C%22%24latest_referrer%22%3A%22%22%7D%2C%22identities%22%3A%22eyIkaWRlbnRpdHlfY29va2llX2lkIjoiMTk0NDM4Yzc3ZGQxMWJiLTAzZDNhZWNmNzgwNzRlLTI2MDExODUxLTEzMjcxMDQtMTk0NDM4Yzc3ZGUxNGMyIiwiJGlkZW50aXR5X2xvZ2luX2lkIjoiODE0NTIyMjU1NTE0MTUyIn0%3D%22%2C%22history_login_id%22%3A%7B%22name%22%3A%22%24identity_login_id%22%2C%22value%22%3A%22814522255514152%22%7D%2C%22%24device_id%22%3A%22194438c77dd11bb-03d3aecf78074e-26011851-1327104-194438c77de14c2%22%7D");
        post.addHeader("Content-Type", "application/json;charset=utf8");

        String paramJson = "{\n" +
                "  \"req_data\": {\n" +
                "    \"text\": \"自己去百度！\\n\",\n" +
                "    \"image_ids\": [],\n" +
                "    \"silenced\": false\n" +
                "  }\n" +
                "}";

        StringEntity stringEntity = new StringEntity(paramJson, ContentType.create("text/json", "UTF-8"));
        post.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(post);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String res = EntityUtils.toString(response.getEntity());
            System.out.println(res);
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }
}
