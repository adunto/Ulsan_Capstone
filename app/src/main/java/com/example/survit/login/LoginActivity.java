package com.example.survit.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.survit.R;
import com.example.survit.home.homeActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/*
    작업 일시 : 2024-01-04
    코드 내용 : 로그인 액티비티
    수정 내용 : 없음 < 수정시 여기에 명시할 것 >

    수정 내용 : 프래그먼트 삭제 & 액티비티 화면으로 대체

 */



public class LoginActivity extends AppCompatActivity {

    private static final String SERVER_URL = "http://10.0.2.2/test.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        TextView startEmail;
        startEmail = (TextView) findViewById(R.id.start_email);
        startEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, homeActivity.class);
                startActivity(intent);
                finish();
            }
        });


        Button kakaotalk, facebook, google;

        // 카카오톡 버튼 클릭시 액션
        kakaotalk = (Button) findViewById(R.id.start_kakao);
        kakaotalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "카카오톡 클릭됨", Toast.LENGTH_LONG).show();
            }
        });

        facebook = (Button) findViewById(R.id.start_facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "페이스북 클릭됨", Toast.LENGTH_LONG).show();
            }
        });

        google = (Button) findViewById(R.id.start_google);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "구글 클릭됨", Toast.LENGTH_LONG).show();
            }
        });
    }

    // 서버 송수신
    private class LoginTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            // 로그인 버튼 액션 시 가져오는 사용자 정보
            String userID = params[0];
            String userPW = params[1];

            // 서버 연결 설정
            try {
                URL url = new URL(SERVER_URL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                // POST 메서드 설정
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                // 데이터 전송
                Map<String, String> postData = new HashMap<>();
                postData.put("userid", userID);
                postData.put("password", userPW);

                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postData));
                writer.flush();
                writer.close();
                os.close();

                // 응답 받기
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;

                    while ((line = in.readLine()) != null) {
                        response.append(line);
                    }

                    in.close();
                    return response.toString();
                } else {
                    return "Error: " + responseCode;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return "Error: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            // 서버에서의 응답(result)에 따라 로그인 성공 또는 실패 처리
            if (result.equals("Login successful")) {
                // 로그인 성공 처리
                Toast.makeText( getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, homeActivity.class);
                startActivity(intent);
                finish();
            } else {
                // 로그인 실패 처리
                Toast.makeText( getApplicationContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getPostDataString(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first) {
                first = false;
            } else {
                result.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    // 카카오톡으로 가입하기
    public void kakaotalkRegister(){

    }

    // 페이스북으로 가입하기
    public void facebookRegister(){

    }

    // 구글로 가입하기
    public void googleRegister(){

    }

}

