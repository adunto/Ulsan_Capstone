package com.example.survit;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.survit.slideFragment.SlideFragment_1;
import com.example.survit.slideFragment.SlideFragment_2;
import com.example.survit.slideFragment.SlideFragment_3;
import com.example.survit.slideFragment.SlideFragment_4;

public class MainSlideWindowAdapter extends FragmentStateAdapter {
    public int mCount;
    public MainSlideWindowAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new SlideFragment_1();
        else if(index==1) return new SlideFragment_2();
        else if(index==2) return new SlideFragment_3();
        else return new SlideFragment_4();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }
}
