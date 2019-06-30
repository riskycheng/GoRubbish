package com.fatfish.chengjian.gorubbish;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.chengjian.utils.GoodsAdapter;
import com.chengjian.utils.GoodsEntity;
import com.chengjian.utils.MyLocalRecycleViewItemDecoration;
import com.chengjian.utils.MyLocalUtils;

import java.util.ArrayList;

public class GoodsRecommendActivity extends Activity {
    private final static String TAG = GoodsRecommendActivity.class.getCanonicalName();
    private RecyclerView mRecycleViewGoods;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_goods_recommend);
        this.mContext = this;
        initUI();
    }


    public void initUI() {
        mRecycleViewGoods = findViewById(R.id.recyclerview_dustbins);
        ArrayList<GoodsEntity> goodsEntities = MyLocalUtils.mockUpGoodEntities(this, 8);
        GoodsAdapter goodsAdapter = new GoodsAdapter(mContext, goodsEntities);
        goodsAdapter.setOnItemClickListener(new GoodsAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, GoodsEntity goodsEntity) {
                Log.d(TAG, goodsEntity.getCode());
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText(null, goodsEntity.getCode()));
                //start Tao-bao
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.taobao.taobao");
                startActivity(intent);
            }
        });
        mRecycleViewGoods.setAdapter(goodsAdapter);
        mRecycleViewGoods.setLayoutManager(new GridLayoutManager(mContext, 2));
        MyLocalRecycleViewItemDecoration itemDecoration = new MyLocalRecycleViewItemDecoration(20);
        mRecycleViewGoods.addItemDecoration(itemDecoration);

    }
}
