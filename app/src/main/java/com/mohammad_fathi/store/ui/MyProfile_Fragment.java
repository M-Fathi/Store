package com.mohammad_fathi.store.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mohammad_fathi.store.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyProfile_Fragment extends Fragment {
    @BindView(R.id.mainLayout_profile)
    LinearLayout mainLayout_profile;

    private LinearLayout linearLayout;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_my_profile,container,false);
        ButterKnife.bind(this, view);

        if (checkLogIn()) {

            linearLayout = (LinearLayout) View.inflate(getActivity(),
                    R.layout.register_page, null);
            mainLayout_profile.addView(linearLayout);
        }else
        {
            linearLayout = (LinearLayout) View.inflate(getActivity(),
                    R.layout.my_profile, null);
            mainLayout_profile.addView(linearLayout);
        }


        return view;




    }


    private boolean checkLogIn(){
        Boolean register;
        register=true;

        return register;
    }
}