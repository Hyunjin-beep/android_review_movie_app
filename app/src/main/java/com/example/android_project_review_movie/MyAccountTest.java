package com.example.android_project_review_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyAccountTest extends AppCompatActivity {

    ViewPager2 viewPager2;
    TabLayout tabLayout;
    FrgAdapter frgAdapter;

    TextView tv_userEmail_account;
    Button btn_logout_test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account_test);

        Toolbar myToolbar =  findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        viewPager2 = findViewById(R.id.viewpager_main);
        tabLayout = findViewById(R.id.tab_layout_main);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        assert firebaseUser != null;

        tv_userEmail_account = findViewById(R.id.tv_name_test);
        tv_userEmail_account.setText(firebaseUser.getEmail() + "!");

        btn_logout_test = findViewById(R.id.btn_logout_test);

        btn_logout_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MyAccountTest.this, MainActivity.class);
                startActivity(intent);
                Toast.makeText(MyAccountTest.this, "Successfully logged out", Toast.LENGTH_SHORT).show();
            }
        });

        FragmentManager fm = getSupportFragmentManager();
        frgAdapter = new FrgAdapter(fm, getLifecycle());
        viewPager2.setAdapter(frgAdapter);

        tabLayout.addTab(tabLayout.newTab().setText("Reviewed"));
        tabLayout.addTab(tabLayout.newTab().setText("Playlist"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }
}