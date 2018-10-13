package com.baidu;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class TransApi {
    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String query, String from, String to) throws UnsupportedEncodingException {
        Map<String, String> params = buildParams(query, from, to);
        // 返回从远程得到的结果
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) throws UnsupportedEncodingException {
    	// 构造一个空HashMap
        Map<String, String> params = new HashMap<String, String>();
        // 将每个键值对放入
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis()); // 以毫秒数值返回当前时间,转换为字符串类型
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        // 返回了计算后的字符数组，将签名以MD5加密
        params.put("sign", MD5.md5(src));

        // 返回存入的集合
        return params;
    }

}
