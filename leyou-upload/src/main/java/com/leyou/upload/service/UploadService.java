package com.leyou.upload.service;


import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.leyou.upload.controller.UploadController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author:jesse
 * @Description: 上传文件到分布式文件系统。
 * @Date:Create in 22:40 2018/10/22
 * @Modified By:
 */
@Service
public class UploadService {

    @Autowired
    private FastFileStorageClient storageClient;

    private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
    private static final List<String> suffixes = Arrays.asList("image/png","image/jpeg");
    public String upload(MultipartFile file) {
        try {
            String type = file.getContentType();
            if (!suffixes.contains(type)){
                logger.info("上传失败，文件类型不匹配:{}",type);
                return null;
            }
            BufferedImage image = ImageIO.read(file.getInputStream());
            if (image == null){
                logger.info("上传失败，文件内容不符合要求");
                return null;
            }

         //   file.transferTo(new File("D:\\project\\images\\upload\\" + file.getOriginalFilename()));
            String extension = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
            // 2.2、上传
            StorePath storePath = this.storageClient.uploadFile(
                    file.getInputStream(), file.getSize(), extension, null);
            String url = "http://image.leyou.com/"+storePath.getFullPath();
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
