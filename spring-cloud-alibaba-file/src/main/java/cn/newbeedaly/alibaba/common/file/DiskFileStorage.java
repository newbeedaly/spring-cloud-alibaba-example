package cn.newbeedaly.alibaba.common.file;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.*;


/**
 * FastDFS文件上下文
 */
@ConditionalOnProperty(prefix = "file", name = "disk", havingValue = "true")
@EnableConfigurationProperties(StorageProperties.class)
@Component
public class DiskFileStorage extends AbstractFileStorage {

    @Autowired
    private StorageProperties storageProperties;

    @Override
    public String saveFile(InputStream fis, String fileUniqueKey) {
        try {
            String fileName = fileUniqueKey;
            FileUtils.copyInputStreamToFile(fis, new File(storageProperties.getPath() + File.separator + fileName));
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("文件上传失败");
        }
    }

    @Override
    public String getFile(String fileUniqueKey) {
        return storageProperties.getPath() + fileUniqueKey;
    }

    @Override
    public InputStream download(String fileUniqueKey) {
        File file = new File(storageProperties.getPath() + File.separator + fileUniqueKey);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("文件下载失败");
        }
    }

    @Override
    public void deleteFile(String fileUniqueKey) {
        FileUtils.deleteQuietly(new File(storageProperties.getPath() + File.separator + fileUniqueKey));
    }
}
