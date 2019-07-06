package com.fatfish.chengjian.gorubbish;

import android.util.Log;
import com.chengjian.utils.GoodsEntity;
import com.chengjian.utils.MyLocalUtils;
import org.junit.Test;

import java.util.ArrayList;

public class TestFunc {
    @Test
    public void testParseJson(){
        String jsonData = "[{\n" +
                "  \"id\": 101,\n" +
                "  \"img_link\": \"dustbin_01.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 30.99\n" +
                "}, {\n" +
                "  \"id\": 102,\n" +
                "  \"img_link\": \"dustbin_02.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 31.99\n" +
                "}, {\n" +
                "  \"id\": 103,\n" +
                "  \"img_link\": \"dustbin_03.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 32.99\n" +
                "}, {\n" +
                "  \"id\": 104,\n" +
                "  \"img_link\": \"dustbin_04.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 33.99\n" +
                "}, {\n" +
                "  \"id\": 105,\n" +
                "  \"img_link\": \"dustbin_05.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 34.99\n" +
                "}, {\n" +
                "  \"id\": 106,\n" +
                "  \"img_link\": \"dustbin_06.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 35.99\n" +
                "}, {\n" +
                "  \"id\": 107,\n" +
                "  \"img_link\": \"dustbin_07.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 36.99\n" +
                "}, {\n" +
                "  \"id\": 108,\n" +
                "  \"img_link\": \"dustbin_08.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 37.99\n" +
                "}, {\n" +
                "  \"id\": 109,\n" +
                "  \"img_link\": \"dustbin_09.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 38.99\n" +
                "}, {\n" +
                "  \"id\": 110,\n" +
                "  \"img_link\": \"dustbin_10.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 39.99\n" +
                "}, {\n" +
                "  \"id\": 111,\n" +
                "  \"img_link\": \"dustbin_11.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 40.99\n" +
                "}, {\n" +
                "  \"id\": 112,\n" +
                "  \"img_link\": \"dustbin_12.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 41.99\n" +
                "}, {\n" +
                "  \"id\": 113,\n" +
                "  \"img_link\": \"dustbin_13.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 42.99\n" +
                "}, {\n" +
                "  \"id\": 114,\n" +
                "  \"img_link\": \"dustbin_14.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 43.99\n" +
                "}, {\n" +
                "  \"id\": 115,\n" +
                "  \"img_link\": \"dustbin_15.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 44.99\n" +
                "}, {\n" +
                "  \"id\": 116,\n" +
                "  \"img_link\": \"dustbin_16.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 45.99\n" +
                "}, {\n" +
                "  \"id\": 117,\n" +
                "  \"img_link\": \"dustbin_17.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 46.99\n" +
                "}, {\n" +
                "  \"id\": 118,\n" +
                "  \"img_link\": \"dustbin_18.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 47.99\n" +
                "}, {\n" +
                "  \"id\": 119,\n" +
                "  \"img_link\": \"dustbin_19.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 48.99\n" +
                "}, {\n" +
                "  \"id\": 120,\n" +
                "  \"img_link\": \"dustbin_20.png\",\n" +
                "  \"description\": \"describe\",\n" +
                "  \"code\": \"复製这条(muWiYgOkCwz),\\n进入【Tao宝】即可抢购\",\n" +
                "  \"price\": 49.99\n" +
                "}]";
        ArrayList<GoodsEntity> goodsEntities = MyLocalUtils.buildGoodsFromJson(jsonData);
        for (GoodsEntity goodsEntity : goodsEntities){
            System.out.println(goodsEntity.toString());
        }



    }
}
