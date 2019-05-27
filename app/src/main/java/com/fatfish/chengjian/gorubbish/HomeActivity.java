package com.fatfish.chengjian.gorubbish;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import static com.chengjian.utils.GlobalConstants.REQUEST_URL;

public class HomeActivity extends Activity {
    private final String TAG = HomeActivity.class.getCanonicalName();
    private EditText editText_Search_kw;
    private Button btn_search;

    //the rubbish categories list
    private LinearLayout mLinearLayoutCategories = null;
    private ImageView mCategory_recycle = null, mCategory_dry = null, mCategory_wet = null, mCategory_harm = null;
    private LinearLayout mLinearLayoutResultPart = null;

    //the specific fields for the result
    private TextView mTextView_result_category;
    private TextView mTextView_result_description;
    private TextView mTextView_result_contain_title;
    private TextView mTextView_result_contain_content;
    private TextView mTextView_result_categorize_title;
    private TextView mTextView_result_categorize_content;
    private ImageView mImageview_result_category;


    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            updateUI((RubbishType) msg.obj);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    public void init() {
        editText_Search_kw = findViewById(R.id.editText_keywords);
        btn_search = findViewById(R.id.btn_search);

        btn_search.setOnClickListener(new MyClickListener());

        mLinearLayoutCategories = findViewById(R.id.category_list);
        mLinearLayoutCategories.setVisibility(View.VISIBLE);
        mLinearLayoutResultPart = findViewById(R.id.result_part);
        mLinearLayoutResultPart.setVisibility(View.GONE);

        mCategory_dry = findViewById(R.id.category_of_dry);
        mCategory_dry.setOnClickListener(new MyClickListener());
        mCategory_harm = findViewById(R.id.category_of_harm);
        mCategory_harm.setOnClickListener(new MyClickListener());
        mCategory_recycle = findViewById(R.id.category_of_recycle);
        mCategory_recycle.setOnClickListener(new MyClickListener());
        mCategory_wet = findViewById(R.id.category_of_wet);
        mCategory_wet.setOnClickListener(new MyClickListener());
        
        
        //init for the specific result controller
         mTextView_result_category = findViewById(R.id.result_rubbish_category);
         mTextView_result_description = findViewById(R.id.result_rubbish_description);
         mTextView_result_contain_title = findViewById(R.id.result_rubbish_contain_title);
         mTextView_result_contain_content = findViewById(R.id.result_rubbish_contain_content);
         mTextView_result_categorize_title = findViewById(R.id.result_rubbish_categorize_title);
         mTextView_result_categorize_content = findViewById(R.id.result_rubbish_categorize_content);
         mImageview_result_category = findViewById(R.id.result_rubbish_image);
    }


