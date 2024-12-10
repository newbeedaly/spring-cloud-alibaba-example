package cn.newbeedaly.alibaba.gateway.config;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.SentinelGatewayFilter;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.BlockRequestHandler;
import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.*;

@Configuration
public class GatewayConfiguration {

    private final List<ViewResolver> viewResolvers;
    private final ServerCodecConfigurer serverCodecConfigurer;

    public GatewayConfiguration(ObjectProvider<List<ViewResolver>> viewResolversProvider, ServerCodecConfigurer serverCodecConfigurer) {
        this.viewResolvers = viewResolversProvider.getIfAvailable(Collections::emptyList);
        this.serverCodecConfigurer = serverCodecConfigurer;
    }

    /**
     * 初始化一个限流的过滤器
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public GlobalFilter sentinelGatewayFilter() {
        return new SentinelGatewayFilter();
    }

    /**
     * 配置初始化的限流参数
     */
    @PostConstruct
    public void initGatewayRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        rules.add(
                // 资源名称，对应路由ID
                new GatewayFlowRule("order_route")
                        // 限流阈值
                        .setCount(1)
                        // 统计时间窗口，单位是秒，默认是1秒
                        .setIntervalSec(1)
        );
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 配置限流的异常处理器
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public SentinelGatewayBlockExceptionHandler sentinelGatewayBlockExceptionHandler() {
        return new SentinelGatewayBlockExceptionHandler(viewResolvers, serverCodecConfigurer);
    }

    /**
     * 自定义限流异常页面
     */
    @PostConstruct
    public void initBlockHandlers() {
        BlockRequestHandler blockRequestHandler = new BlockRequestHandler() {
            @Override
            public Mono<ServerResponse> handleRequest(ServerWebExchange serverWebExchange, Throwable throwable) {
                Map<String, Object> map = new HashMap<>();
                map.put("code", 0);
                map.put("message", "接口被限流了");
                return ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(map));
            }
        };
        GatewayCallbackManager.setBlockHandler(blockRequestHandler);
    }

    // 自定义API分组，是一种更细力度的限流规则定义

    /**
     * 配置初始化的限流参数
     */
    @PostConstruct
    public void initGatewayApiRules() {
        Set<GatewayFlowRule> rules = new HashSet<>();
        // 资源名称 对应路由ID,限流阈值 单位是秒,统计时间窗口 默认是1秒
        rules.add(new GatewayFlowRule("order_api1").setCount(1).setIntervalSec(1));
        rules.add(new GatewayFlowRule("order_api2").setCount(1).setIntervalSec(1));
        GatewayRuleManager.loadRules(rules);
    }

    /**
     * 自定义API分组
     */
    @PostConstruct
    private void initCustomizedApis() {
        Set<ApiDefinition> definitions = new HashSet<>();
        ApiDefinition api1 = new ApiDefinition("order_api1")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {
                                       {
                                           // 以/order/order/sentinel1 开头的请求
                                           add(new ApiPathPredicateItem().setPattern("/order/order/sentinel1/**")
                                                   .setMatchStrategy(SentinelGatewayConstants.URL_MATCH_STRATEGY_PREFIX)
                                           );
                                       }
                                   }
                );
        ApiDefinition api2 = new ApiDefinition("order_api2")
                .setPredicateItems(new HashSet<ApiPredicateItem>() {
                                       {
                                           // 以/order/order/sentinel2/2 完全的url路径匹配
                                           add(new ApiPathPredicateItem().setPattern("/order/order/sentinel2/2"));
                                       }
                                   }
                );
        definitions.add(api1);
        definitions.add(api2);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);
    }

}

