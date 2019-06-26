package com.chengjian.utils;


import com.fatfish.chengjian.gorubbish.HomeActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RubbishGenrator {

    public static class RubbishEntity{
        private String rubbishName;
        private HomeActivity.RubbishType rubbishType;

        public RubbishEntity(String rubbishName, HomeActivity.RubbishType rubbishType) {
            this.rubbishName = rubbishName;
            this.rubbishType = rubbishType;
        }
    }

    public static ArrayList<RubbishEntity> rubbishLib(){
        ArrayList<RubbishEntity> rubbishEntities = new ArrayList<>();

/*
餐巾纸、卫生间用纸、尿不湿、猫砂、狗尿垫、污损纸张、烟蒂、干燥剂
污损塑料、尼龙制品、编织袋、防碎气泡膜
大骨头、硬贝壳、硬果壳（椰子壳、榴莲壳、核桃壳、玉米衣、甘蔗皮）、硬果实（榴莲核、菠萝蜜核）
毛发、灰土、炉渣、橡皮泥、太空沙、带胶制品（胶水、胶带）、花盆、毛巾
一次性餐具、镜子、陶瓷制品、竹制品（竹篮、竹筷、牙签）
成分复杂的制品（伞、笔、眼镜、打火机）
 */
        //add the entity - DRY rubbish
        rubbishEntities.add(new RubbishEntity("餐巾纸", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("尿不湿", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("猫砂", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("编织袋", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("大骨头", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("硬贝壳", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("尼龙制品", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("硬果壳", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("硬果核", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("毛发", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("炉渣", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("橡皮泥", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("太空沙", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("花盆", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("毛巾", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("一次性餐具", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("陶瓷制品", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("竹制品", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("打火机", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("胶带", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("牙签", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("镜子", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("塑料袋", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("防碎气泡膜", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("烟蒂", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("干燥剂", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("榴莲壳", HomeActivity.RubbishType.RUBBISH_DRY));
        rubbishEntities.add(new RubbishEntity("榴莲核", HomeActivity.RubbishType.RUBBISH_DRY));




        return rubbishEntities;
    }


    public static ArrayList<RubbishEntity> getDryRubbishLibs(){
        ArrayList<RubbishEntity> allLibs = rubbishLib();
        ArrayList<RubbishEntity> resRubbishLibs = new ArrayList<>();
        for (RubbishEntity entity : allLibs){
            if (entity.rubbishType == HomeActivity.RubbishType.RUBBISH_DRY){
                resRubbishLibs.add(entity);
            }
        }
        return resRubbishLibs;
    }


}
