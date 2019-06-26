package com.fatfish.chengjian.gorubbish;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LearnActivity extends AppCompatActivity {
    private ImageView imgBtn_recycle, imgBtn_dry, imgBtn_wet, imgBtn_harm;
    private TextView textRubbishName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_learn);
        init();
    }

    public void init() {
        imgBtn_dry = findViewById(R.id.learn_category_of_dry);
        imgBtn_dry.setOnClickListener(new MyClickListener());
        imgBtn_dry.setOnTouchListener(new MyClickListener());
        imgBtn_recycle = findViewById(R.id.learn_category_of_recycle);
        imgBtn_recycle.setOnTouchListener(new MyClickListener());
        imgBtn_recycle.setOnClickListener(new MyClickListener());
        imgBtn_wet = findViewById(R.id.learn_category_of_wet);
        imgBtn_wet.setOnClickListener(new MyClickListener());
        imgBtn_wet.setOnTouchListener(new MyClickListener());
        imgBtn_harm = findViewById(R.id.learn_category_of_harm);
        imgBtn_harm.setOnClickListener(new MyClickListener());
        imgBtn_harm.setOnTouchListener(new MyClickListener());
        textRubbishName = findViewById(R.id.learn_rubbish_name_tobe_categorize);
        textRubbishName.setOnClickListener(new MyClickListener());
    }


    public class MyClickListener implements View.OnClickListener, View.OnTouchListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.learn_category_of_dry:
                    Toast.makeText(LearnActivity.this, "dry", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.learn_category_of_harm:
                    Toast.makeText(LearnActivity.this, "harm", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.learn_category_of_recycle:
                    Toast.makeText(LearnActivity.this, "recycle", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.learn_category_of_wet:
                    Toast.makeText(LearnActivity.this, "wet", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.learn_rubbish_name_tobe_categorize:
                    Toast.makeText(LearnActivity.this, "rubbish", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.animate().scaleX(0.8f).scaleY(0.8f).setDuration(80L).start();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(80L).start();
            }


            return false;
        }
    }
}
