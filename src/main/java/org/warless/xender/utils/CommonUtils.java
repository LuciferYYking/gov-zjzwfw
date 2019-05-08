package org.warless.xender.utils;

import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * CommonUtils
 *
 * @author :  Noa Swartz
 * @version : 1.0
 * @date : Created in 2019/5/7
 */
public class CommonUtils {

    private static final String FILE_SEPARATOR = File.separator;

    public static boolean isEmpty(byte[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isNotEmpty(byte[] arr) {
        return arr != null && arr.length != 0;
    }

    public static boolean isEmpty(Object[] arr) {
        return arr == null || arr.length == 0;
    }

    public static boolean isNotEmpty(Object[] arr) {
        return arr != null && arr.length != 0;
    }

    public static List<String> listFiles(String path) {
        File file = new File(path);
        List<String> fileList = new ArrayList<>();
        if (file.isFile()) {
            fileList.add(path);
        } else {
            File[] files = file.listFiles();
            if (isNotEmpty(files)) {
                for (File f : files) {
                    if (f.isDirectory()) {
                        fileList.addAll(listFiles(path + FILE_SEPARATOR + f.getName()));
                    } else {
                        fileList.add(path + FILE_SEPARATOR + f.getName());
                    }
                }
            }
        }
        return fileList;
    }

    public static void mkdirs(String path) {
        File destFile = new File(path);
        if (!destFile.exists()) {
            destFile.mkdirs();
        }
    }

}
