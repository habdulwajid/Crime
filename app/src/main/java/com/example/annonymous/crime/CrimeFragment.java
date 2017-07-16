package com.example.annonymous.crime;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Annonymous on 7/10/2017.
 */
public class CrimeFragment extends Fragment {
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;
    private LayoutInflater mInflater;
    private ViewGroup mContainer;
    private Bundle mSavedInstanceState;
    private static final String ARG_CRIME_ID="crime_id";
    private static final String DIALOG_DATE ="DateDialog";

    private static final int REQUEST_DATE =0;


    public static CrimeFragment newInstance(UUID crimeId){
        Bundle args = new Bundle();
        args.putSerializable(ARG_CRIME_ID, crimeId);

        CrimeFragment fragment = new CrimeFragment();
        fragment.setArguments(args);
        return  fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
//        mCrime = new Crime();

//        UUID crimeId = (UUID)getActivity().getIntent().getSerializableExtra(MainActivity.EXTRA_CRIME_ID);

        UUID crimeId = (UUID)getArguments().getSerializable(ARG_CRIME_ID);
        mCrime = CrimeLab.get(getActivity()).getCrimes(crimeId);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.fragment_crime, container, false);

//        wiring up text editor

        mTitleField = (EditText)v.findViewById(R.id.crime_title);
        mTitleField.setText(mCrime.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCrime.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton = (Button)v.findViewById(R.id.crime_date);
       updateDate();
//        mDateButton.setText(mCrime.getDate().toString());
//        mDateButton.setEnabled(true);

        mDateButton.setOnClickListener(new  View.OnClickListener(){
          @Override
            public void onClick(View v){
              FragmentManager manager = getFragmentManager();
//              DatePickerFragment dialog = new DatePickerFragment();

              DatePickerFragment dialog = DatePickerFragment
                      .newInstance(mCrime.getDate());
              dialog.setTargetFragment(CrimeFragment.this,REQUEST_DATE);
              dialog.show(manager, DIALOG_DATE);
          }
        });
        mSolvedCheckBox = (CheckBox)v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setChecked(mCrime.isSolved());
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mCrime.setSolved(isChecked);
            }


        });
        return  v;

    }

    private void updateDate() {
        mDateButton.setText(mCrime.getDate().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if (resultCode != Activity.RESULT_OK){
            return;
        }
        if (requestCode == REQUEST_DATE){
            Date date = (Date)data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCrime.setDate(date);
           mDateButton.setText(mCrime.getDate().toString());
            updateDate();
        }
    }
}


