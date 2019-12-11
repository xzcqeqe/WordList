package com.example.wordlistsecond;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebActivity extends AppCompatActivity  {

   EditText  e1;
   TextView  t1;
   Button    b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





        setContentView(R.layout.activity_web);
        b1=(Button) findViewById(R.id.search);
        e1=(EditText) findViewById(R.id.editText);
        t1 =(TextView) findViewById(R.id.textView);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String word = e1.getText().toString();
               queryWord(word);
            }
        });
    }
    public void queryWord(String s) {
        final String url0 = "http://fanyi.youdao.com/openapi.do?keyfrom=ghyghyghy&key=1853216072&type=data&doctype=json&version=1.1&q="+s;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url0).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();

    }
    private void parseJSONWithJSONObject(String jsonData) {
        String message=null;

        try {
            JSONArray jsonArray = new JSONArray("["+jsonData+"]");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject != null) {
                    String errorCode = jsonObject.getString("errorCode");
                    if (errorCode.equals("20")) {
                        Toast.makeText(getApplicationContext(), "要翻译的文本过长", Toast.LENGTH_SHORT);
                    } else if (errorCode.equals("30 ")) {
                        Toast.makeText(getApplicationContext(), "无法进行有效的翻译", Toast.LENGTH_SHORT);
                    } else if (errorCode.equals("40")) {
                        Toast.makeText(getApplicationContext(), "不支持的语言类型", Toast.LENGTH_SHORT);
                    } else if (errorCode.equals("50")) {
                        Toast.makeText(getApplicationContext(), "无效的key", Toast.LENGTH_SHORT);
                    } else {
                        // 要翻译的内容
                        String query = jsonObject.getString("query");
                        message = query;
                        // 翻译内容
                        String translation = jsonObject.getString("translation");
                        message += "\t" + translation;
                        // 有道词典-基本词典
                        if (jsonObject.has("basic")) {
                            JSONObject basic = jsonObject.getJSONObject("basic");
                            if (basic.has("phonetic")) {
                                String phonetic = basic.getString("phonetic");
                                message += "\n\t" + phonetic;
                            }
                            if (basic.has("phonetic")) {
                                String explains = basic.getString("explains");
                                message += "\n\t" + explains;
                            }
                        }
                        // 有道词典-网络释义
                        if (jsonObject.has("web")) {
                            String web = jsonObject.getString("web");
                            JSONArray webString = new JSONArray("[" + web + "]");
                            message += "\n网络释义：";
                            JSONArray webArray = webString.getJSONArray(0);
                            int count = 0;
                            while (!webArray.isNull(count)) {

                                if (webArray.getJSONObject(count).has("key")) {
                                    String key = webArray.getJSONObject(count).getString("key");
                                    message += "\n\t<" + (count + 1) + ">" + key;
                                }
                                if (webArray.getJSONObject(count).has("value")) {
                                    String value = webArray.getJSONObject(count).getString("value");
                                    message += "\n\t   " + value;
                                }
                                count++;
                            }
                        }
                    }
                }
            }
            t1.setText(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
