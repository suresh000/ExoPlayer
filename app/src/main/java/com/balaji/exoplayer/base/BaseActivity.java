package com.balaji.exoplayer.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.balaji.exoplayer.R;

public abstract class BaseActivity extends AppCompatActivity {

    public abstract Fragment getCurrentFragment();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void addFragment(@NonNull final Fragment addFragment) {
        new Thread(() -> {
            String currentFragmentName = "";
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null) {
                currentFragmentName = currentFragment.getClass().getName();
            }
            if (addFragment.getClass().getName().equals(currentFragmentName)) {
                return;
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.add(R.id.fragmentContainer, addFragment);
            transaction.commit();
        }).start();
    }

    protected void replaceFragment(@NonNull final Fragment addFragment) {
        new Thread(() -> {
            String currentFragmentName = "";
            Fragment currentFragment = getCurrentFragment();
            if (currentFragment != null) {
                currentFragmentName = currentFragment.getClass().getName();
            }
            if (addFragment.getClass().getName().equals(currentFragmentName)) {
                return;
            }

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();

            transaction.replace(R.id.fragmentContainer, addFragment);
            transaction.commit();
        }).start();
    }


}
