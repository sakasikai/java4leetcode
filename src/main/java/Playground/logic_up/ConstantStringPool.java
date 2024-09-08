package Playground.logic_up;

/**
 * @author maiqi
 * @title ConstantStringPool
 * @description TODO
 * @create 2023/9/11 15:36
 */
public class ConstantStringPool {

    public static void main(String[] args) {
        t1();
        t1();
        t2();
        t3();
        t4();
    }

    /**
     * @description: <p>
     * 对String ref;
     * 如果 ref = new String("content") or new String("??") + new String("..")，则创建对象
     * 如果 ref = "content" or "??" + ".."，则指向常量池引用
     * </p>
     * @author: maiqi
     * @update: 2023/9/11 16:23
     */
    public static void t1() {
        String s = new String("1"); // "1" 已经出现，#intern首次遇见_原则❌
        s.intern();
        String s2 = "1";
        System.out.println(s == s2); // false

        String s3 = new String("1") + new String("1");
        s3.intern(); // "11" 没有出现过，#intern首次遇见原则✅
        String s4 = "11";
        System.out.println(s3 == s4); // true
        System.out.println();
    }

    public static void t2() {
        String s = new String("2");
        String s2 = "2"; // "2" 已经出现，#intern首次遇见_原则❌
        s.intern();
        System.out.println(s == s2); // false

        String s3 = new String("2") + new String("2");
        String s4 = "22"; // "22" 已经出现，#intern首次遇见_原则❌
        s3.intern();
        System.out.println(s3 == s4); // false
        System.out.println();
    }

    public static void t3() {
        System.out.println("t3");
        String s1 = "1" + "1"; // a "11" 字符串常量引用
        System.out.println(s1 == "11"); // true
        String s2 = "2" + new String("2"); // "2" 常量引用，生成的是"22" 对象
        System.out.println(s2 == "22"); // false 常量引用 != 对象
        String s3 = new String("3") + "3"; // "3" 常量引用，生成的是"33" 对象
        System.out.println(s3 == "33"); // false 常量引用 != 对象
        String s4 = "4" + new String("4"); // "4" 常量引用，"44" 对象
        s4.intern();
        System.out.println(s4 == "44"); // true String#intern 首次遇见原则✅
        System.out.println();
    }

    /**
     * @description: <p>
     * 调用{@link String#intern}只有常量池里没有 content，才会把当前object reference直接赋值给常量池的引用
     * </p>
     * <p>
     * 即《首次遇到原则》。否则，常量池事先已经有content了，调用 intern 是无效的
     * </p>
     * @update: 2023/9/11 15:53
     */
    public static void t4() {
        System.out.println("t4");
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1 == str1.intern()); // true; 《首次遇到原则》生效✅
        System.out.println(str1 == "计算机软件"); // true
        System.out.println();


        String str2 = new StringBuilder("硬").append("件").toString();
        System.out.println(str2 == "硬件"); // false "硬件" 已经出现，#intern首次遇见_原则❌

        str2.intern();
        System.out.println(str2 == "硬件"); // false
        System.out.println();

        String str3 = new StringBuilder("java").toString(); // "java" 已经出现，#intern首次遇见_原则❌

        str3.intern();
        System.out.println(str3 == "java"); // false
        System.out.println();
    }
}
