package cn.newbeedaly.alibaba.common.controller;

import cn.newbeedaly.alibaba.common.file.IFileStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@RequestMapping("/file")
@RestController
public class FileController {

    @Autowired
    private IFileStorage fileStorage;

    @PostMapping("/upload")
    public String upload(MultipartFile dataFile) throws IOException {
        // 文件格式校验，文件是否存在，文件信息
        String fileUniqueKey = DigestUtils.md5DigestAsHex(dataFile.getInputStream());
        return fileStorage.saveFile(dataFile.getInputStream(), fileUniqueKey);
    }

    @GetMapping("/download/{fileUniqueKey}")
    public ResponseEntity<InputStreamResource> download(@RequestParam("fileUniqueKey") String fileUniqueKey) {
        // 文件是否存在，文件信息
        String fileName = "readme.txt";
        InputStream inputStream = fileStorage.download(fileUniqueKey);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        fileName = new String(fileName.replace(" ", "_").getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);
        headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.parseMediaType("application/octet-stream;charset=utf-8")).body(new InputStreamResource(inputStream));
    }

}

