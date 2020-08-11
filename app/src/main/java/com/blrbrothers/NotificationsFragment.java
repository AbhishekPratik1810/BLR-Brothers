package com.blrbrothers;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsFragment extends Fragment {

    ImageView backButton;
    private BottomNavigationViewEx bottomNavigationViewEx;
    public NotificationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);
        init(view);
        bottomNavigationViewEx = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_avigation_bar);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationViewEx.setCurrentItem(0); // Home item should be selected
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        return view;
    }

    private void init(View view){
        backButton = view.findViewById(R.id.iv_Notif_Back_Button);
    }

}
