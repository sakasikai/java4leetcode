package core.advanced.window;

/**
 * @author maiqi
 * @title t2
 * @description TODO
 * @create 2023/9/19 22:00
 */
public class t2 extends Thread {
    int x = 2;

    public t2(String t) {
        x = 5;
        start();
    }

    public int getN(int x) {
        int s;
        return x + 1;


    }

    public static void main(String[] args) throws Exception {
        new t2("s").makeltSo();
    }

    public void makeltSo() throws Exception {
        join();
        x = x - 1;
        System.out.println(x);
    }

    public void run() {
        x *= 2;
    }
}
