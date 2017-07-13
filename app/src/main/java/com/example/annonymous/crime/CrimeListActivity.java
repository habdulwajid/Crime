package com.example.annonymous.crime;

import android.support.v4.app.Fragment;

/**
 * Created by Annonymous on 7/13/2017.
 */
public class CrimeListActivity extends  SingalFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
