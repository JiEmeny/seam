package com.example.seam.configurer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Value("${web.upload-path}")
    private String mImagesPath;
    @Autowired
    TokenInterceptor tokenInterceptor;

    /**
     * 图片获取路径
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/JiMoney/Pic/**")
                .addResourceLocations("file:" + mImagesPath);
    }

    /**
     * 解决跨越请求
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                // 要放行的原始域名
                .allowedOriginPatterns("*")
                .allowedHeaders("*");
    }

    /**
     * 异步请求配置
     *
     * @param configurer
     */
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(new ConcurrentTaskExecutor(Executors.newFixedThreadPool(2)));
        WebMvcConfigurer.super.configureAsyncSupport(configurer);
    }

    /**
     * 配置拦截器
     * 拦截路径
     * 每次请求到拦截的路径，就会去执行拦截器中的方法
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePath = new ArrayList<>();
        // 排除拦截，除了注册登录（此时没有token），其他都拦截
        excludePath.add("/JiMoney/index/login"); // 登录
        excludePath.add("/JiMoney/index/set_user"); // 注册
        excludePath.add("/JiMoney/Pic/*"); // 静态资源
        excludePath.add("/img/**"); // 静态资源
        excludePath.add("/static/**"); // 静态资源
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**") // 拦截所有请求
                .excludePathPatterns(excludePath);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
