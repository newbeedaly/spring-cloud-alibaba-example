package cn.newbeedaly.alibaba.gateway.filter;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
public class LogGatewayFilterFactory extends AbstractGatewayFilterFactory<LogGatewayFilterFactory.LogGatewayFilterConfig> {

    public LogGatewayFilterFactory() {
        super(LogGatewayFilterConfig.class);
    }

    /**
     * 读取配置文件中的参数，赋值到config类中
     *
     * @return 参数值列表
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("consoleLog", "cacheLog");
    }

    /**
     * 过滤器逻辑
     */
    @Override
    public GatewayFilter apply(LogGatewayFilterConfig config) {
        return (exchange, chain) -> {
            if (config.isCacheLog()) {
                log.info("cacheLog已经开启了");
            }
            if (config.isConsoleLog()) {
                log.info("consoleLog已经开启了");
            }
            return chain.filter(exchange);
        };
    }

    @Getter
    @Setter
    public static class LogGatewayFilterConfig {

        private boolean consoleLog = false;
        private boolean cacheLog = false;
    }

}

