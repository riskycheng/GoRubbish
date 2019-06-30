package com.fatfish.chengjian.gorubbish;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.*;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.*;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.chengjian.utils.GlobalConstants;
import okhttp3.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.chengjian.utils.GlobalConstants.REQUEST_URL;

public class HomeActivity extends Activity {
    private final String TAG = HomeActivity.class.getCanonicalName();
    private EditText editText_Search_kw;
    private Button btn_search;

    //the rubbish categories list
    private LinearLayout mLinearLayoutCategories = null;
    private ImageView mCategory_recycle = null, mCategory_dry = null, mCategory_wet = null, mCategory_harm = null;
    private LinearLayout mLinearLayoutResultPart = null;
    private LinearLayout mLinearLayoutDemoPart = null;

    //the specific fields for the result
    private TextView mTextView_result_category;
    private TextView mTextView_result_description;
    private TextView mTextView_result_contain_title;
    private TextView mTextView_result_contain_content;
    private TextView mTextView_result_categorize_title;
    private TextView mTextView_result_categorize_content;
    private ImageView mImageview_result_category;

    private ScrollView mScrollViewFull;

    private LinearLayout mLinearButtonsBackShare = null;
    // the back and share button
    private Button mBtnBack = null;
    private Button mBtnShare = null;

    private Button mBtnPractice = null;
    private Button mBtnShareWithResult = null;

    private final int UPDATE_RESULT_MSG = 0;
    private final int SHOW_QUERYING_DIALOG = 1;
    private final int DISMISS_QUERY_DIALOG = 2;

    SweetAlertDialog pDialog = null;

    private String mRubbishName = null;
    private RubbishType mRubbishType = null;

    @SuppressLint("HandlerLeak")
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_RESULT_MSG:
                    if (pDialog.isShowing())
                        pDialog.cancel();
                    updateUI((RubbishType) msg.obj);
                    break;
                case DISMISS_QUERY_DIALOG:
                    if (pDialog.isShowing())
                        pDialog.cancel();
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        setContentView(R.layout.activity_main);
        GlobalConstants.verifyStoragePermissions(this);
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
        mLinearLayoutDemoPart = findViewById(R.id.demo_part);
        mLinearLayoutDemoPart.setVisibility(View.VISIBLE);

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

        mScrollViewFull = findViewById(R.id.scrollview_full);
        mLinearButtonsBackShare = findViewById(R.id.linear_buttons_back_share);

        //initialize the buttons
        mBtnBack = findViewById(R.id.btn_back_result);
        mBtnBack.setOnClickListener(new MyClickListener());
        mBtnShare = findViewById(R.id.btn_share_app);
        mBtnShare.setOnClickListener(new MyClickListener());

