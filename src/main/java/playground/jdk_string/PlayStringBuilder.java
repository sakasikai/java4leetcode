package playground.jdk_string;

import playground.utils.SimpleMainResolver;

/**
 * @author maiqi
 * @Title: PlayStringBuilder
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/7/5 14:04
 */
public class PlayStringBuilder {
    public static void main(String[] args) {
        SimpleMainResolver r = new SimpleMainResolver(PlayStringBuilder.class);
        r.invokeAll();
    }

    public void usage() {
        String ablaze = " will be earned when you are 30";

        StringBuilder bld = new StringBuilder();
        bld.append(1000).append("w").append(ablaze);
        System.out.println(bld);

        StringBuffer buf = new StringBuffer();
        buf.append(1000).append("w").append(ablaze);
        System.out.println(buf);
    }
}
