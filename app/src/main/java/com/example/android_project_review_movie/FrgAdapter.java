package com.example.android_project_review_movie;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FrgAdapter extends FragmentStateAdapter {
    public FrgAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if( position == 1){
            return new PlayListFrg();
        }
        return new Reviewed();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
