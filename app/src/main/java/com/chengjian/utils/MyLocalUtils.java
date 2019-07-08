package com.chengjian.utils;

import android.content.Context;
import android.util.Log;
import com.fatfish.chengjian.gorubbish.R;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class MyLocalUtils {

    public static ArrayList<GoodsEntity> mockUpGoodEntities(Context context, int count) {
        ArrayList<GoodsEntity> goodsEntities = new ArrayList<>();
        int drawableIntArr[] = {R.drawable.dustbin_01, R.drawable.dustbin_02, R.drawable.dustbin_03, R.drawable.dustbin_01,
                R.drawable.dustbin_05, R.drawable.dustbin_06, R.drawable.dustbin_07, R.drawable.dustbin_08};

        String localCodes[] = {
                "复製这条(muWiYgOkCwz),\n进入【Tao宝】即可抢购","复製这条(5th6YgOPEXh),\n进入【Tao宝】即可抢购",
                "复製这条(fq7SYgOPkyT),\n进入【Tao宝】即可抢购","复製这条(0OptYgOl6xK),\n进入【Tao宝】即可抢购",
                "复製这条(7N53YgOPKhF),\n进入【Tao宝】即可抢购","复製这条(6mDHYgOlcHb),\n进入【Tao宝】即可抢购",
                "复製这条(0boCYgOPLcu),\n进入【Tao宝】即可抢购","复製这条(TSRBYgOls7n),\n进入【Tao宝】即可抢购"};
        for (int i = 0; i < count; i++) {
            GoodsEntity goodsEntity = new GoodsEntity();
            goodsEntity.setImage(context.getDrawable(drawableIntArr[new Random().nextInt(drawableIntArr.length)]));
            double priceStr = (new Random().nextFloat() + new Random().nextInt(200));
            goodsEntity.setPrice(priceStr);
            goodsEntity.setDescription("家用厨房客厅卫生间无盖垃圾筒纸篓");
            goodsEntity.setCode(localCodes[new Random().nextInt(localCodes.length)]);
            goodsEntities.add(goodsEntity);
        }
        return goodsEntities;
    }


    //parse the json file to build the arrayList for the GoodEntity
    public static ArrayList<GoodsEntity> buildGoodsFromJson(String jsonData){
        ArrayList<GoodsEntity> goodsEntities = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject != null){
                    GoodsEntity goodsEntity = new GoodsEntity();
                    goodsEntity.setCode(jsonObject.getString("code"));
                    goodsEntity.setDescription(jsonObject.getString("description"));
                    goodsEntity.setImgLink(jsonObject.getString("img_link"));
                    goodsEntity.setPrice(jsonObject.getDouble("price"));
                    goodsEntities.add(goodsEntity);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return goodsEntities;
    }




    public static String downloadJson(final String urlStr)
    {
        InputStream inStream = null;
        URL url = null;
        try
        {
            url = new URL(urlStr);
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        HttpURLConnection conn = null;
        try {
            conn = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        conn.setConnectTimeout(5 * 1000);
        try {
            conn.setRequestMethod("GET");
            conn.connect();
        } catch (Exception e) {
        }

        try {
            inStream= conn.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder sb = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream,"utf-8"));
            sb= new StringBuilder();
            String line = null;
            try {
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return sb.toString();
    }






}
