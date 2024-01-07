package com.example.survit.login.loginFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.survit.*;
import com.example.survit.home.homeActivity;

/*
    작업 일시 : 2024-01-05
    코드 내용 : 로그인 초기화면 프레그먼트
    수정 내용 : 없음 < 수정시 여기에 명시할 것 >
 */


public class LoginFragment extends Fragment {
   public LoginFragment(){

   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.login_fragment, container, false);

       // 로그인 버튼 클릭시 로그인 프래그먼트 전환
       Button loginBtn = view.findViewById(R.id.login_button);
       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getActivity(), homeActivity.class);
               startActivity(intent);
               getActivity().finish();
           }
       });

        ImageView kakaotalk, facebook, google;

        // 카카오톡 버튼 클릭시 액션
        kakaotalk = view.findViewById(R.id.kakaotalkIcon);
        kakaotalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "카카오톡 클릭됨", Toast.LENGTH_LONG).show();
            }
        });

        facebook = view.findViewById(R.id.facebookIcon);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "페이스북 클릭됨", Toast.LENGTH_LONG).show();
            }
        });

        google = view.findViewById(R.id.googleIcon);
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "구글 클릭됨", Toast.LENGTH_LONG).show();
            }
        });

        return view;
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

