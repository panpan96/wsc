package com.youngor.wsc.common;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.type.TypeReference;
import com.youngor.wsc.entity.Item;
import com.youzan.open.sdk.client.auth.Token;
import com.youzan.open.sdk.client.core.DefaultYZClient;
import com.youzan.open.sdk.client.core.YZClient;
import com.youzan.open.sdk.client.oauth.model.OAuthToken;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanItemGet;
import com.youzan.open.sdk.gen.v3_0_0.api.YouzanItemSearch;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemGetParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemGetResult;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemSearchParams;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemSearchResult;
import com.youzan.open.sdk.util.http.DefaultHttpClient;
import com.youzan.open.sdk.util.http.HttpClient;
import com.youzan.open.sdk.util.json.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther zhoutf
 * @Date 2019/1/3 9:19
 * @Description
 */
public class HttpUtils {


    public static OAuthToken getToken() {

        HttpClient httpClient = new DefaultHttpClient();
        HttpClient.Params params = HttpClient.Params.custom()

                .add("client_id", "8129319e405c094121")
                .add("client_secret", "9ade302b9ad483ce1d197251d035fda4")
                .add("grant_type", "silent")
                .add("kdt_id","41161051")
                .setContentType(ContentType.APPLICATION_FORM_URLENCODED).build();
        String resp = httpClient.post("https://open.youzan.com/oauth/token", params);
        //  System.out.println(resp);
        if (StringUtils.isBlank(resp) || !resp.contains("access_token")) {
            throw new RuntimeException(resp);
        }
        return JsonUtils.toBean(resp, new TypeReference<OAuthToken>() {
        });
    }


    public static Long getCount(String token)
    {

       // OAuthToken token = getToken();
        YZClient client = new DefaultYZClient(new Token(token)); //new Sign(appKey, appSecret)
        YouzanItemSearchParams youzanItemSearchParams = new YouzanItemSearchParams();
        youzanItemSearchParams.setShowSoldOut(0L);
        YouzanItemSearch youzanItemSearch = new YouzanItemSearch();
        youzanItemSearch.setAPIParams(youzanItemSearchParams);
        YouzanItemSearchResult result = client.invoke(youzanItemSearch);

        return result.getCount();
    }


    public static YouzanItemSearchResult getItemSearchResult(Long pageNo,String token)
    {
       // OAuthToken token = HttpUtils.getToken();
        YZClient client = new DefaultYZClient(new Token(token)); //new Sign(appKey, appSecret)
        YouzanItemSearchParams youzanItemSearchParams = new YouzanItemSearchParams();
        youzanItemSearchParams.setShowSoldOut(0L);
        youzanItemSearchParams.setPageSize(50L);
        youzanItemSearchParams.setPageNo(pageNo);
        YouzanItemSearch youzanItemSearch = new YouzanItemSearch();
        youzanItemSearch.setAPIParams(youzanItemSearchParams);
        YouzanItemSearchResult result = client.invoke(youzanItemSearch);
        return  result;
    }


    public static YouzanItemGetResult.ItemSkuOpenModel[] getItemSkuOpenModel(Long itemId,String token)
    {
       // OAuthToken token = HttpUtils.getToken();
        YZClient client = new DefaultYZClient(new Token(token));
        YouzanItemGetParams youzanItemGetParams = new YouzanItemGetParams();
        youzanItemGetParams.setItemId(itemId);
        YouzanItemGet youzanItemGet = new YouzanItemGet();
        youzanItemGet.setAPIParams(youzanItemGetParams);
        YouzanItemGetResult result = client.invoke(youzanItemGet);
        YouzanItemGetResult.ItemDetailOpenModel item =result.getItem();
        YouzanItemGetResult.ItemSkuOpenModel[] skus = item.getSkus();
        return  skus;
    }


   public static void main(String[] args) {


//       OAuthToken token = HttpUtils.getToken();
//       YZClient client = new DefaultYZClient(new Token(token.getAccessToken())); //new Sign(appKey, appSecret)
//       YouzanItemGetParams youzanItemGetParams = new YouzanItemGetParams();
//
//       youzanItemGetParams.setItemId(452834544L);
//
//       YouzanItemGet youzanItemGet = new YouzanItemGet();
//       youzanItemGet.setAPIParams(youzanItemGetParams);
//       YouzanItemGetResult result = client.invoke(youzanItemGet);
//
//       YouzanItemGetResult.ItemDetailOpenModel item =result.getItem();

//       YouzanItemGetResult.ItemSkuOpenModel[] skus = getItemSkuOpenModel(452834544L);
//       for ( YouzanItemGetResult.ItemSkuOpenModel itemSkuOpenModel:skus
//            ) {
//
//           System.out.println(itemSkuOpenModel.getItemNo());
//       }


   }
}
