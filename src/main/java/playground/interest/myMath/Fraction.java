package playground.interest.myMath;

import lombok.Builder;
import org.springframework.util.Assert;

import java.util.Scanner;

@Builder
public class Fraction {

    private int molecular;            //分子
    private int denominator;        //分母

    public int getMolecular() {    //获取分子
        return molecular;
    }

    public int getDenominator() {  //获取分母
        return denominator;
    }

    private Fraction(int molecular, int denominator) { //构造器1
        this.molecular = molecular;
        if (denominator == 0) {
            System.out.print("分母不能为零");
        } else {
            this.denominator = denominator;
        }
        reduce();
    }

    private Fraction(int denominator) {                //构造器2
        this.molecular = 1;                 //此构造器中分子为1
        if (denominator == 0) {
            throw new ArithmeticException("分母不能为零");
        } else {
            this.denominator = denominator;
        }
        reduce();
    }

    public static Fraction of(int molecular, int denominator) {
        Assert.isTrue(denominator != 0, "分母不能为零");
        return builder()
                .molecular(molecular)
                .denominator(denominator)
                .build();
    }

    public Fraction ADD(Fraction b) {    //a+b
        return new Fraction(this.molecular * b.denominator + this.denominator * b.molecular
                , this.denominator * b.denominator);
    }

    public Fraction SUB(Fraction b) {    //a-b
        return new Fraction(this.molecular * b.denominator - this.denominator * b.molecular
                , this.denominator * b.denominator);
    }

    public Fraction mul(Fraction b) {    //a*b
        return new Fraction(this.molecular * b.molecular, this.denominator * b.denominator);
    }

    public Fraction div(Fraction b) {    //a/b
        return new Fraction(this.molecular * b.denominator, this.denominator * b.molecular);
    }

    public Integer toInteger() {
        reduce();
        return Math.round((float) this.molecular / this.denominator);
    }

    public String toString() {
        //字符串输出
        reduce();
        return String.format("%d/%d", this.molecular, this.denominator);
    }

    public boolean le(Fraction b) {
        return !this.ge(b);
    }

    public boolean ge(Fraction b) {
        if (b.molecular == 0) {
            return this.molecular > 0;
        }
        Fraction A_div_B = this.div(b);
        return A_div_B.molecular >= A_div_B.denominator;
    }

    public boolean eq(Fraction b) {
        if (b.molecular == 0) {
            return this.molecular == 0;
        }
        Fraction A_div_B = this.div(b);
        return A_div_B.molecular == A_div_B.denominator;
    }

    public boolean ne(Fraction b) {
        return !eq(b);
    }

    public Fraction abs() {
        return new Fraction(Math.abs(molecular), Math.abs(denominator));
    }

    public Fraction reduce() {   //化简分数
        int gcd = this.gcd(this.molecular, this.denominator);
        this.molecular /= gcd;
        this.denominator /= gcd;
        return this;
    }

    private int gcd(int a, int b) {   //辗转相除法求最大公约数
        int mod = a % b;
        if (mod == 0) {
            return b;
        } else {
            return gcd(b, mod);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);    //创建扫描器对象
        System.out.print("输入第一个分数的分子:");
        int a1 = input.nextInt();       //接受第一个分数的分子
        System.out.print("输入第一个分数的分母:");
        int a2 = input.nextInt();       //接受第一个分数的分母
        System.out.print("输入第二个分数的分母:");
        int b2 = input.nextInt();       //接受第二个分数的分母
        Fraction a = new Fraction(a1, a2);   //使用第一个构造器
        Fraction b = new Fraction(b2);        //使用第二个构造器
        System.out.println(a.toString());
        System.out.println(b.toString());
        System.out.println(a.ADD(b).toString());
        System.out.println(a.SUB(b).toString());
        System.out.println(a.mul(b).toString());
        System.out.println(a.div(b).toString());
        System.out.println(a.le(b));
        System.out.println(a.ge(b));
        System.out.println(a.eq(a));

    }
}