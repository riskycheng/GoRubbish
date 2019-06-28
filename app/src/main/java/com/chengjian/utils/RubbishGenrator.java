package com.chengjian.utils;


import com.fatfish.chengjian.gorubbish.HomeActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class RubbishGenrator {

    public static class RubbishEntity{
        private String rubbishName;
        private HomeActivity.RubbishType rubbishType;

        public String getRubbishName() {
            return rubbishName;
        }

        public void setRubbishName(String rubbishName) {
            this.rubbishName = rubbishName;
        }

        public HomeActivity.RubbishType getRubbishType() {
            return rubbishType;
        }

        public void setRubbishType(HomeActivity.RubbishType rubbishType) {
            this.rubbishType = rubbishType;
        }

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

        /*
        食材废料：谷物及其加工食品（米、米饭、面、面包、豆类）、
        肉蛋及其加工食品（鸡、鸭、猪、牛、羊肉、蛋、动物内脏、腊肉、午餐肉、蛋壳）、
        水产及其加工食品（鱼、鱼鳞、虾、虾壳、鱿鱼）、蔬菜（绿叶菜、根茎蔬菜、菌菇）、调料、酱料……
        剩菜剩饭：火锅汤底（沥干后的固体废弃物）、鱼骨、碎骨、茶叶渣、咖啡渣……
        过期食品：糕饼、糖果、风干食品（肉干、红枣、中药材）、粉末类食品（冲泡饮料、面粉）、宠物饲料……
        瓜皮果核：水果果肉（椰子肉）、水果果皮（西瓜皮、桔子皮、苹果皮）、水果茎枝（葡萄枝）、果实（西瓜籽）……
        花卉植物：家养绿植、花卉、花瓣、枝叶……
        中药药渣
         */
        //add the wet rubbish libs
        rubbishEntities.add(new RubbishEntity("米饭", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("面条", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("面包", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("鸡蛋", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("鸡肉", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("西瓜籽", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("绿植", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("中药渣", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("鸡蛋壳", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("鱼鳞", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("虾壳", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("鱿鱼", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("蔬菜叶", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("酱料", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("锅底", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("鱼骨", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("茶叶渣", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("咖啡渣", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("糕点", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("肉干", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("红枣", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("面粉", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("猫粮", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("椰肉", HomeActivity.RubbishType.RUBBISH_WET));
        rubbishEntities.add(new RubbishEntity("西瓜皮", HomeActivity.RubbishType.RUBBISH_WET));


        /*
        废纸主要包括：报纸、杂志、图书、各种包装纸、办公用纸、纸盒等，但是纸巾和卫生用纸由于水溶性太强不可回收；
        塑料主要包括：各种塑料袋、塑料包装物、一次性塑料餐盒和餐具、牙刷、杯子、矿泉水瓶等；
        玻璃主要包括：各种玻璃容器，根据回收工艺，玻璃分为无色玻璃，绿色玻璃，棕色玻璃；
        金属主要包括:易拉罐、金属罐头盒、装饰物和铝箔等，按照回收材料分类：铁类，非铁类(一般指有色金属)；
        纺织物主要包括：废弃衣服、毛巾、书包、布鞋等。
        危害垃圾主要包括:油漆、颜料、各类清洗液等化学品，部分地区将电池和电子/电器垃圾也归于此类。
         */
        //build the recycle rubbish libs
        rubbishEntities.add(new RubbishEntity("易拉罐", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("衣服", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("牙刷", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("毛巾", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("矿泉水瓶", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("报纸", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("包装纸", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("布鞋", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("铁具", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("铝箔", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("餐具", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("颜料", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("玻璃杯", HomeActivity.RubbishType.RUBBISH_RECYCLE));
        rubbishEntities.add(new RubbishEntity("书包", HomeActivity.RubbishType.RUBBISH_RECYCLE));



        /*
        有害垃圾包括废电池、废日光灯管、废水银温度计、过期药品等，这些垃圾需要特殊安全处理。
        废镍镉电池和废氧化汞电池：充电电池、镉镍电池、铅酸电池、蓄电池、纽扣电池
        废荧光灯管：荧光（日光）灯管、卤素灯
        废药品及其包装物：过期药物、药物胶囊、药片、药品内包装
        废油漆和溶剂及其包装物：废油漆桶、染发剂壳、过期的指甲油、洗甲水
        废矿物油及其包装物
        废含汞温度计、废含汞血压计：水银血压计、水银体温计、水银温度计
        废杀虫剂及其包装：老鼠药（毒鼠强）、杀虫喷雾罐
        废胶片及废相纸：x光片等感光胶片、相片底片
         */
        // build the harmful rubbish libs
        rubbishEntities.add(new RubbishEntity("电池", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("灯管", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("水银温度计", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("过期药品", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("废旧油漆", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("染发剂", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("指甲油", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("老鼠药", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("杀毒剂", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("x光片", HomeActivity.RubbishType.RUBBISH_HARM));
        rubbishEntities.add(new RubbishEntity("相片底片", HomeActivity.RubbishType.RUBBISH_HARM));

        return rubbishEntities;
    }


    public static ArrayList<RubbishEntity> getRubbishLibsByTypes(HomeActivity.RubbishType type){
        ArrayList<RubbishEntity> allLibs = rubbishLib();
        ArrayList<RubbishEntity> resRubbishLibs = new ArrayList<>();
        for (RubbishEntity entity : allLibs){
            if (entity.rubbishType == type){
                resRubbishLibs.add(entity);
            }
        }
        return resRubbishLibs;
    }


}
