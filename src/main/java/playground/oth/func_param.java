package playground.oth;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class func_param {
    public static void main(String[] args) {
//        int a=1;
//        increase(a);
//        System.out.println(a); // remain 1
//
//        String s="hello";
//        complete(s);
//        System.out.println(s); // remain hello

//        int[] height = {5,0,2,1,4,0,1,0,3};
//
//        System.out.println(trap(height));

//        playByte();

        String hs;
        System.out.printf("h ^ (h >>> 16) = %35s\n", (hs = Integer.toBinaryString(hash("hahaha"))));
        System.out.printf("前%d位不动%n", hs.length() - 16);
        System.out.printf("后%d位和前%d位异或%n", 16, hs.length() - 16);
    }

    static final int hash(Object key) {
        int h;
        //计算key的hashCode, h = Objects.hashCode(key)
        //h >>> 16表示对h无符号右移16位，高位补0，然后h与h >>> 16按位异或

        h = key.hashCode();
        System.out.printf("h              = %35s\n", Integer.toBinaryString(h));
        System.out.printf("h >>> 16       = %35s\n", Integer.toBinaryString(h >>> 16));
        return (key == null) ? 0 : h ^ (h >>> 16);
    }

    public static void playByte() {
        int len = 6;
        int ctl = 1 << len;
        System.out.println(Byte.parseByte(String.valueOf(ctl)));
    }

    public static void increase(int x) {
        x++;
    }

    public static void increase(){
        List<String> lowers = new LinkedList<>(), digits = new LinkedList<>(), uppers = new LinkedList<>();

        for(char i='0'; i<='z'; i++){
            if(!Character.isLetterOrDigit(i)) {
            }
            else if(Character.isDigit(i))
                digits.add(String.valueOf(i));
            else if(Character.isUpperCase(i))
                uppers.add(String.valueOf(i));
            else
                lowers.add(String.valueOf(i));
        }

//        System.out.println(digits + " " + digits.size());
//        System.out.println(uppers + " " + uppers.size());
//        System.out.println(lowers + " " + lowers.size());

        String collect = Stream.of('0', '1', 'A', 'Z', 'a', 'z').
                map(String::valueOf).
                collect(Collectors.joining("-"));

        System.out.println(collect);
        collect += "x,3";


        char[] chars = collect.toCharArray();
        System.out.println(Arrays.toString(chars));
        String[] split = collect.split("[,-]");
        System.out.println(Arrays.toString(split));

//        out.forEach(i-> System.out.printf("%d ", Integer.valueOf(i)));


    }

    public static void complete(String tmp){
        tmp +=" java";
    }


    public static int trap(int[] h) {
        int n = h.length;
        Stack<Integer> ldesc=new Stack<>(), rdesc=new Stack<>(); // dir-mono-stack
        int[] lhidx=new int[n], rhidx=new int[n]; // dir-higher-index
        Arrays.fill(lhidx, -1);
        Arrays.fill(rhidx, -1);

        // 利用 mono-stack 构建 hidx
        for(int i=0; i<n; i++){
            while(ldesc.size()>0 && h[ldesc.peek()] < h[i]) ldesc.pop(); // 弹掉小于的
            if(ldesc.size()>0) lhidx[i] = ldesc.peek(); // 留下大于等于的，-1的话说明没有
            ldesc.push(i);
        }

        for(int i=n-1; i>=0; i--){
            while(rdesc.size()>0 && h[rdesc.peek()] < h[i]) rdesc.pop();
            if(rdesc.size()>0) rhidx[i] = rdesc.peek();
            rdesc.push(i);
        }

        int highest = 0; // index，可能多个
        for(int i=1; i<n; i++)
            if(h[i] > h[highest]){
                highest = i;
            }

        int water=0;
        for(int l=0, r=0; r<=highest;){
            if(rhidx[l]!=-1 && rhidx[l]<=highest){ // 有右边更高的，r就确定
                r = rhidx[l];
                // cout  << h[l] << "," << h[r] << endl;
                for(int i=l+1; i<r; i++) water += h[l]-h[i];
                l = r;
            } else { // 没有
                break;
            }
        }

        for(int l=n-1, r=l; l>=highest;){
            if(lhidx[r]!=-1 && lhidx[r]>=highest){
                l = lhidx[r];
                for(int i=r-1; i>l; i--) water += h[r]-h[i];
                r = l;
            } else {
                break;
            }
        }

        return water;
    }


}
