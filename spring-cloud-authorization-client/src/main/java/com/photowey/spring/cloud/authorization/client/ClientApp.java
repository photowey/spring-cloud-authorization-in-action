package com.photowey.spring.cloud.authorization.client;

import com.photowey.spring.cloud.authorization.app.starting.printer.AppStartingPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@code ClientApp}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@SpringBootApplication
public class ClientApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ClientApp.class, args);
        AppStartingPrinter.print(applicationContext, false);
    }

}
