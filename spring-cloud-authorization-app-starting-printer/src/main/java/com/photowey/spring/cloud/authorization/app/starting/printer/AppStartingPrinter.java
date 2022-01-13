package com.photowey.spring.cloud.authorization.app.starting.printer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.StringUtils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * {@code AppStartingPrinter}
 *
 * @author photowey
 * @date 2022/01/13
 * @since 1.0.0
 */
@Slf4j
public class AppStartingPrinter {

    private static final String SERVER_SSL_KEY = "server.ssl.key-store";
    private static final String APPLICATION_NAME = "spring.application.name";
    private static final String PROFILES_ACTIVE = "spring.profiles.active";
    private static final String SERVER_PORT = "server.port";
    private static final String SERVLET_CONTEXT_PATH = "server.servlet.context-path";

    public static void print(ConfigurableApplicationContext applicationContext) {
        print(applicationContext, true);
    }

    public static void print(ConfigurableApplicationContext applicationContext, boolean swagger) {
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        String protocol = "http";
        String app = environment.getProperty(APPLICATION_NAME);
        String port = environment.getProperty(SERVER_PORT);
        String profileActive = environment.getProperty(PROFILES_ACTIVE);
        String contextPath = environment.getProperty(SERVLET_CONTEXT_PATH);
        if (null != environment.getProperty(SERVER_SSL_KEY)) {
            protocol = "https";
        }
        try {
            String host = InetAddress.getLocalHost().getHostAddress();
            if (swagger) {
                log.info("\n----------------------------------------------------------\n\t" +
                                "Bootstrap: the '{}' is Success!\n\t" +
                                "Application: '{}' is running! Access URLs:\n\t" +
                                "Local: \t\t{}://localhost:{}\n\t" +
                                "External: \t{}://{}:{}{}\n\t" +
                                "Swagger: \t{}://{}:{}/doc.html\n\t" +
                                "Actuator: \t{}://{}:{}{}/actuator/health\n\t" +
                                "Profile(s): {}\n----------------------------------------------------------",
                        app + " Context",
                        app,
                        protocol, port,
                        protocol, host, port, StringUtils.hasText(contextPath) ? contextPath : "",
                        protocol, host, port,
                        protocol, host, port, StringUtils.hasText(contextPath) ? contextPath : "",
                        profileActive
                );
            } else {
                log.info("\n----------------------------------------------------------\n\t" +
                                "Bootstrap: the '{}' is Success!\n\t" +
                                "Application: '{}' is running! Access URLs:\n\t" +
                                "Local: \t\t{}://localhost:{}\n\t" +
                                "External: \t{}://{}:{}{}\n\t" +
                                "Actuator: \t{}://{}:{}{}/actuator/health\n\t" +
                                "Profile(s): {}\n----------------------------------------------------------",
                        app + " Context",
                        app,
                        protocol, port,
                        protocol, host, port, StringUtils.hasText(contextPath) ? contextPath : "",
                        protocol, host, port, StringUtils.hasText(contextPath) ? contextPath : "",
                        profileActive
                );
            }
        } catch (UnknownHostException e) {
            // Ignore
        }
    }
}
