package com.example.survit.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.survit.R;
import com.example.survit.addSurvey.SurveyAddActivity;
import com.example.survit.home.homeFragment.category_fragment;
import com.example.survit.home.homeFragment.home_fragment;
import com.example.survit.home.homeFragment.setting_fragment;
import com.example.survit.home.homeFragment.userinfo_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import java.util.List;

public class homeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    int currentItemId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if(itemId == R.id.home_menu_favorite){
                            replaceFragment(new category_fragment());
                            currentItemId = R.id.home_menu_favorite;

                            return true;
                        }
                        else if (itemId == R.id.home_menu_home) {
                            // 홈버튼이 눌리면 스택에 있는 프레그먼트 모두 종료하고 홈 프레그먼트만 실행
                            replaceFragment(new home_fragment());
                            terminateAllFragment();
                            currentItemId = R.id.home_menu_home;

                            return true;
                        }
                        else if (itemId == R.id.home_menu_add) {
                            Intent intent = new Intent(homeActivity.this, SurveyAddActivity.class);
                            startActivity(intent);

                            bottomNavigationView.setSelectedItemId(currentItemId);

                            return false;
                        }
                        else if (itemId == R.id.home_menu_userinfo) {
                            // 내정보 프레그먼트
                            replaceFragment(new userinfo_fragment());

                            return true;
                        }
                        else if (itemId == R.id.home_menu_setting) {
                            // 설정 프레그먼트
                            replaceFragment(new setting_fragment());

                            return true;
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Error:BottomNavigationView", Toast.LENGTH_LONG).show();
                            return false;
                        }

                    }
                }
        );

        // 초기 화면 설정
        bottomNavigationView.setSelectedItemId(R.id.home_menu_home);
    }

    // 타이틀바 액션
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu); // 메뉴 인플레이트
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 타이틀바 메뉴 아이템 클릭 이벤트 처리
        if (item.getItemId()==R.id.toolbar_menu_search){
            Intent intent = new Intent(homeActivity.this, searchActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            return true;
        }
        else if (item.getItemId()==R.id.toolbar_menu_notification){
            Toast.makeText(this, "정보 버튼 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }

    // 홈 화면 프레그먼트 전환 메서드
    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.home_fragment_container, fragment);
        transaction.commit();
    }

    private void terminateAllFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        List<Fragment> fragmentList = fragmentManager.getFragments();

        if(fragmentList != null){
            for(Fragment fragment : fragmentList){
                if(fragment != null && !(fragment instanceof home_fragment)){
                    fragmentManager.beginTransaction().remove(fragment).commit();
                }
            }
        }
    }
}
