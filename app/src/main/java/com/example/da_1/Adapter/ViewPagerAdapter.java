package com.example.da_1.Adapter;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.da_1.Fragment.OnboardingFragment;
import com.example.da_1.Fragment.OnboardingFragment1;
import com.example.da_1.Fragment.OnboardingFragment2;







public class ViewPagerAdapter extends FragmentStatePagerAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new OnboardingFragment();
            case 1:
                return new OnboardingFragment1();
            case 2:
                return new OnboardingFragment2();
            default:
                return new OnboardingFragment();
        }
    }


    @Override
    public int getCount() {
        return 3;
    }
}
