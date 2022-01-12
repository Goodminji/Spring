package hello.proxy.config.v1_proxy.concrete_proxy;

import hello.proxy.app.v2.OrderServiceV2;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;

public class OrderServiceConcreteProxy extends OrderServiceV2{

	private final OrderServiceV2 target;
	private final LogTrace logTrace;
	
	public OrderServiceConcreteProxy(OrderServiceV2 target,LogTrace logTrace) {
		/*
		 자바 기본 문법에 의해서 자식클래스을 생성할때는 항상 super로 부모 클래스의 생성자를 호출해줘야 한다. 이부분을
		  생략하면 기본 생성자가 호출이 된다.
		  부모에 기본 생성자 말고 파라미터가 있는 생성자가 있기 때문에 호출(super(파라미터))을 해줘야함 
		  프록시는 부모 객체의 기능을 사용하지 않기 때문에 null로 입력 해도 된다.
		*/
		super(null);
		this.target = target;
		this.logTrace = logTrace;
	}
	
	@Override
	public void orderItem(String itemId) {
		TraceStatus status = null;
		
		try {
		
			status = logTrace.begin("OrderService.orderItem()");
			
			//target 호출
			target.orderItem(itemId);
			
			logTrace.end(status);
			
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
	}
}
