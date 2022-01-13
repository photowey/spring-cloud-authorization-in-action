package com.photowey.spring.cloud.authorization.resource;

import com.photowey.spring.cloud.authorization.app.starting.printer.AppStartingPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@code ResourceApp}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@SpringBootApplication
public class ResourceApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ResourceApp.class, args);
        AppStartingPrinter.print(applicationContext, false);
    }

}
