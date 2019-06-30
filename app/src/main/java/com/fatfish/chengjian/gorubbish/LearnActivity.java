package com.fatfish.chengjian.gorubbish;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.chengjian.utils.RubbishGenrator;
import com.chengjian.utils.SnailBar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LearnActivity extends Activity {
    private final static String TAG = LearnActivity.class.getCanonicalName();
    private ImageView imgBtn_recycle, imgBtn_dry, imgBtn_wet, imgBtn_harm;
    private TextView textRubbishName;
    private ImageView mImageViewResult;
    private ImageView mBack;

    private ArrayList<RubbishGenrator.RubbishEntity> mRubbishLibs = null;
    private Set<Integer> mDisplayedItems = null;
    private RubbishGenrator.RubbishEntity mCur_Rubbish = null;

    private SnailBar mSnailBar = null;

    private int mCurrentScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
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

        mImageViewResult = findViewById(R.id.img_result);

        mSnailBar = findViewById(R.id.finishedProgressBar);
        mSnailBar.setOnTouchListener(new MyClickListener());

        mBack = findViewById(R.id.learn_back);
        mBack.setOnClickListener(new MyClickListener());

        mRubbishLibs = RubbishGenrator.rubbishLib();

        mDisplayedItems = new HashSet<>();

        //init first time
        updateRubbishName();
    }


    // update the text to be categorize
    public void updateRubbishName() {
        //if it is already full, then clear it
        if (mDisplayedItems.size() >= mRubbishLibs.size()) {
            Log.d(TAG, "clear current libs");
            mDisplayedItems.clear();
        }

        mImageViewResult.setVisibility(View.INVISIBLE);
        textRubbishName.animate().scaleY(1.0f).scaleX(1.0f).setDuration(0L).start();
        textRubbishName.setTextColor(Color.BLACK);
        if (mCurrentScore >= 100) {
            textRubbishName.setText("恭喜完成");
            //disable the buttons
            imgBtn_harm.setEnabled(false);
            imgBtn_wet.setEnabled(false);
            imgBtn_recycle.setEnabled(false);
            imgBtn_dry.setEnabled(false);
            return;
        }

        int index = new Random().nextInt(1000) % mRubbishLibs.size();
        while (mDisplayedItems.contains(index)) {
            index = new Random().nextInt(1000) % mRubbishLibs.size();
        }
        mDisplayedItems.add(index);
        mCur_Rubbish = mRubbishLibs.get(index);
        //update the text
        textRubbishName.setText(mCur_Rubbish.getRubbishName());
    }

    private void startShakeByViewAnim(View view, float scaleSmall, float scaleLarge, float shakeDegrees, long duration) {
        if (view == null) {
            return;
        }
        //TODO 验证参数的有效性

        //由小变大
        Animation scaleAnim = new ScaleAnimation(scaleSmall, scaleLarge, scaleSmall, scaleLarge);
        //从左向右
        Animation rotateAnim = new RotateAnimation(-shakeDegrees, shakeDegrees, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnim.setDuration(duration);
        rotateAnim.setDuration(duration / 10);
        rotateAnim.setRepeatMode(Animation.REVERSE);
        rotateAnim.setRepeatCount(10);

        AnimationSet smallAnimationSet = new AnimationSet(false);
        smallAnimationSet.addAnimation(scaleAnim);
        smallAnimationSet.addAnimation(rotateAnim);

        view.startAnimation(smallAnimationSet);
    }

    //animation for this text
    public void updateUIAfterAnswering(boolean success) {
        mImageViewResult.setVisibility(View.VISIBLE);
        if (success) {
            mCurrentScore += 5;
            if (mCurrentScore > 100) {
                Log.e(TAG, "error when updating the snail...");
            } else {
                mSnailBar.setProgress(mCurrentScore);
            }
            mImageViewResult.setImageDrawable(getDrawable(R.drawable.correct));
            //correct animation
            textRubbishName.animate().scaleX(0.f).scaleY(0.f).setDuration(600L).start();
        } else {
            mCurrentScore -= 10;
            if (mCurrentScore < 0)
                mCurrentScore = 0;
            mSnailBar.setProgress(mCurrentScore);
            textRubbishName.setTextColor(Color.RED);
            mImageViewResult.setImageDrawable(getDrawable(R.drawable.wrong));
            //wrong animation
            startShakeByViewAnim(textRubbishName, 1.0f, 1.0f, 15.f, 600L);
        }
    }


    public class MyClickListener implements View.OnClickListener, View.OnTouchListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.learn_category_of_dry:
                    if (mCur_Rubbish.getRubbishType() == HomeActivity.RubbishType.RUBBISH_DRY) {
                        updateUIAfterAnswering(true);
                        new Handler().postDelayed(() -> updateRubbishName(), 1000L);
                    } else {
                        updateUIAfterAnswering(false);
                    }
                    break;
                case R.id.learn_category_of_harm:
                    if (mCur_Rubbish.getRubbishType() == HomeActivity.RubbishType.RUBBISH_HARM) {
                        updateUIAfterAnswering(true);
                        new Handler().postDelayed(() -> updateRubbishName(), 1000L);
                    } else {
                        updateUIAfterAnswering(false);
                    }
                    break;
                case R.id.learn_category_of_recycle:
                    if (mCur_Rubbish.getRubbishType() == HomeActivity.RubbishType.RUBBISH_RECYCLE) {
                        updateUIAfterAnswering(true);
                        new Handler().postDelayed(() -> updateRubbishName(), 1000L);
                    } else {
                        updateUIAfterAnswering(false);
                    }
                    break;
                case R.id.learn_category_of_wet:
                    if (mCur_Rubbish.getRubbishType() == HomeActivity.RubbishType.RUBBISH_WET) {
                        updateUIAfterAnswering(true);
                        new Handler().postDelayed(() -> updateRubbishName(), 1000L);
                    } else {
                        updateUIAfterAnswering(false);
                    }
                    break;
                case R.id.learn_rubbish_name_tobe_categorize:
                    break;

                case R.id.learn_back:
                    finish();
                    break;
            }
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.getId() == R.id.finishedProgressBar) {
                return true;
            }
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                v.animate().scaleX(0.8f).scaleY(0.8f).setDuration(80L).start();
            } else if (event.getAction() == MotionEvent.ACTION_UP) {
                v.animate().scaleX(1.0f).scaleY(1.0f).setDuration(80L).start();
            }


            return false;
        }
    }
}
