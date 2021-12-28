package hello.advanced.app.v5;

import org.springframework.stereotype.Service;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;

@Service
public class OrderServiceV5 {

	private final OrderRepositoryV5 orderRepository;
	private final TraceTemplate traceTamplate;
	
	public OrderServiceV5(OrderRepositoryV5 orderRepository,LogTrace trace) {
		this.orderRepository = orderRepository;
		this.traceTamplate = new TraceTemplate(trace);
		
	}
	public void orderItem(String itemId) {
		
		traceTamplate.execute("OrderService.orderItem()", () -> {
			orderRepository.save(itemId);
			return null;
		});
	}
}
