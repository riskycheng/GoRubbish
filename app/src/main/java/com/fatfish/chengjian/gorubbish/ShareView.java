package com.fatfish.chengjian.gorubbish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.*;

public class ShareView extends FrameLayout {
    private String mName = null;

    public static final int IMAGE_WIDTH = 1440;
    public static final int IMAGE_HEIGHT = 3000;

    private HomeActivity.RubbishType mType = null;

    //the specific fields for the result
    private TextView mTextView_result_category;
    private TextView mTextView_result_description;
    private TextView mTextView_result_contain_title;
    private TextView mTextView_result_contain_content;
    private TextView mTextView_result_categorize_title;
    private TextView mTextView_result_categorize_content;
    private ImageView mImageview_result_category;

    private LinearLayout mLinearLayoutResultPart = null;
    private LinearLayout mLinearLayoutDemoPart = null;

    private Context mContext = null;

    //fill the editText for review
    private EditText mEditTextName = null;

    public ShareView(Context context, String name, HomeActivity.RubbishType type) {
        super(context);
        mContext = context;
        setInfo(name, type);
        init();
    }


    private void setInfo(String name, HomeActivity.RubbishType type) {
        mName = name;
        mType = type;
    }

    private void init() {
        View layout = View.inflate(getContext(), R.layout.activity_share, this);
        //init the view according to the passed values
        //init for the specific result controller
        mTextView_result_category = layout.findViewById(R.id.result_rubbish_category);
        mTextView_result_description = layout.findViewById(R.id.result_rubbish_description);
        mTextView_result_contain_title = layout.findViewById(R.id.result_rubbish_contain_title);
        mTextView_result_contain_content = layout.findViewById(R.id.result_rubbish_contain_content);
        mTextView_result_categorize_title = layout.findViewById(R.id.result_rubbish_categorize_title);
        mTextView_result_categorize_content = layout.findViewById(R.id.result_rubbish_categorize_content);
        mImageview_result_category = layout.findViewById(R.id.result_rubbish_image);

        mLinearLayoutResultPart = layout.findViewById(R.id.result_part);
        mLinearLayoutDemoPart = layout.findViewById(R.id.demo_part);

        mEditTextName = layout.findViewById(R.id.editText_keywords_share);
        updateUI(mName, mType);

    }


    public void updateUI(String name, HomeActivity.RubbishType rubbishType) {

        mLinearLayoutResultPart.setVisibility(View.VISIBLE);

        //update the editText to fill in the name
        mEditTextName.setHint(name);

        switch (rubbishType) {
            case RUBBISH_DRY:
                mImageview_result_category.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_dry));
                mTextView_result_category.setText(mContext.getString(R.string.rubbish_dry_name));
                mTextView_result_category.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_dry_color));
                mTextView_result_description.setText(mContext.getString(R.string.rubbish_dry_descriptions));
                mTextView_result_description.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_dry_color));
                mTextView_result_contain_title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rubbish_dry_color));
                mTextView_result_contain_title.setText(mContext.getString(R.string.rubbish_dry_contains_title));
                mTextView_result_contain_content.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_dry_color));
                mTextView_result_contain_content.setText(mContext.getString(R.string.rubbish_dry_contains_content));
                mTextView_result_categorize_title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rubbish_dry_color));
                mTextView_result_categorize_title.setText(mContext.getString(R.string.rubbish_dry_categorize_title));
                mTextView_result_categorize_content.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_dry_color));
                mTextView_result_categorize_content.setText(mContext.getString(R.string.rubbish_dry_categorize_content));
                break;
            case RUBBISH_WET:
                mImageview_result_category.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_wet));
                mTextView_result_category.setText(mContext.getString(R.string.rubbish_wet_name));
                mTextView_result_category.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_wet_color));
                mTextView_result_description.setText(mContext.getString(R.string.rubbish_wet_descriptions));
                mTextView_result_description.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_wet_color));
                mTextView_result_contain_title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rubbish_wet_color));
                mTextView_result_contain_title.setText(mContext.getString(R.string.rubbish_wet_contains_title));
                mTextView_result_contain_content.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_wet_color));
                mTextView_result_contain_content.setText(mContext.getString(R.string.rubbish_wet_contains_content));
                mTextView_result_categorize_title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rubbish_wet_color));
                mTextView_result_categorize_title.setText(mContext.getString(R.string.rubbish_wet_categorize_title));
                mTextView_result_categorize_content.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_wet_color));
                mTextView_result_categorize_content.setText(mContext.getString(R.string.rubbish_wet_categorize_content));
                break;
            case RUBBISH_HARM:
                mImageview_result_category.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_harm));
                mTextView_result_category.setText(mContext.getString(R.string.rubbish_harm_name));
                mTextView_result_category.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_harm_color));
                mTextView_result_description.setText(mContext.getString(R.string.rubbish_harm_descriptions));
                mTextView_result_description.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_harm_color));
                mTextView_result_contain_title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rubbish_harm_color));
                mTextView_result_contain_title.setText(mContext.getString(R.string.rubbish_harm_contains_title));
                mTextView_result_contain_content.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_harm_color));
                mTextView_result_contain_content.setText(mContext.getString(R.string.rubbish_harm_contains_content));
                mTextView_result_categorize_title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rubbish_harm_color));
                mTextView_result_categorize_title.setText(mContext.getString(R.string.rubbish_harm_categorize_title));
                mTextView_result_categorize_content.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_harm_color));
                mTextView_result_categorize_content.setText(mContext.getString(R.string.rubbish_harm_categorize_content));
                break;
            case RUBBISH_RECYCLE:
                mImageview_result_category.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.icon_recycle));
                mTextView_result_category.setText(mContext.getString(R.string.rubbish_recycle_name));
                mTextView_result_category.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_recycle_color));
                mTextView_result_description.setText(mContext.getString(R.string.rubbish_recycle_descriptions));
                mTextView_result_description.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_recycle_color));
                mTextView_result_contain_title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rubbish_recycle_color));
                mTextView_result_contain_title.setText(mContext.getString(R.string.rubbish_recycle_contains_title));
                mTextView_result_contain_content.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_recycle_color));
                mTextView_result_contain_content.setText(mContext.getString(R.string.rubbish_recycle_contains_content));
                mTextView_result_categorize_title.setBackgroundColor(ContextCompat.getColor(mContext, R.color.rubbish_recycle_color));
                mTextView_result_categorize_title.setText(mContext.getString(R.string.rubbish_recycle_categorize_title));
                mTextView_result_categorize_content.setTextColor(ContextCompat.getColor(mContext, R.color.rubbish_recycle_color));
                mTextView_result_categorize_content.setText(mContext.getString(R.string.rubbish_recycle_categorize_content));
                break;
        }
    }


    public Bitmap createImage() {

        int widthMeasureSpec = MeasureSpec.makeMeasureSpec(IMAGE_WIDTH, MeasureSpec.EXACTLY);
        int heightMeasureSpec = MeasureSpec.makeMeasureSpec(IMAGE_HEIGHT, MeasureSpec.EXACTLY);

        measure(widthMeasureSpec, heightMeasureSpec);
        layout(0, 0, IMAGE_WIDTH, IMAGE_HEIGHT);
        Bitmap bitmap = Bitmap.createBitmap(IMAGE_WIDTH, IMAGE_HEIGHT, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        draw(canvas);

        return bitmap;
    }
}