        mBtnPractice = findViewById(R.id.btn_practice);
        mBtnPractice.setOnClickListener(new MyClickListener());
        mBtnShareWithResult = findViewById(R.id.btn_share_result);
        mBtnShareWithResult.setOnClickListener(new MyClickListener());

    }


    public void requestForResult(String keyWords) {

        //update the keywords
        mRubbishName = keyWords;

        pDialog = new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(getString(R.string.querying_loading));
        pDialog.setCancelable(true);
        pDialog.show();

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(2 * 1000, TimeUnit.MILLISECONDS)
                .writeTimeout(2 * 1000, TimeUnit.MILLISECONDS)
                .build();
        Request request = new Request.Builder()
                .url(REQUEST_URL + keyWords)
                .header("User-Agent", "OkHttp Example")
                .build();
        new Handler().postDelayed(() -> okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: " + e.getMessage());
                Message msg_dismissDialog = Message.obtain(handler);
                msg_dismissDialog.what = DISMISS_QUERY_DIALOG;
                msg_dismissDialog.sendToTarget();
                //remind that the connect is not successful
                Looper.prepare();
                SweetAlertDialog failDialog = new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.ERROR_TYPE);
                failDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                failDialog.setTitleText(getString(R.string.network_error));
                failDialog.setCancelable(true);
                failDialog.setCancelText(getString(R.string.i_know));
                failDialog.show();
                Looper.loop();
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
                } else {
                    //at least, we need to release the dialog
                    Message msg_dismissDialog = Message.obtain(handler);
                    msg_dismissDialog.what = DISMISS_QUERY_DIALOG;
                    msg_dismissDialog.sendToTarget();
                }
            }
        }), 1000);

    }

    public enum RubbishType {
        RUBBISH_DRY,
        RUBBISH_WET,
        RUBBISH_HARM,
        RUBBISH_RECYCLE,
        UNKNOWN
    }


    public void updateUI(RubbishType rubbishType) {

        //update the rubbish type
        mRubbishType = rubbishType;

        mLinearLayoutResultPart.setVisibility(View.VISIBLE);
        mLinearLayoutCategories.setVisibility(View.GONE);

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
                mLinearLayoutDemoPart.setVisibility(View.VISIBLE);
                mLinearLayoutCategories.setVisibility(View.VISIBLE);
                SweetAlertDialog failDialog = new SweetAlertDialog(HomeActivity.this, SweetAlertDialog.ERROR_TYPE);
                failDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
                failDialog.setTitleText(getString(R.string.not_found));
                failDialog.setCancelable(true);
                failDialog.setCancelText(getString(R.string.i_know));
                failDialog.show();
                break;
        }
        mScrollViewFull.post(() -> mScrollViewFull.smoothScrollTo(0, mLinearButtonsBackShare.getBottom()));
    }


    public RubbishType parseCategoryWithHttpResult(String httpResult) {
        Log.d(TAG, httpResult);
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
                    resetOtherCategoryToNormalAnim();
                    //visualize the result
                    mLinearLayoutResultPart.setVisibility(View.VISIBLE);
                    mCategory_dry.animate()
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .setInterpolator(new AccelerateDecelerateInterpolator()).
                            setDuration(1L).start();
                    updateUI(RubbishType.RUBBISH_DRY);
                    mLinearLayoutCategories.setVisibility(View.VISIBLE);
                    mLinearLayoutDemoPart.setVisibility(View.GONE);
                    break;
                case R.id.category_of_harm:
                    Log.d(TAG, "trigger descriptions of harm rubbish");
                    resetOtherCategoryToNormalAnim();
                    //visualize the result
                    mLinearLayoutResultPart.setVisibility(View.VISIBLE);
                    mCategory_harm.animate()
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .setInterpolator(new AccelerateDecelerateInterpolator()).
                            setDuration(1L).start();

                    updateUI(RubbishType.RUBBISH_HARM);
                    mLinearLayoutCategories.setVisibility(View.VISIBLE);
                    mLinearLayoutDemoPart.setVisibility(View.GONE);
                    break;
                case R.id.category_of_recycle:
                    Log.d(TAG, "trigger descriptions of recycle rubbish");
                    resetOtherCategoryToNormalAnim();
                    //visualize the result
                    mLinearLayoutResultPart.setVisibility(View.VISIBLE);
                    mCategory_recycle.animate()
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .setInterpolator(new AccelerateDecelerateInterpolator()).
                            setDuration(1L).start();

                    updateUI(RubbishType.RUBBISH_RECYCLE);
                    mLinearLayoutCategories.setVisibility(View.VISIBLE);
                    mLinearLayoutDemoPart.setVisibility(View.GONE);
                    break;
                case R.id.category_of_wet:
                    Log.d(TAG, "trigger descriptions of wet rubbish");
                    resetOtherCategoryToNormalAnim();
                    //visualize the result
                    mLinearLayoutResultPart.setVisibility(View.VISIBLE);
                    mCategory_wet.animate()
                            .scaleX(1.2f)
                            .scaleY(1.2f)
                            .setInterpolator(new AccelerateDecelerateInterpolator()).
                            setDuration(1L).start();

                    updateUI(RubbishType.RUBBISH_WET);
                    mLinearLayoutCategories.setVisibility(View.VISIBLE);
                    mLinearLayoutDemoPart.setVisibility(View.GONE);
                    break;

                //the buttons
                case R.id.btn_practice:
                    Intent intent = new Intent();
                    intent.setClass(HomeActivity.this, LearnActivity.class);
                    startActivity(intent);
                    break;

                case R.id.btn_share_app:
                    break;


                case R.id.btn_back_result:
                    //hide the result page and show the linear array
                    mLinearLayoutCategories.setVisibility(View.VISIBLE);
                    mLinearLayoutDemoPart.setVisibility(View.VISIBLE);
                    mLinearLayoutResultPart.setVisibility(View.GONE);
                    resetOtherCategoryToNormalAnim();
                    break;
                case R.id.btn_share_result:
                    ShareView shareView = new ShareView(HomeActivity.this, mRubbishName, mRubbishType);
                    final Bitmap bitmapForShare = shareView.createImage();
                    String savedPath = saveImage(bitmapForShare, GlobalConstants.getSharedImagePath());
                    Log.d(TAG, "saved to ==> " + savedPath);
                    //share to timeline
                    shareToTimeLine(new File(savedPath));
                    if (bitmapForShare != null && !bitmapForShare.isRecycled()) {
                        bitmapForShare.recycle();
                    }
                    break;
            }
        }
    }


    private void resetOtherCategoryToNormalAnim() {
        mCategory_dry.animate()
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setInterpolator(new AccelerateDecelerateInterpolator()).start();
        mCategory_harm.animate()
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setInterpolator(new AccelerateDecelerateInterpolator()).start();
        mCategory_recycle.animate()
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setInterpolator(new AccelerateDecelerateInterpolator()).start();
        mCategory_wet.animate()
                .scaleX(1.0f)
                .scaleY(1.0f)
                .setInterpolator(new AccelerateDecelerateInterpolator()).start();
    }


    /**
     * used for saving images
     *
     * @param bitmap
     * @return
     */
    private String saveImage(Bitmap bitmap, String savePath) {

        File file = new File(savePath);

        if (file.exists()) {
            file.delete();
        }

        FileOutputStream fos = null;
        Bitmap resBitmap = null;
        try {
            fos = new FileOutputStream(file);
            // downsize first
            Matrix matrix = new Matrix();
            matrix.setScale(0.6f, 0.6f);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            resBitmap = Bitmap.createBitmap(bitmap, 0, 0, ShareView.IMAGE_WIDTH, ShareView.IMAGE_HEIGHT, matrix, true);
            resBitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (bitmap != null && !bitmap.isRecycled())
            bitmap.recycle();
        if (resBitmap != null && !resBitmap.isRecycled())
            resBitmap.recycle();

        return file.getAbsolutePath();
    }


    /**
     * 分享信息到朋友圈
     *
     * @param file ，假如图片的路径为path，那么file = new File(path);
     */
    private void shareToTimeLine(File file) {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareToTimeLineUI");
        intent.setComponent(componentName);

        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));

        // intent.setAction(android.content.Intent.ACTION_SEND_MULTIPLE);
        // ArrayList<Uri> uris = new ArrayList<Uri>();
        // for (int i = 0; i < images.size(); i++) {
        // Uri data = Uri.fromFile(new File(thumbPaths.get(i)));
        // uris.add(data);
        // }
        // intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);

        intent.setType("image/*");
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "wechat not installed!");
            Toast.makeText(this, "wechat not installed!", Toast.LENGTH_LONG).show();
        }
    }


    /**
     * 分享信息到朋友
     */
    private void shareToWxFriend() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.tencent.mm", "com.tencent.mm.ui.tools.ShareImgUI");
        intent.setComponent(componentName);
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, "这是分享内容");
        intent.putExtra(Intent.EXTRA_STREAM, "http://www.weixin.com");
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "wechat not installed!");
            Toast.makeText(this, "wechat not installed!", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 分享到QQ好友
     */
    private void shareToQQFriend() {
        Intent intent = new Intent();
        ComponentName componentName = new ComponentName("com.tencent.mobileqq", "com.tencent.mobileqq.activity.JumpActivity");
        intent.setComponent(componentName);
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/*");
        intent.putExtra(Intent.EXTRA_TEXT, "这是分享内容");
        try {
            startActivity(intent);
        } catch (Exception e) {
            Log.e(TAG, "QQ not installed!");
            Toast.makeText(this, "QQ not installed!", Toast.LENGTH_LONG).show();
        }
    }

}
