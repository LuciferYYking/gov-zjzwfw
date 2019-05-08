package org.warless.xender.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.warless.xender.XenderApplication;
import org.warless.xender.constant.Common;
import org.warless.xender.utils.CommonUtils;
import org.warless.xender.utils.Zip;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Xender
 *
 * @author : Noa Swartz
 * @version : 1.0.0
 * @date : 2019/5/8
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = XenderApplication.class)
public class MainTest {

    @Test
    public void compress() throws IOException {
        String src = "E:\\workspaces\\temp\\files";
        String dest = "E:\\workspaces\\temp\\A.zip";
        Zip zip = new Zip(StandardCharsets.UTF_8);
        zip.compress(src, dest);
    }

    @Test
    public void decompress() throws IOException {
        String src = "E:\\workspaces\\temp\\A.zip";
        String dest = "E:\\workspaces\\temp\\A";
        Zip zip = new Zip(StandardCharsets.UTF_8);
        zip.decompress(src, dest);
    }


}
