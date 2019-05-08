package org.warless.xender.utils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/8
 */
public class Zip {

    private Charset charset;

    public Zip(Charset charset) {
        this.charset = charset;
    }

    public void compress(String src, String dest) throws IOException {
        ZipOutputStream zout = new ZipOutputStream( new FileOutputStream(dest), this.charset);
        List<String> files = CommonUtils.listFiles(src);
        for (String f : files) {
            zout.putNextEntry(new ZipEntry(f.substring(src.length() + 1)));
            FileInputStream fos = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fos);
            int len;
            while((len = bis.read()) != -1) {
                zout.write(len);
                zout.flush();
            }
            bis.close();
            fos.close();
        }
        zout.close();
    }

    public void decompress(String src, String dest) throws IOException {
        CommonUtils.mkdirs(dest);
        ZipInputStream zin = new ZipInputStream(new FileInputStream(src), this.charset);
        ZipEntry entry;
        while (((entry = zin.getNextEntry()) != null) && !entry.isDirectory()) {
            String file = entry.getName();
            if (file.contains(File.separator)) {
                String dir = dest + File.separator + file.substring(0, file.indexOf(File.separator));
                CommonUtils.mkdirs(dir);
            }
            String path = dest + File.separator + file;
            OutputStream out = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = zin.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            out.close();
        }
        zin.close();
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }
}
