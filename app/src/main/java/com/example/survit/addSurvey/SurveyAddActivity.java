package com.example.survit.addSurvey;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.survit.R;

import java.util.ArrayList;

import me.relex.circleindicator.CircleIndicator3;

public class SurveyAddActivity extends AppCompatActivity {
    private ViewPager2 SurveyAddViewPager2;

    private SurveyAddPagerAdapter adapter;

    Button previousBtn, nextBtn, backBtn2;

    public AddItem addItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey_add_layout);

        SurveyAddViewPager2 = (ViewPager2) findViewById(R.id.survey_add_viewPager2);
        SurveyAddViewPager2.setUserInputEnabled(false);  // 사용자가 화면을 슬라이드로 못넘기게 함

        adapter = new SurveyAddPagerAdapter(this);

        SurveyAddViewPager2.setAdapter(adapter);

        CircleIndicator3 indicator = (CircleIndicator3) findViewById(R.id.CircleIndicator);
        indicator.setViewPager(SurveyAddViewPager2);

        previousBtn = (Button) findViewById(R.id.previousBtn);
        nextBtn = (Button) findViewById(R.id.nextBtn);

        previousBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = SurveyAddViewPager2.getCurrentItem();
                SurveyAddViewPager2.setCurrentItem(currentItem - 1, true);
            }
        });

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = SurveyAddViewPager2.getCurrentItem();
                SurveyAddViewPager2.setCurrentItem(currentItem + 1, true);
            }
        });

        SurveyAddViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    previousBtn.setVisibility(View.GONE);
                }
                else if (position == 2) {
                    nextBtn.setVisibility(View.GONE);
                }
                else {
                    previousBtn.setVisibility(View.VISIBLE);
                    nextBtn.setVisibility(View.VISIBLE);
                }
            }
        });

        // 뒤로가기 버튼

        backBtn2 = (Button) findViewById(R.id.back_Button2);
        backBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 추가할 아이템
        addItem = new AddItem();
        addItem.setAns(null, null, null, null, null);



    }


    private class SurveyAddPagerAdapter extends FragmentStateAdapter {
        ArrayList<Fragment> items = new ArrayList<>();

        public SurveyAddPagerAdapter(FragmentActivity fa){
            super(fa);

        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            SurveyAddFragment1 frag1 = new SurveyAddFragment1();
            SurveyAddFragment2 frag2 = new SurveyAddFragment2();
            SurveyAddFragment3 frag3 = new SurveyAddFragment3();

            items.add(frag1);
            items.add(frag2);
            items.add(frag3);

            return items.get(position);
        }

        @Override
        public int getItemCount() {
            return 3;
        }


    }


}
