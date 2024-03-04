package com.example.survit.home.homeFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.survit.RecentPagerAdapter;
import com.example.survit.Top5SurveyFragment.Hot5_Survey_Fragment1;
import com.example.survit.Top5SurveyFragment.Hot5_Survey_Fragment2;
import com.example.survit.Top5SurveyFragment.Hot5_Survey_Fragment3;
import com.example.survit.Top5SurveyFragment.Hot5_Survey_Fragment4;
import com.example.survit.Top5SurveyFragment.Hot5_Survey_Fragment5;
import com.example.survit.R;

import java.util.ArrayList;

public class home_fragment extends Fragment {

    private ViewPager2 hot5ViewPager2, adverViewPager2;
    private static final int HOT_NUM_PAGER = 5;
    private FragmentStateAdapter hot5PagerAdapter, adverPagerAdapter;

    Button goLeftBtn_hot5, goRightBtn_hot5;

    public home_fragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_layout, container, false);

        // 광고 뷰페이저






        // -------------------------------------------------------------------------------------- //

        hot5ViewPager2 = (ViewPager2) view.findViewById(R.id.viewPager_hot);

        hot5PagerAdapter = new hot5PagerAdapter(getActivity());

        hot5ViewPager2.setAdapter(hot5PagerAdapter);

        // 버튼
        goLeftBtn_hot5 = (Button)view.findViewById(R.id.go_left_btn);
        goRightBtn_hot5 = (Button) view.findViewById(R.id.go_right_btn);

        goLeftBtn_hot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = hot5ViewPager2.getCurrentItem();
                hot5ViewPager2.setCurrentItem(currentItem - 1, true);
            }
        });

        goRightBtn_hot5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentItem = hot5ViewPager2.getCurrentItem();
                hot5ViewPager2.setCurrentItem(currentItem + 1, true);
            }
        });

        hot5ViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    goLeftBtn_hot5.setVisibility(View.GONE);
                }
                else if (position == 4) {
                    goRightBtn_hot5.setVisibility(View.GONE);
                }
                else {
                    goLeftBtn_hot5.setVisibility(View.VISIBLE);
                    goRightBtn_hot5.setVisibility(View.VISIBLE);
                }
            }
        });

        // -------------------------------------------------------------------------------------- //


        // 데이터베이스를 검사하여 3일이내에 작성된 설문이 있으면 게재



        // -------------------------------------------------------------------------------------- //

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3, RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        RecentPagerAdapter adapter = new RecentPagerAdapter(getContext());
        recyclerView.setAdapter(adapter);

        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);


        return view;
    }

    private class hot5PagerAdapter extends FragmentStateAdapter {
        // 아이템 배열
        ArrayList<Fragment> items_hot5 = new ArrayList<>();
        // 타이틀 배열
        ArrayList<String> title_hot5 = new ArrayList<>();
        // 질문 수 배열
        ArrayList<String> questionCnt_hot5 = new ArrayList<>();
        // 참여 수 배열
        ArrayList<String> participateCnt_hot5 = new ArrayList<>();



        public hot5PagerAdapter(FragmentActivity fa){
            super(fa);
            Hot5_Survey_Fragment1 fragment1 = new Hot5_Survey_Fragment1();
            Hot5_Survey_Fragment2 fragment2 = new Hot5_Survey_Fragment2();
            Hot5_Survey_Fragment3 fragment3 = new Hot5_Survey_Fragment3();
            Hot5_Survey_Fragment4 fragment4 = new Hot5_Survey_Fragment4();
            Hot5_Survey_Fragment5 fragment5 = new Hot5_Survey_Fragment5();


            items_hot5.add(fragment1);
            items_hot5.add(fragment2);
            items_hot5.add(fragment3);
            items_hot5.add(fragment4);
            items_hot5.add(fragment5);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {


            return items_hot5.get(position);
        }

        @Override
        public int getItemCount() {
            return HOT_NUM_PAGER;
        }

        public void createTop5Item() {

        }

    }

    // 광고 패널 어뎁터
    private class advertisePagerAdapter extends FragmentStateAdapter {

        ArrayList<Fragment> items_advertisement = new ArrayList<>();

        public advertisePagerAdapter(FragmentActivity fa) {
            super(fa);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {

            return null;
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }



}
