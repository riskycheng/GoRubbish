package com.chengjian.utils;

import android.content.Context;
import com.fatfish.chengjian.gorubbish.R;

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
            String priceStr = "¥ " + (new Random().nextFloat() + new Random().nextInt(200));
            goodsEntity.setPrice(priceStr);
            goodsEntity.setDescription("家用厨房客厅卫生间无盖垃圾筒纸篓");
            goodsEntity.setCode(localCodes[new Random().nextInt(localCodes.length)]);
            goodsEntities.add(goodsEntity);
        }
        return goodsEntities;
    }
}
