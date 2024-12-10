package cn.newbeedaly.alibaba.common.file;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "file")
public class StorageProperties {

    /**
     * 是否开启
     */
    private Boolean enabled = true;
    /**
     * 文件上传的目录
     */
    private String path;

}
