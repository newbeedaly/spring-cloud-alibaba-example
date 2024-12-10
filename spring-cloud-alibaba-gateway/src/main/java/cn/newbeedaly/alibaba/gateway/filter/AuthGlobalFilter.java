package cn.newbeedaly.alibaba.gateway.filter;

import com.alibaba.nacos.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 全局认证过滤器
 */
@Slf4j
@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("执行一次全局过滤器, 请求ID为: " + exchange.getRequest().getId());
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (StringUtils.isNotBlank(token)) {
            log.info("鉴权失败");
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        // 继续向下执行
        return chain.filter(exchange);
    }

    /**
     * 优先级：值越小优先级越高
     *
     * @return 优先级
     */
    @Override
    public int getOrder() {
        return 0;
    }

}

