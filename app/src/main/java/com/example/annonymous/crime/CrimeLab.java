package com.example.annonymous.crime;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Annonymous on 7/13/2017.
 */
public class CrimeLab {
    private static  CrimeLab sCrimelab;
    public List<Crime>mCrimes;

    public static  CrimeLab get(Context context){

        if (sCrimelab == null){
            sCrimelab = new CrimeLab(context);
        }
        return sCrimelab;
    }

    private CrimeLab(Context context){
        mCrimes = new ArrayList<>();
        for (int i=0; i<100; i++){
            Crime crime = new Crime();
            crime.setTitle("Crime #" + i);
            crime.setSolved(i % 2 == 0);
            mCrimes.add(crime);
        }
    }

    public List<Crime> getCrimes(){
        return  mCrimes;
    }

    public Crime getCrimes(UUID id){
        for (Crime crime: mCrimes){
            if (crime.getId().equals(id)){
                return crime;
            }
        }
        return null;
    }
}
