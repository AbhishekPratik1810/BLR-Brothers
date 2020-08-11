package com.blrbrothers;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.net.URLEncoder;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    ImageView backButton,call,mail,location,website;
    TextView tv_Call,tv_Mail,tv_Location,tv_website;
    String PHONE,MAIL,LOCATION,WEBSITE;
    private BottomNavigationViewEx bottomNavigationViewEx;
    public ContactFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        init(view);
        bottomNavigationViewEx = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_avigation_bar);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationViewEx.setCurrentItem(0); // Home item should be selected
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMaps();
            }
        });

        tv_Location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchMaps();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(PHONE);
            }
        });

        tv_Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call(PHONE);
            }
        });

        mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEmailIntent();
            }
        });
        tv_Mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startEmailIntent();
            }
        });

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebView(WEBSITE);
            }
        });
        tv_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebView(WEBSITE);
            }
        });
        return view;
    }

    private void init(View view){
        backButton = view.findViewById(R.id.iv_Contact_Back_Button);
        call = view.findViewById(R.id.iv_Contact_Phone);
        mail = view.findViewById(R.id.iv_Contact_Mail);
        location = view.findViewById(R.id.iv_Contact_Location);
        website = view.findViewById(R.id.iv_Contact_Website);
        tv_Call = view.findViewById(R.id.tv_Contact_Phone);
        tv_Mail = view.findViewById(R.id.tv_Contact_Mail);
        tv_Location = view.findViewById(R.id.tv_Contact_Location);
        tv_website = view.findViewById(R.id.tv_Contact_Website);
        PHONE = tv_Call.getText().toString();
        MAIL = tv_Mail.getText().toString();
        LOCATION = tv_Location.getText().toString();
        WEBSITE = tv_website.getText().toString();

    }
    private void startEmailIntent() {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                "mailto", MAIL, null));
        startActivity(intent);
    }

    public void call(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        startActivity(intent);
    }

    private void launchMaps(){

        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&destination=" + URLEncoder.encode(LOCATION, java.nio.charset.StandardCharsets.UTF_8.toString()));
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        }catch (Exception e){
            Log.i("BLR",e.toString());
        }

    }

    public void openWebView(String webViewLink){
        //implicit intent call
        Intent implicit = new Intent(Intent.ACTION_VIEW, Uri.parse(webViewLink));
        startActivity(implicit);
    }

}
