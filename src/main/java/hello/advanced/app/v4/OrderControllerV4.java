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
		//캠플릿 메소드 패턴 적용 => solid 법칙중 SRP 단일 책임 원직을 지키게 되었다(변경이 일어났을때 하나로 모아서 변경에 쉽게 대처 할 수 있는 구조)
		/*
		 탬플릿 메소드 디자인 패턴?
		 작업에서 알고리즘의 골격을 정의하고 일부 단계를 하위 클래스로 연기합니다. 템플릿 메서도를 사용하면 하위클래스가 
		 알고리즘의 구조를 변경하지 않고도 알고리즘의 특정 단계를 재정의 할 수 있습니다
		 => 부모클래스에 알고리즘 골격인 템플릿을 정의하고 일부 변경되는 로직은 자식 클래스에 정의하는 것이다. 자식클래스가 알고리짐의 전체 구조를 변경하지 않고 
		 특정 부분만 재 정의할 수 있다. 상속과 오버라이딩을 통한 다형성으로 문제를 해결.
		 단점: 상속을 받는다는 것은 부모클래스에 의존하고 있는건데 자식 클래스 입장에서는 무보 클래스의 기능을 전혀 사용하지 않는데 부모클래스를 알아야 한다는 단점.잘못된
		 의존관계 때문에 부모 클래스 수정하면 자식클래스에도 영향.
		*/
		AbstractTemplate<String> template = new AbstractTemplate<String>(trace) {//익명 클래스 사용
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
			throw e; // 예외를 다시 던져주기
		}*/
	}
}
