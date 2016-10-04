package com.wohlig.sava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by adiam on 7/19/2016.
 */
public class MobileVerificationActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    View view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_mobile_verification);
    }

}
