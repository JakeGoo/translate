package kk;

import java.io.UnsupportedEncodingException;

import com.baidu.TransApi;

import net.sf.json.JSONObject;

public class CallApi {

    // 申请的认证ID
    private static final String APP_ID = XML.APP_ID;
    private static final String SECURITY_KEY = XML.SECURITY_KEY;

    /**
     * 调用call(String, String) 
     */
    public CallApi(){
    	
    }
    public String call(String to, String query) throws UnsupportedEncodingException {
    	// 创建API类对象
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);
        // 以给定字符替换匹配序列
        query = query.replaceAll("\n|\r|\t","");
        // 调用API, 返回结果集
        String result = api.getTransResult(query, "auto", to);
        // 创建json对象, 转换字符串为JSON
        JSONObject jsonobj = JSONObject.fromObject(result);
        if(jsonobj.get("trans_result") == null) {
        	// 显示错误信息
        	new ErrorWin("还没有输入要翻译的文字啦！！");
			return "";
		}
        // 获取指定键的值
        String strResult = jsonobj.get("trans_result").toString();
        // 去除双边符号
        strResult = strResult.replace("[", "").replace("]", "");
        // 再次通过JSON获取最终结果
        JSONObject finallResult = JSONObject.fromObject(strResult);
        // 返回翻译结果
        return finallResult.get("dst").toString();
    }   
}

