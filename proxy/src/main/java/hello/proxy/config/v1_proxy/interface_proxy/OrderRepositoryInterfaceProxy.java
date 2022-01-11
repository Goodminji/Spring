package hello.proxy.config.v1_proxy.interface_proxy;

import hello.proxy.app.v1.OrderRepositoryV1;
import hello.proxy.trace.TraceStatus;
import hello.proxy.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

//리포지토리 프록시 생성 - 기존 로직 OrderRepositoryV1 그대로 이고 부가기능만 추가.
@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1{

	private final OrderRepositoryV1 target;
	private final LogTrace logTrace;
	
	@Override
	public void save(String itemId) {
		TraceStatus status = null;
		
		try {
		
			status = logTrace.begin("OrderRepository.request()");
			
			//target 호출
			target.save(itemId);
			
			logTrace.end(status);
			
		} catch (Exception e) {
			logTrace.exception(status, e);
			throw e;
		}
		
	}

}
