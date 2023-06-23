package IO;

import com.google.common.base.Preconditions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author maiqi
 * @Title: file_operation
 * @ProjectName Java4leetcode
 * @Description: TODO
 * @date 2023/6/23 18:04
 */
public class FileOperation {
    public static void main(String[] args) throws IOException {
        FileOperation r = new FileOperation();
        r.deleteCannonPictures();
    }

    /**
     * 　* @Description: 先生成raw_ids，jpg_ids两个数据，找到差集ids，输出对应id的图片全名，输出到文件，然后删除
     * 　* @author: maiqi
     * 　* @date: 2023/6/23 18:25
     *
     **/
    public void deleteCannonPictures() throws IOException {
        String root = "/Volumes/QLETSD32/DCIM/100CANON";
        Path pRoot = Paths.get(root);

        if (Files.notExists(pRoot)) {
            Files.createDirectory(pRoot);
            System.out.printf("create dir(%s)%n", pRoot.toAbsolutePath());
        }

        Path pCandidateIds = pRoot.resolve("RAW_pic_ids.txt");
        Path pSelectedIds = pRoot.resolve("JPG_pic_ids.txt");

        Preconditions.checkArgument(Files.exists(pCandidateIds) && Files.exists(pSelectedIds), "err");

        // 根据 id 删除对应的 pics
        Set<String> sCandidates = Files.readAllLines(pCandidateIds)
                .stream().flatMap(ln -> Arrays.stream(ln.split(" ")))
                .collect(Collectors.toSet());

        Set<String> sSelected = Files.readAllLines(pSelectedIds).stream().flatMap(ln -> Arrays.stream(ln.split(" ")))
                .collect(Collectors.toSet());

        sCandidates.removeAll(sSelected);
        Set<String> sToBeDeleted = sCandidates.stream().map(id -> String.format("IMG_%s.CR3", id)).collect(Collectors.toSet());

        Path pRes = pRoot.resolve("to_be_deleted_ids.txt");
        Files.deleteIfExists(pRes);
        Files.write(pRes, sToBeDeleted);

        System.out.printf("result file is: %s\n", pRes.toAbsolutePath());
        System.out.printf("\nexecute cmd %s%n", "cat to_be_deleted_ids.txt| xargs -I {} rm ./RAW/{}");
        System.out.println("done!");
    }
}
