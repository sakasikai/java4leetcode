package advanced.dp.linear;

/**
 * a数组中的每个元素代表你在该位置可以跳跃的最大长度
 */
public class lc55 {
    // TODO 科学计数法
    boolean[] f = new boolean[(int)3e4+(int)1e5];
    public boolean canJump(int[] a) {
        // f[0] = f[1] || f[2]
        // f[1] = f[2 || 3 || 4]
        int n = a.length;

        f[n-1] = true; // 0..n-1
        for(int i=n-2; i>=0; i--){
            for(int step=1; step<=a[i]; step++){ // 跳1步or最大步数
                f[i] |= f[i+step];
                if(f[i]) break;
            }

        }

        return f[0];
    }
}
