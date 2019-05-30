package com.joshcummings.codeplay.terracotta.testng;

import java.net.InetSocketAddress;

import org.littleshoot.proxy.HttpProxyServer;
import org.littleshoot.proxy.impl.DefaultHttpProxyServer;
import org.testng.ITestContext;

public class ProxySupport {
    protected static HttpProxyServer proxy;

    public static void main(String[] args) {
        new ProxySupport().start("tomcat");
    }

    public void start(ITestContext ctx) {
        start(ctx.getName());
    }

    public void start(String type) {
        proxy = DefaultHttpProxyServer.bootstrap()
            .withPort(8081)
            .withServerResolver((host, port) -> {
                if (host.equals(TestConstants.host) ||
                    host.equals(TestConstants.evilHost)) {
                    return new InetSocketAddress("docker".equals(type) ? "host.docker.internal" : "localhost", 8080);
                }
                return new InetSocketAddress(host, port);
            })
            .start();
    }

    public void stop() {
        proxy.stop();
    }
}
