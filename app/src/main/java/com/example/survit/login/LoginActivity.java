package com.example.survit.login;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.survit.R;
import com.example.survit.login.loginFragment.Fragment1;
import com.example.survit.login.loginFragment.Fragment2;

/*
    작업 일시 : 2024-01-04
    코드 내용 : 로그인 액티비티
    수정 내용 : 없음 < 수정시 여기에 명시할 것 >
 */

public class LoginActivity extends AppCompatActivity {

    Button loginBtn, registerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        getSupportFragmentManager().beginTransaction()
                        .replace(R.id.login_fragment_container, new Fragment1())
                        .commit();


    }

}
