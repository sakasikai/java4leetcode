package loacl_file_job;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @title txt
 * @description TODO
 * @create 2024/1/25 10:08
 */
public class TxtJob {
    String path = "data/1.txt";

    public static void main(String[] args) throws IOException {
        TxtJob r = new TxtJob();
        r.paperPattern();
    }

    public void paperPattern() throws IOException {
        Path p = Paths.get(path);
        if (Files.exists(p)) {
//            System.out.println(Files.readAllLines(p));
            List<String> collect = Files.readAllLines(p)
                    .stream().filter(s -> !s.isEmpty()).collect(Collectors.toList());
            String pattern = "%s（%s，%s），";
            Preconditions.checkArgument(collect.size() == 15 * 3, "err");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 15; i++) {
                sb.append(String.format(pattern,
                        collect.get(i), collect.get(i + 15), collect.get(i + 30)));
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        } else {
            System.out.printf("%s not found%n", p.toAbsolutePath());
        }
    }
}
