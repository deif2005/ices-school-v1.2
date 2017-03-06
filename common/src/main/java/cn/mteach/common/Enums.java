package cn.mteach.common;

/**
 * Created by Sugior on 2016/8/11.
 */
public class Enums {
    /**
     * 普通枚举
     *
     * @author Sugior
     */
    public enum ColorEnum {
        red, green, yellow, blue
    }

    /**
     * 枚举像普通的类一样可以添加属性和方法，可以为它添加静态和非静态的属性或方法
     *
     * @author Sugior
     */
    public enum SeasonEnum {
        //注：枚举写在最前面，否则编译出错
        spring, summer, autumn, winter;

        private final static String position = "test";

        public static SeasonEnum getSeason() {
            return ("test".equals(position)) ? spring : winter;
        }
    }

    /**
     * 星期
     *
     * 实现带有抽象方法的枚举
     *
     * @author Sugior
     */
    //==================================================================================
    public enum WeekEnum {

        MON {
            public String getName() {
                return "星期一";
            }
        }, TUES {
            public String getName() {
                return "星期二";
            }
        }, WEB {
            public String getName() {
                return "星期三";
            }
        }, THUR {
            public String getName() {
                return "星期四";
            }
        }, FRI {
            public String getName() {
                return "星期五";
            }
        }, SAT {
            public String getName() {
                return "星期六";
            }
        }, SUN {
            public String getName() {
                return "星期日";
            }
        };

        public abstract String getName();
    }

    //==================================================================================

    /**
     * 性别
     *
     * 实现带有构造器的枚举
     *
     * @author Sugior
     */
    public enum SexEnum {
        //通过括号赋值,而且必须带有一个参构造器和一个属性跟方法，否则编译出错
        //赋值必须都赋值或都不赋值，不能一部分赋值一部分不赋值；如果不赋值则不能写构造器，赋值编译也出错
        MAN("MAN"), WOMEN("WOMEN");

        private final String value;

        //构造器默认也只能是private, 从而保证构造函数只能在内部使用
        SexEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    //==================================================================================
    public enum LampEnum {
        /*每个枚举元素各表示一个方向的控制灯*/
        S2N("N2S", "S2W", false), S2W("N2E", "E2W", false), E2W("W2E", "E2S", false), E2S("W2N", "S2N", false),
        /*下面元素表示与上面的元素的相反方向的灯，它们的“相反方向灯”和“下一个灯”应忽略不计！*/
        N2S(null, null, false), N2E(null, null, false), W2E(null, null, false), W2N(null, null, false),
        /*由南向东和由西向北等右拐弯的灯不受红绿灯的控制，所以，可以假想它们总是绿灯*/
        S2E(null, null, true), E2N(null, null, true), N2W(null, null, true), W2S(null, null, true);

        /*与当前灯同时为绿的对应方向*/
        private String opposite;
        /*当前灯变红时下一个变绿的灯*/
        private String next;
        /*当前灯是否为绿*/
        private boolean lighted;

        public boolean isLighted() {
            return  lighted;
        }
        /*构造函数LampEnum*/
        LampEnum(String opposite, String next, boolean lighted) {
            this.opposite = opposite;
            this.next = next;
            this.lighted = lighted;
        }
        /**
         * 某个灯变绿时，它对应方向的灯也要变绿
         */
        public void light() {
            this.lighted = true;
            if (opposite != null) {
                LampEnum.valueOf(opposite).light();
            }
            System.out.println(name() + " lamp is green，下面总共应该有6个方向能看到汽车穿过！");
        }
        /**
         * 某个灯变红时，对应方向的灯也要变红，并且下一个方向的灯要变绿
         *
         * @return 下一个要变绿的灯
         */
        public LampEnum blackOut() {
            this.lighted = false;
            if (opposite != null) {
                LampEnum.valueOf(opposite).blackOut();
            }

            LampEnum nextLamp = null;
            if (this.next != null) {
                nextLamp = LampEnum.valueOf(this.next);
                System.out.println("绿灯从" + name() + "-------->切换为" + this.next);
                nextLamp.light();
            }
            return nextLamp;
        }
    }


    //====================================================================================
    public static void main(String[] args) {
        //枚举是一种类型，用于定义变量，以限制变量的赋值；赋值时通过“枚举名.值”取得枚举中的值
        ColorEnum colorEnum = ColorEnum.blue;
        switch (colorEnum) {
            case red:
                System.out.println("color is red");
                break;
            case green:
                System.out.println("color is green");
                break;
            case yellow:
                System.out.println("color is yellow");
                break;
            case blue:
                System.out.println("color is blue");
                break;
        }
        //遍历枚举
        System.out.println("遍历ColorEnum枚举中的值");
        for (ColorEnum color : ColorEnum.values()) {
            System.out.println(color);
            System.out.println(ColorEnum.valueOf("red"));
        }

        //获取枚举的个数
        System.out.println("ColorEnum枚举中的值有" + ColorEnum.values().length + "个");

        //获取枚举的索引位置，默认从0开始
        System.out.println(ColorEnum.red.ordinal());//0
        System.out.println(ColorEnum.green.ordinal());//1
        System.out.println(ColorEnum.yellow.ordinal());//2
        System.out.println(ColorEnum.blue.ordinal());//3

        //枚举默认实现了java.lang.Comparable接口
        System.out.println(ColorEnum.red.compareTo(ColorEnum.green));//-1

        //--------------------------
        System.out.println("===========");
        System.err.println("季节为" + SeasonEnum.getSeason());

        //--------------
        System.out.println("===========");
        for (SexEnum sexEnum : SexEnum.values()) {
            System.out.println(sexEnum.value);
        }

        //--------------
        System.out.println("===========");
        WeekEnum weekEnum = WeekEnum.MON;
        System.out.println(weekEnum.getName());
        for (WeekEnum week : WeekEnum.values()) {
            System.out.println(week.getName());
        }

        for (LampEnum lamp:LampEnum.values()){
            System.out.println(lamp);
            lamp.light();
            System.out.println(lamp.next);
            lamp.isLighted();
        }
    }
}
