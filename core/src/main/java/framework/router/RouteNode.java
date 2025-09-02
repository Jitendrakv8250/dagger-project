package framework.router;

import io.netty.handler.codec.http.HttpMethod;

import java.util.function.Supplier;

public record RouteNode(HttpMethod method, String path, Supplier<?> handler,String contentType){}
