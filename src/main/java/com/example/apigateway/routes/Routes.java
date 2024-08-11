package com.example.apigateway.routes;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.FilterFunctions.setPath;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration(proxyBeanMethods = false)
public class Routes {

  @Bean
  public RouterFunction<ServerResponse> productServiceRoute() {
    return route("product_service")
        .route(RequestPredicates.path("/api/product"), http("http://localhost:8083"))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> orderServiceRoute() {
    return route("order_service")
        .route(RequestPredicates.path("/api/order"), http("http://localhost:8081"))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> customerServiceRoute() {
    return route("inventory_service")
        .route(RequestPredicates.path("/api/inventory"), http("http://localhost:8082"))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> productServiceSwaggerRoute() {
    return GatewayRouterFunctions.route("product_service_swagger")
        .route(RequestPredicates.path("/aggregate/product-service/v3/api-docs"), http("http://localhost:8083"))
        .filter(setPath("/api-docs"))
        .build();
  }

//  @Bean
//  public RouterFunction<ServerResponse> orderServiceSwaggerRoute() {
//    return GatewayRouterFunctions.route("order_service_swagger")
//        .route(RequestPredicates.path("/aggregate/order-service/v3/api-docs"), http("http://localhost:8081"))
//        .filter(setPath("/api-docs"))
//        .build();
//  }
//
//  @Bean
//  public RouterFunction<ServerResponse> inventoryServiceSwaggerRoute() {
//    return GatewayRouterFunctions.route("inventory_service_swagger")
//        .route(RequestPredicates.path("/aggregate/inventory-service/v3/api-docs"), http("http://localhost:8082"))
//        .filter(setPath("/api-docs"))
//        .build();
//  }
}
