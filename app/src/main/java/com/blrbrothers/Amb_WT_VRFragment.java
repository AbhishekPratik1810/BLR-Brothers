package com.blrbrothers;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class Amb_WT_VRFragment extends Fragment {

    ImageView backButton,banner;
    TextView header,title,body;
    Button call;
    int count;
    BottomNavigationViewEx bottomNavigationViewEx;
    public Amb_WT_VRFragment(int count) {
        this.count = count;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_amb__wt__vr, container, false);
        initView(view);
        bottomNavigationViewEx = Objects.requireNonNull(getActivity()).findViewById(R.id.bottom_avigation_bar);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) banner.getLayoutParams();
        float d = getContext().getResources().getDisplayMetrics().density;
        if(count==1){
            params.topMargin= (int) (138*d);
            header.setText("Water Tank");
            title.setText("BLR Brothers Water Tank");
            String bodyText = "వేసవి కాలం మన మిర్యాలగూడ పట్టన ప్రజలు  నీరు లేక ఇబ్బంది పడకూడదు అని మన BLR బ్రదర్స్ ఉచిత వాటర్ టాంకర్ సౌకర్యం కలిపిస్తునారు. మీ ఏరియా లో నీటి  ఇబ్బంది ఉన్నట్లు అయితే వెంటనే  క్రింద వున్నా కాల్ బటన్ ను ప్రెస్ చేసి కాల్ చేయండి మరియు మా వాటర్ టాంకర్ డ్రైవర్ కు మీ అడ్రస్ వివరాలు తెలపండి.";
            body.setText(bodyText);
            banner.setImageResource(R.drawable.ic_wt_banner);
            banner.setLayoutParams(params);
        }else if (count ==2){
            params.topMargin= (int)(140*d);
            header.setText("Vaikunta Ratham");
            title.setText("BLR Brothers Vaikunta Rath");
            String bodyText = "పుట్టిన వానికి మరణం తప్పదు, మరణించినవానికి మరల పుట్టుక తప్పదు. \n" +
                    "మరణించిన వారి అంతిమ యాత్ర కోసం BLR  బ్రదర్స్ ఉచిత వైకుంఠ రథం సౌకర్యం కలిపిస్తునారు. మీకు వైకుంఠ రథం అవసరం ఉన్నట్లు ఐతే క్రింద వున్నా కాల్ బటన్ ను ప్రెస్ చేసి కాల్ చేయండి మరియు మా వైకుంఠ రథం  డ్రైవర్ కు మీ అడ్రస్ వివరాలు తెలపండి.";
            body.setText(bodyText);
            banner.setImageResource(R.drawable.ic_vr_banner);
            banner.setLayoutParams(params);

        }

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomNavigationViewEx.setCurrentItem(0); // Home item should be selected
                Objects.requireNonNull(getActivity()).getSupportFragmentManager().popBackStack();
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("+91 99999 88888");
            }
        });
        return view;
    }

    private void initView(View view){
        backButton=view.findViewById(R.id.iv_AWTVR_Back_Button);
        banner = view.findViewById(R.id.iv_AWTVR_Image);
        header = view.findViewById(R.id.tv_AWTVR_Header);
        title = view.findViewById(R.id.tv_AWTVR_Title);
        body = view.findViewById(R.id.tv_AWTVR_Body);
        call = view.findViewById(R.id.btn_AWTVR_call);
    }

    public void call(String number) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));
        startActivity(intent);
    }


}
