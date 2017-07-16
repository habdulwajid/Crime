package com.example.annonymous.crime;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.UUID;

/**
 * Created by Annonymous on 7/13/2017.
 */
public class CrimeListFragment  extends Fragment{

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View viewe = inflater.inflate(R.layout.fragment_crime_list, container, false);

        mCrimeRecyclerView = (RecyclerView) viewe.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return viewe;
    }


    @Override
    public void onResume() {
        super.onResume();
        updateUI();
    }

    private void updateUI() {
        CrimeLab crimeLab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimeLab.getCrimes();
        if (mAdapter == null) {
            mAdapter = new CrimeAdapter(crimes);
            mCrimeRecyclerView.setAdapter(mAdapter);
        }else {
            mAdapter.notifyDataSetChanged();
        }

    }

//    working with view holder

    private  class CrimeHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

//        public TextView mTitleTextView;
        private  TextView mTitleTextView;
        private  TextView mDateView;
        private CheckBox mSolvedCheckBox;
        private Crime mCrime;



        public CrimeHolder(View itemView) {
            super(itemView);


//            mTitleTextView = (TextView)itemView;
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_crime_title_text_view);
            mDateView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox)
                    itemView.findViewById(R.id.list_item_crime_solved_check_box);

            itemView.setOnClickListener(this);
        }

        public void bindCrime(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v) {
//            Toast.makeText(getActivity(),mCrime.getTitle()+ " clicked! ", Toast.LENGTH_SHORT).show();

//            Intent intent = new Intent(getActivity(),MainActivity.class);
//            Intent intent = MainActivity.newIntent(getActivity(), mCrime.getId());
            Intent intent =CrimePagerActivity.newIntent(getActivity(),mCrime.getId());
            startActivity(intent);
        }
    }

    private  class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{

        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes) {
            mCrimes = crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
//            View view = layoutInflater.inflate(android.R.layout.simple_list_item_1,parent,false);         cheanging after
            View view = layoutInflater.inflate(R.layout.list_item_crime, parent, false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {

            Crime crime = mCrimes.get(position);
//            holder.mTitleTextView.setText(crime.getTitle());
        holder.bindCrime(crime);
        }


        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
