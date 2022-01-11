package hello.proxy.config.v1_proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.proxy.app.v1.OrderControllerV1;
import hello.proxy.app.v1.OrderControllerV1Impl;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.app.v1.OrderRepositoryV1Impl;
import hello.proxy.app.v1.OrderServiceV1;
import hello.proxy.app.v1.OrderServiceV1Impl;
import hello.proxy.config.v1_proxy.interface_proxy.OrderControllerInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderRepositoryInterfaceProxy;
import hello.proxy.config.v1_proxy.interface_proxy.OrderServiceInterfaceProxy;
import hello.proxy.trace.logtrace.LogTrace;

/*
프록시를 생성하고 프록시를 실제 스프링 빈 대신에 등록한다. 실제 객체는 스프링 빈으로 등록 하지 않는다.

프록시와 DI 덕분에 원본 코드 수정 안하고 로그추적기를 도입, 너무 많은 프록시 클래스를 만들어야 하는게 단점.
*/
@Configuration
public class InterfaceProxyConfig {
	//프록시를 호출
	 @Bean
     public OrderControllerV1 orderController(LogTrace logTrace) {
		 OrderControllerV1Impl orderControllerV1Impl =  new OrderControllerV1Impl(orderService(logTrace));
        return new OrderControllerInterfaceProxy(orderControllerV1Impl, logTrace);
     }

     @Bean
     public OrderServiceV1 orderService(LogTrace logTrace) {
    	 // proxy -> target
    	 // OrderServiceInterfaceProxy -> orderServoceV1Impl
    	 OrderServiceV1Impl orderServiceV1 =  new OrderServiceV1Impl(orderRepository(logTrace));
        return new OrderServiceInterfaceProxy(orderServiceV1,logTrace);
     }

     @Bean
     public OrderRepositoryV1 orderRepository(LogTrace logTrace) {
    	 OrderRepositoryV1Impl orderRepositoryV1Impl =  new OrderRepositoryV1Impl();
        return new OrderRepositoryInterfaceProxy(orderRepositoryV1Impl, logTrace);
     }
}
