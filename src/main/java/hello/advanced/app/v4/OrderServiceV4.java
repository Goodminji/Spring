package hello.advanced.app.v4;

import org.springframework.stereotype.Service;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceV4 {

	private final OrderRepositoryV4 orderRepository;
	private final LogTrace trace;
	
	public void orderItem(String itemId) {
		
		//캠플릿 메소드 패턴 적용
		AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {//익명 클래스 사용
			@Override
			protected Void call() {
				orderRepository.save(itemId);
				return null;
			}
		};
		
		template.execute("OrderService.orderItem()");
	}
}
