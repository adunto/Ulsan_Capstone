package com.example.survit.login.loginFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.survit.R;

/*
    작업 일시 : 2024-01-05
    코드 내용 : 로그인 초기화면 프레그먼트
    수정 내용 : 없음 < 수정시 여기에 명시할 것 >
 */


public class Fragment1 extends Fragment {
   public Fragment1(){

   }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.login_fragment1, container, false);

       // 로그인 버튼 클릭시 로그인 프래그먼트 전환
       Button loginBtn = view.findViewById(R.id.login_button);
       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               getParentFragmentManager().beginTransaction()
                       .replace(R.id.login_fragment_container, new Fragment2())
                       .addToBackStack(null)
                       .commit();
           }
       });

       // 회원가입 버튼 클릭시 회원가입 액티비티 전환
        Button registerBtn = view.findViewById(R.id.register_button);

        // 카카오톡으로 시작하기

        // 구글로 시작하기

        return view;
    }

}
