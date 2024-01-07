package com.example.survit.home;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.survit.R;
import com.example.survit.home.homeFragment.category_fragment;
import com.example.survit.home.homeFragment.home_fragment;
import com.example.survit.home.homeFragment.search_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class homeActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int itemId = item.getItemId();
                        if(itemId == R.id.home_menu_category){
                            // 카테고리 프레그먼트
                            replaceFragment(new category_fragment());

                            return true;
                        }
                        else if (itemId == R.id.home_menu_search){
                            // 설문 검색 프레그먼트
                            replaceFragment(new search_fragment());

                            return true;
                        }
                        else if (itemId == R.id.home_menu_home) {
                            // 홈 프레그먼트
                            // 홈버튼이 눌리면 스택에 있는 프레그먼트 모두 종료하고 홈 프레그먼트만 실행
                            replaceFragment(new home_fragment());
                            terminateAllFragment();

                            return true;
                        }
                        else if (itemId == R.id.home_menu_board) {
                            // 게시판 프레그먼트


                            return true;
                        }
                        else if (itemId == R.id.home_menu_setting) {
                            // 설정 프레그먼트


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
