package com.example.survit.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.survit.R;
import com.example.survit.login.loginFragment.LoginFragment;

/*
    작업 일시 : 2024-01-04
    코드 내용 : 로그인 액티비티
    수정 내용 : 없음 < 수정시 여기에 명시할 것 >
 */

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

         getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_fragment_container, new LoginFragment())
                        .commit();
    }


}
