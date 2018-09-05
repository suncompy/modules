package com.lebaoxun.upload.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.cloud.netflix.feign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.web.client.RestTemplate;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;

@Configuration
public class FeignMultipartSupportConfig {

    @Bean
    @Primary
    @Scope("prototype")
    public Encoder multipartFormEncoder() {
    	return new SpringFormEncoder(new SpringEncoder(new ObjectFactory<HttpMessageConverters>() {  
            @Override  
            public HttpMessageConverters getObject() throws BeansException {  
                return new HttpMessageConverters(new RestTemplate().getMessageConverters());  
            }  
        }));
    }
}