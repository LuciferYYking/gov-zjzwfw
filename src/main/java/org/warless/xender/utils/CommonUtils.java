package org.warless.xender.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
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

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return collection != null && !collection.isEmpty();
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

    public static void copy(String src, String dest) {
        File file = new File(src);
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dest + File.separator + file.getName());
            byte[] buffer = new byte[2048];
            int len;
            while ((len =in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (IOException ignored) {}
        }
    }

}
