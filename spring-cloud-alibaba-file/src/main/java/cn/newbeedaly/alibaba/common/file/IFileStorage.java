package cn.newbeedaly.alibaba.common.file;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface IFileStorage {

    String saveFile(InputStream fis, String fileUniqueKey);

    String getFile(String fileUniqueKey);

    InputStream download(String fileUniqueKey);

    void deleteFile(String fileUniqueKey);
}
