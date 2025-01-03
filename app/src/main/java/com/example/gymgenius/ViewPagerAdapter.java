package com.example.gymgenius;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new Sat();
            case 1: return new Sun();
            case 2: return new Mon();
            case 3: return new Tue();
            case 4: return new Wed();
            case 5: return new Thu();
            case 6: return new Fri();
            default: return new Sat();
        }
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
