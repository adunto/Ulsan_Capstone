package com.example.survit.login.loginFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.survit.R;

/*
    작업 일시 : 2024-01-06
    코드 내용 : 로그인 화면 프래그먼트
    수정 내용 : 없음 < 수정시 여기에 명시할 것 >
 */


public class Fragment2 extends Fragment{
    public Fragment2(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment2, container, false);
    }
}
