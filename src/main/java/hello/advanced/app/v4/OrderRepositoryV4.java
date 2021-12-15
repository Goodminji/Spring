package hello.advanced.app.v4;

import org.springframework.stereotype.Repository;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryV4 {

	private final LogTrace trace;
	
	public void save(String itemId) {
		
		
		//캠플릿 메소드 패턴 적용
		AbstractTemplate<Void> template = new AbstractTemplate<Void>(trace) {//익명 클래스 사용
			@Override
			protected Void call() {
				//저장 로직
				if(itemId.equals("ex")) {
					throw new IllegalStateException("예외발생");
				}
				
				sleep(1000);
				return null;
			}
		};
		
		template.execute("OrderRepository.save()");
		
	}
	
	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
