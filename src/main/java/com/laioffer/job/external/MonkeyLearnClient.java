package com.laioffer.job.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.laioffer.job.config.PropertyReader;
import com.laioffer.job.entity.ExtractRequestBody;
import com.laioffer.job.entity.ExtractResponseItem;
import com.laioffer.job.entity.Extraction;
import org.apache.http.HttpEntity;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class MonkeyLearnClient {
  
  public List<Set<String>> extract(List<String> articles) {
    ObjectMapper mapper = new ObjectMapper();
    CloseableHttpClient httpClient = HttpClients.createDefault();
    String EXTRACT_URL = PropertyReader.getProperty("monkeyLearn", "EXTRACT_URL");
    HttpPost request = new HttpPost(EXTRACT_URL);
    request.setHeader("Content-type", "application/json");
    String AUTH_TOKEN = PropertyReader.getProperty("monkeyLearn", "AUTH_TOKEN");
    request.setHeader("Authorization", "Token " + AUTH_TOKEN);
    ExtractRequestBody body = new ExtractRequestBody(articles, 3);
    
    String jsonBody;
    try {
      jsonBody = mapper.writeValueAsString(body);
    } catch (JsonProcessingException e) {
      return Collections.emptyList();
    }
    
    try {
      request.setEntity(new StringEntity(jsonBody));
    } catch (UnsupportedEncodingException e) {
      return Collections.emptyList();
    }
    
    ResponseHandler<List<Set<String>>> responseHandler = response -> {
      if (response.getStatusLine().getStatusCode() != 200) {
        return Collections.emptyList();
      }
      HttpEntity entity = response.getEntity();
      if (entity == null) {
        return Collections.emptyList();
      }
      ExtractResponseItem[] results = mapper.readValue(entity.getContent(), ExtractResponseItem[].class);
      List<Set<String>> keywordList = new ArrayList<>();
      for (ExtractResponseItem result : results) {
        Set<String> keywords = new HashSet<>();
        for (Extraction extraction : result.extractions) {
          keywords.add(extraction.parsedValue);
        }
        keywordList.add(keywords);
      }
      return keywordList;
    };
    
    try {
      return httpClient.execute(request, responseHandler);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }
}
