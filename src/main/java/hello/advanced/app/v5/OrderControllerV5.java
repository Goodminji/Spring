package hello.advanced.app.v5;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.callback.TraceCallback;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@RestController
public class OrderControllerV5 {

	private final OrderServiceV5 orderService;
	private final TraceTemplate traceTamplate;

	public OrderControllerV5(OrderServiceV5 orderService,LogTrace trace) {
		this.orderService = orderService;
		this.traceTamplate = new TraceTemplate(trace);
	}
	
	@GetMapping("/v5/request")
	public String request(String itemId) {
		/*
		 ���ø� �ݹ� ������ ����?
	  */
		
		return traceTamplate.execute("OrderController.request()", new TraceCallback<>() {

			@Override
			public String call() {
				orderService.orderItem(itemId);
				return "ok";
			}
		});
		
	}
}
