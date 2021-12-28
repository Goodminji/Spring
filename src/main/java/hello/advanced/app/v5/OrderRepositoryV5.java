package hello.advanced.app.v5;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Repository
public class OrderRepositoryV5 {

	private final TraceTemplate traceTamplate;
	
	public OrderRepositoryV5(LogTrace trace) {
		this.traceTamplate = new TraceTemplate(trace);
	}
	
	public void save(String itemId) {
		
		traceTamplate.execute("OrderRepository.save()", ()->{
			//���� ����
			if(itemId.equals("ex")) {
				throw new IllegalStateException("���ܹ߻�");
			}
			
			sleep(1000);
			return null;
		});
		
	}
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
