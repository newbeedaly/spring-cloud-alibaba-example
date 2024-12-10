package cn.newbeedaly.alibaba.order.adaptor.feign.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import io.seata.core.context.RootContext;
import org.apache.http.HttpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Configuration
public class FeignConfiguration implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        requestTemplate.header(HttpHeaders.AUTHORIZATION, request.getHeader(HttpHeaders.AUTHORIZATION));
        //seata 分布式事务
        requestTemplate.header(RootContext.KEY_XID, RootContext.getXID());
    }

}
