package com.example.gymgenius;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

public class Sub_DietPlans extends AppCompatActivity {

    TabLayout tabLayoutObj;
    ViewPager2 viewPager2Obj;
    ViewPagerAdapter viewPagerAdapterObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub_diet_plans);

        tabLayoutObj = findViewById(R.id.tabLayoutId);
        viewPager2Obj = findViewById(R.id.viewPager2Id);
        viewPagerAdapterObj = new ViewPagerAdapter(this);
        viewPager2Obj.setAdapter(viewPagerAdapterObj);
        tabLayoutObj.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2Obj.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2Obj.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayoutObj.getTabAt(position).select();
            }
        });

    }

    public void profileFunction(View view) {
        startActivities(new Intent[]{new Intent(this, UserProfileActivity.class)});
    }
}