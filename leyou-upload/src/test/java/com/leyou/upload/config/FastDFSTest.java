package com.leyou.upload.config;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.domain.ThumbImageConfig;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * @Author:jesse
 * @Description:
 * @Date:Create in 12:29 2018/10/23
 * @Modified By:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FastDFSTest {
    @Autowired
    private FastFileStorageClient storageClient;
    @Autowired
    private ThumbImageConfig thumbImageConfig;

    @Test
    public void TestUpload() throws FileNotFoundException {
        File file = new File("D:\\file.jpg");

        StorePath storePath = this.storageClient.uploadFile(new FileInputStream(file),file.length(),
                "jpg",null);
        System.out.println(storePath.getFullPath());
        System.out.println(storePath.getPath());
    }

    @Test
    public void testUploadAndCreateThumb() throws FileNotFoundException {
        File file = new File("D:\\file.jpg");

        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(new FileInputStream(file),
                file.length(),"png",null);

        System.out.println(storePath.getFullPath());

        System.out.println(storePath.getPath());

        String path = thumbImageConfig.getThumbImagePath(storePath.getPath());
        System.out.println(path);
    }
}