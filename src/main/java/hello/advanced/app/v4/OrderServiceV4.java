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
		
		//ķ�ø� �޼ҵ� ���� ����
		AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {//�͸� Ŭ���� ���
			@Override
			protected Void call() {
				orderRepository.save(itemId);
				return null;
			}
		};
		
		template.execute("OrderService.orderItem()");
	}
}
