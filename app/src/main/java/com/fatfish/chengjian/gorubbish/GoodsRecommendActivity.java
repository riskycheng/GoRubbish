package com.fatfish.chengjian.gorubbish;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.chengjian.utils.*;

import java.util.ArrayList;

public class GoodsRecommendActivity extends Activity {
    private final static String TAG = GoodsRecommendActivity.class.getCanonicalName();
    private RecyclerView mRecycleViewGoods;
    private Context mContext;
    SweetAlertDialog pDialog = null;
    private ImageButton mBtnBack = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        setContentView(R.layout.activity_goods_recommend);
        this.mContext = this;
        mRecycleViewGoods = findViewById(R.id.recyclerview_dustbins);
        mBtnBack = findViewById(R.id.btn_good_share_back);
        mBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initHttpRequests();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (pDialog.isShowing())
                pDialog.cancel();
            /* get the message from internet requesting */
            ArrayList<GoodsEntity> entities = (ArrayList<GoodsEntity>) msg.obj;
            Log.d(TAG, "get the internet results!");
            //update the UI part
            updateItems(entities);
        }
    };


    public void initHttpRequests() {

        pDialog = new SweetAlertDialog(GoodsRecommendActivity.this, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(getString(R.string.loading));
        pDialog.setCancelable(false);
        pDialog.show();


        new Thread(new Runnable() {
            @Override
            public void run() {
                ArrayList<GoodsEntity> entities = MyLocalUtils.buildGoodsFromJson(MyLocalUtils.downloadJson(GlobalConstants.MY_SERVER_GOODS_JSON_ADDR));
                Message msg = new Message();
                msg.obj = entities;
                handler.sendMessage(msg);
            }
        }).start();


    }


    public void mockupItems() {
        ArrayList<GoodsEntity> goodsEntities = MyLocalUtils.mockUpGoodEntities(mContext, 8);
        GoodsAdapter goodsAdapter = new GoodsAdapter(mContext, goodsEntities);
        goodsAdapter.setOnItemClickListener(new GoodsAdapter.onItemClickListener() {
            @Override
            public void onClick(View view, GoodsEntity goodsEntity) {
                Log.d(TAG, goodsEntity.getCode());
            }
        });
        mRecycleViewGoods.setAdapter(goodsAdapter);
        mRecycleViewGoods.setLayoutManager(new GridLayoutManager(mContext, 2));
        MyLocalRecycleViewItemDecoration itemDecoration = new MyLocalRecycleViewItemDecoration(20);
        mRecycleViewGoods.addItemDecoration(itemDecoration);
    }

    public void updateItems(ArrayList<GoodsEntity> goodsEntities) {
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
