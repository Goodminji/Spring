package hello.advanced.app.v4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderControllerV4 {

	private final OrderServiceV4 orderService;
	private final LogTrace trace;
	
	
	@GetMapping("/v4/request")
	public String request(String itemId) {
		//ķ�ø� �޼ҵ� ���� ���� => solid ��Ģ�� SRP ���� å�� ������ ��Ű�� �Ǿ���(������ �Ͼ���� �ϳ��� ��Ƽ� ���濡 ���� ��ó �� �� �ִ� ����)
		/*
		 ���ø� �޼ҵ� ������ ����?
		 �۾����� �˰����� ����� �����ϰ� �Ϻ� �ܰ踦 ���� Ŭ������ �����մϴ�. ���ø� �޼����� ����ϸ� ����Ŭ������ 
		 �˰����� ������ �������� �ʰ� �˰����� Ư�� �ܰ踦 ������ �� �� �ֽ��ϴ�
		 => �θ�Ŭ������ �˰��� ����� ���ø��� �����ϰ� �Ϻ� ����Ǵ� ������ �ڽ� Ŭ������ �����ϴ� ���̴�. �ڽ�Ŭ������ �˰����� ��ü ������ �������� �ʰ� 
		 Ư�� �κи� �� ������ �� �ִ�. ��Ӱ� �������̵��� ���� ���������� ������ �ذ�.
		 ����: ����� �޴´ٴ� ���� �θ�Ŭ������ �����ϰ� �ִ°ǵ� �ڽ� Ŭ���� ���忡���� ���� Ŭ������ ����� ���� ������� �ʴµ� �θ�Ŭ������ �˾ƾ� �Ѵٴ� ����.�߸���
		 �������� ������ �θ� Ŭ���� �����ϸ� �ڽ�Ŭ�������� ����.
		*/
		AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {//�͸� Ŭ���� ���
			@Override
			protected String call() {
				orderService.orderItem(itemId);
				return "ok";
			}
		};
		
		return template.execute("OrderController.request()");
		/*TraceStatus status = null;
		try {
			status = trace.begin("OrderController.request()");
			
			trace.end(status);
			return "ok";
		} catch(Exception e) {
			trace.exception(status, e);
			throw e; // ���ܸ� �ٽ� �����ֱ�
		}*/
	}
}