    public void requestForResult(String keyWords) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        Request request = new Request.Builder()
                .url(REQUEST_URL + keyWords)
                .header("User-Agent", "OkHttp Example")
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    RubbishType rubbishType = parseCategoryWithHttpResult(body.string());
                    Log.d(TAG, "rubbish type is " + rubbishType);
                    //update the UI according to the result type
                    Message msg = Message.obtain(handler);
                    msg.obj = rubbishType;
                    msg.sendToTarget();
                    body.close();
                }
            }
        });
    }

    enum RubbishType {
        RUBBISH_DRY,
        RUBBISH_WET,
        RUBBISH_HARM,
        RUBBISH_RECYCLE,
        UNKNOWN
    }


    public void updateUI(RubbishType rubbishType) {

        mLinearLayoutResultPart.setVisibility(View.VISIBLE);
        mLinearLayoutCategories.setVisibility(View.GONE);
        //hide the method
        InputMethodManager methodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        methodManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        switch (rubbishType) {
            case RUBBISH_DRY:
                mImageview_result_category.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_dry));
                mTextView_result_category.setText(getString(R.string.rubbish_dry_name));
                mTextView_result_category.setTextColor(ContextCompat.getColor(this, R.color.rubbish_dry_color));
                mTextView_result_description.setText(getString(R.string.rubbish_dry_descriptions));
                mTextView_result_description.setTextColor(ContextCompat.getColor(this, R.color.rubbish_dry_color));
                mTextView_result_contain_title.setBackgroundColor(ContextCompat.getColor(this, R.color.rubbish_dry_color));
                mTextView_result_contain_title.setText(getString(R.string.rubbish_dry_contains_title));
                mTextView_result_contain_content.setTextColor(ContextCompat.getColor(this, R.color.rubbish_dry_color));
                mTextView_result_contain_content.setText(getString(R.string.rubbish_dry_contains_content));
                mTextView_result_categorize_title.setBackgroundColor(ContextCompat.getColor(this, R.color.rubbish_dry_color));
                mTextView_result_categorize_title.setText(getString(R.string.rubbish_dry_categorize_title));
                mTextView_result_categorize_content.setTextColor(ContextCompat.getColor(this, R.color.rubbish_dry_color));
                mTextView_result_categorize_content.setText(getString(R.string.rubbish_dry_categorize_content));
                break;
            case RUBBISH_WET:
                mImageview_result_category.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_wet));
                mTextView_result_category.setText(getString(R.string.rubbish_wet_name));
                mTextView_result_category.setTextColor(ContextCompat.getColor(this, R.color.rubbish_wet_color));
                mTextView_result_description.setText(getString(R.string.rubbish_wet_descriptions));
                mTextView_result_description.setTextColor(ContextCompat.getColor(this, R.color.rubbish_wet_color));
                mTextView_result_contain_title.setBackgroundColor(ContextCompat.getColor(this, R.color.rubbish_wet_color));
                mTextView_result_contain_title.setText(getString(R.string.rubbish_wet_contains_title));
                mTextView_result_contain_content.setTextColor(ContextCompat.getColor(this, R.color.rubbish_wet_color));
                mTextView_result_contain_content.setText(getString(R.string.rubbish_wet_contains_content));
                mTextView_result_categorize_title.setBackgroundColor(ContextCompat.getColor(this, R.color.rubbish_wet_color));
                mTextView_result_categorize_title.setText(getString(R.string.rubbish_wet_categorize_title));
                mTextView_result_categorize_content.setTextColor(ContextCompat.getColor(this, R.color.rubbish_wet_color));
                mTextView_result_categorize_content.setText(getString(R.string.rubbish_wet_categorize_content));
                break;
            case RUBBISH_HARM:
                mImageview_result_category.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_harm));
                mTextView_result_category.setText(getString(R.string.rubbish_harm_name));
                mTextView_result_category.setTextColor(ContextCompat.getColor(this, R.color.rubbish_harm_color));
                mTextView_result_description.setText(getString(R.string.rubbish_harm_descriptions));
                mTextView_result_description.setTextColor(ContextCompat.getColor(this, R.color.rubbish_harm_color));
                mTextView_result_contain_title.setBackgroundColor(ContextCompat.getColor(this, R.color.rubbish_harm_color));
                mTextView_result_contain_title.setText(getString(R.string.rubbish_harm_contains_title));
                mTextView_result_contain_content.setTextColor(ContextCompat.getColor(this, R.color.rubbish_harm_color));
                mTextView_result_contain_content.setText(getString(R.string.rubbish_harm_contains_content));
                mTextView_result_categorize_title.setBackgroundColor(ContextCompat.getColor(this, R.color.rubbish_harm_color));
                mTextView_result_categorize_title.setText(getString(R.string.rubbish_harm_categorize_title));
                mTextView_result_categorize_content.setTextColor(ContextCompat.getColor(this, R.color.rubbish_harm_color));
                mTextView_result_categorize_content.setText(getString(R.string.rubbish_harm_categorize_content));
                break;
            case RUBBISH_RECYCLE:
                mImageview_result_category.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.icon_recycle));
                mTextView_result_category.setText(getString(R.string.rubbish_recycle_name));
                mTextView_result_category.setTextColor(ContextCompat.getColor(this, R.color.rubbish_recycle_color));
                mTextView_result_description.setText(getString(R.string.rubbish_recycle_descriptions));
                mTextView_result_description.setTextColor(ContextCompat.getColor(this, R.color.rubbish_recycle_color));
                mTextView_result_contain_title.setBackgroundColor(ContextCompat.getColor(this, R.color.rubbish_recycle_color));
                mTextView_result_contain_title.setText(getString(R.string.rubbish_recycle_contains_title));
                mTextView_result_contain_content.setTextColor(ContextCompat.getColor(this, R.color.rubbish_recycle_color));
                mTextView_result_contain_content.setText(getString(R.string.rubbish_recycle_contains_content));
                mTextView_result_categorize_title.setBackgroundColor(ContextCompat.getColor(this, R.color.rubbish_recycle_color));
                mTextView_result_categorize_title.setText(getString(R.string.rubbish_recycle_categorize_title));
                mTextView_result_categorize_content.setTextColor(ContextCompat.getColor(this, R.color.rubbish_recycle_color));
                mTextView_result_categorize_content.setText(getString(R.string.rubbish_recycle_categorize_content));
                break;
            case UNKNOWN:
                mTextView_result_category.setText(getString(R.string.rubbish_unknown_name));
                mLinearLayoutResultPart.setVisibility(View.GONE);
                break;

        }
    }


    public RubbishType parseCategoryWithHttpResult(String httpResult) {
        RubbishType resultType = RubbishType.UNKNOWN;

        Document docParser = Jsoup.parse(httpResult);
        List<Element> elements = docParser.select("#form1 > div.main > div.con > div.info");

        if (elements.size() != 1) {
            return resultType;
        }

        //get the first child : the p element
        Element element = ((Elements) elements).first();
        //get the span element : the first span element
        List<Element> spanElements = element.getElementsByTag("span");

        if (spanElements.size() != 1) {
            return resultType;
        }

        //get the target span info
        Element infoElement = ((Elements) spanElements).first();

        String keyResultWords = infoElement.text();

        Log.d(TAG, "the result key words is ==> " + keyResultWords);

        if (keyResultWords.contains(getString(R.string.rubbish_dry_name))) {
            resultType = RubbishType.RUBBISH_DRY;
        } else if (keyResultWords.contains(getString(R.string.rubbish_harm_name))) {
            resultType = RubbishType.RUBBISH_HARM;
        } else if (keyResultWords.contains(getString(R.string.rubbish_recycle_name))) {
            resultType = RubbishType.RUBBISH_RECYCLE;
        } else if (keyResultWords.contains(getString(R.string.rubbish_wet_name))) {
            resultType = RubbishType.RUBBISH_WET;
        }
        return resultType;
    }


    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_search:
                    String keyWords = editText_Search_kw.getText().toString().trim();
                    requestForResult(keyWords);
                    break;
                case R.id.category_of_dry:
                    Log.d(TAG, "trigger descriptions of dry rubbish");
                    break;
                case R.id.category_of_harm:
                    Log.d(TAG, "trigger descriptions of harm rubbish");
                    break;
                case R.id.category_of_recycle:
                    Log.d(TAG, "trigger descriptions of recycle rubbish");
                    break;
                case R.id.category_of_wet:
                    Log.d(TAG, "trigger descriptions of wet rubbish");
                    break;
            }
        }
    }

}
