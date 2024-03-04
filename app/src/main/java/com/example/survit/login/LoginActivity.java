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

import com.example.survit.LoadingDialog;
import com.example.survit.R;
import com.example.survit.home.homeActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/*
    작업 일시 : 2024-01-04
    코드 내용 : 로그인 액티비티

    수정 내용 : 프래그먼트 삭제 & 액티비티 화면으로 대체

 */



public class LoginActivity extends AppCompatActivity {

    private Button startEmailBtn;

    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);



        EditText usernameEditText = findViewById(R.id.email_editText);
        EditText passwordEditText = findViewById(R.id.password_editText);

        startEmailBtn = (Button)findViewById(R.id.start_email);

        startEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 입력한 정보 가져오기
                String user_id = usernameEditText.getText().toString();
                String user_pw = passwordEditText.getText().toString();

                if (user_id.length()==0 || user_pw.length()==0 ) {
                    Toast.makeText(LoginActivity.this, "로그인 정보가 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    // 다이얼로그 객체 생성
                    loadingDialog = new LoadingDialog(LoginActivity.this);

                    // 다이얼로그 표시
                    loadingDialog.show();
                    // AsyncTask를 사용하여 백그라운드에서 서버와 통신
                    new LoginTask().execute(user_id, user_pw);
                }

            }
        });


        Button kakaotalk, google;

        // 카카오톡 버튼 클릭시 액션
        kakaotalk = (Button) findViewById(R.id.start_kakao);
        kakaotalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "카카오톡 클릭됨", Toast.LENGTH_LONG).show();
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

    // HttpURLConnection 사용
    private class LoginTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String result = "";
            try {
                // 서버 URL 설정
                URL url = new URL("http://124.54.47.115:8080/loginTask.php");

                // HttpURLConnection 객체 생성
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                // POST 방식 설정
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                // 파라미터 설정
                String data = "username=" + URLEncoder.encode(params[0], "UTF-8")
                        + "&password=" + URLEncoder.encode(params[1], "UTF-8");

                // 출력 스트림 설정
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(data.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                // 응답 받기
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();

                // 연결 종료
                httpURLConnection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                result = "error";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // 서버로부터 받은 결과를 처리하는 부분
            if (result.equals("success")) {

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }


                // 로그인 성공
                Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                loadingDialog.dismiss();
                Intent intent = new Intent(LoginActivity.this, homeActivity.class);
                startActivity(intent);
                finish();
            } else if (result.equals("error")) {
                // 통신 오류
                Toast.makeText(LoginActivity.this, "통신 오류", Toast.LENGTH_SHORT).show();
            } else if (result.equals("failure")){
                // 로그인 실패
                Toast.makeText(LoginActivity.this, "로그인 실패", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(LoginActivity.this, result, Toast.LENGTH_LONG).show();
            }
        }
    }


    // 카카오톡으로 가입하기
    public void kakaotalkRegister(){

    }

    // 구글로 가입하기
    public void googleRegister(){

    }

}

