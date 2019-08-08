package br.com.fcamara.provajava.helper;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * precisei fazer essa gambi pq o console do h2 nao sobe automaticamente qndo a aplicacao roda sobre webflux
 * @author raphaelstefani
 *
 */
@Component
public class H2Manager {

    private org.h2.tools.Server webServer;

    private org.h2.tools.Server server;

    @EventListener(org.springframework.context.event.ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        this.webServer = org.h2.tools.Server.createWebServer("-webPort", "8082", "-tcpAllowOthers").start();
        this.server = org.h2.tools.Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void stop() {
        this.webServer.stop();
        this.server.stop();
    }
}
