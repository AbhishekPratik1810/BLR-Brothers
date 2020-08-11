package com.blrbrothers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationViewEx bottomNavigationViewEx;
    int ctr =0;
    boolean hackyFix=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationViewEx =  findViewById(R.id.bottom_navigation_view).findViewById(R.id.bottom_avigation_bar);
        setUpBottomNavigationView(bottomNavigationViewEx);
        bottomNavigationViewEx.setCurrentItem(0); //setting the selected item as home
        //toolbar.setVisibility(View.VISIBLE);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new HomeFragment()) // opening the login fragment
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .addToBackStack("Home").commit();


        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w("BLRFCMCheck", "getInstanceId failed", task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();

                        // Log and toast
                        String msg = "This is Token "+token;
                        Log.d("BLRFCMCheck", msg);
                        //Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void setUpBottomNavigationView(final BottomNavigationViewEx bottomNavigationViewEx) {
        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableShiftingMode(false);
        bottomNavigationViewEx.enableItemShiftingMode(true);

        bottomNavigationViewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.action_home:
                        //toolbar.setVisibility(View.VISIBLE);
                        Log.i("HAHA","selected Home");
                            getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ft.replace(R.id.fragment_placeholder, new HomeFragment());

                        break;

                    case R.id.action_contact:
                        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        ft.replace(R.id.fragment_placeholder, new ContactFragment()).addToBackStack("contact");
                        /*if (bottomNavigationViewEx.getCurrentItem() != 1) {

                            *//*getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                            ft.replace(R.id.fragment_placeholder, ContactFragment.newInstance()).addToBackStack("contact");*//*
                        }*/
                        break;


                    case R.id.action_notifications:
                        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                        ft.replace(R.id.fragment_placeholder, new NotificationsFragment()).addToBackStack("contact");
                        /*if (bottomNavigationViewEx.getCurrentItem() != 2) {


//                            ft.replace(R.id.fragment_placeholder, new AdminFragment()).addToBackStack("contact");

                            *//*if (firebaseAuth.getCurrentUser() != null) {
                                if (admin) {
                                    ft.replace(R.id.fragment_placeholder, new AdminFragment()).addToBackStack("contact");
                                } else {
                                    ft.replace(R.id.fragment_placeholder, NotificationsFragment.newInstance()).addToBackStack("contact");
                                }
                            } else {
                                ft.replace(R.id.fragment_placeholder, NotificationsFragment.newInstance()).addToBackStack("contact");
                            }*//*
                        }*/
                        break;


                    case R.id.action_share:
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey check out BLR Brother's App");
                        sendIntent.setType("text/plain");
                        startActivityForResult(sendIntent,1805);
                        break;
                }
                ft.commit();
                return true;
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ++ctr;
        if (requestCode == 1805) {
            if(resultCode == 0){
                if(ctr>1){
                    hackyFix=true;
                }

                onBackPressed();
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
            }
        }
    }//o

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();
        if(hackyFix&&count==0) {count =1;hackyFix=false;}
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_placeholder);

        if (!(fragment instanceof IOnBackPressed) || !((IOnBackPressed) fragment).onBackPressed()) {
            if (count == 0) {
                //toolbar.setVisibility(View.VISIBLE);
                super.onBackPressed();
               // showExitDialog();
            } else if (count == 1) {
                //toolbar.setVisibility(View.VISIBLE);
                getSupportFragmentManager().popBackStack();
                bottomNavigationViewEx.setCurrentItem(0); //setting the selected item as Home
            } else {
                getSupportFragmentManager().popBackStack();
            }
        } else {
        }
    }
}
