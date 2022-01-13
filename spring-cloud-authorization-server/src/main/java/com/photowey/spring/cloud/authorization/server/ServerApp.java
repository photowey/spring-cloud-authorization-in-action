package com.photowey.spring.cloud.authorization.server;

import com.photowey.spring.cloud.authorization.app.starting.printer.AppStartingPrinter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * {@code ServerApp}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@SpringBootApplication
public class ServerApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServerApp.class, args);
        AppStartingPrinter.print(applicationContext, false);
    }

}
