package fr.simplex_software.k8s.tests;

import io.undertow.*;

import java.net.*;

public class UndertowServer
{
  public static void main(String[] args) throws UnknownHostException
  {
    Undertow.builder()
      .addHttpListener(8080, InetAddress.getLocalHost().getHostAddress())
      .setHandler((exchange) -> exchange.getResponseSender().send("hello world"))
      .build().start();
  }
}
