package com.skel;

import com.skel.util.FilterUtil;
import org.opencv.core.Core;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class PurifierApplication {
    @Bean
    FilterUtil filterUtil() {
        return new FilterUtil();
    }

    public static void main(String[] args) {
       // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        SpringApplication.run(PurifierApplication.class, args);
    }
}
