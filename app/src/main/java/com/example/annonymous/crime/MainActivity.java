package com.example.annonymous.crime;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import java.util.UUID;

public  class MainActivity extends SingalFragmentActivity{
//    @Override
//    protected void onCreate( Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fragment);
//
//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
//
//        if (fragment == null){
//            fragment = new CrimeFragment();
//            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
//        }
//    }


        @Override
    protected Fragment createFragment(){
//        return new CrimeFragment();
            UUID crimeId =(UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);
            return CrimeFragment.newInstance(crimeId);
    }


    private static final String EXTRA_CRIME_ID = "com.example.annonymous.crime.crime_id";

    public static Intent newIntent(Context packageContext, UUID crimeId){
        Intent intent = new Intent(packageContext, MainActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

}
