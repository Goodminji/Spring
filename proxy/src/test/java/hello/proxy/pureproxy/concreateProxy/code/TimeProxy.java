package hello.proxy.pureproxy.concreateProxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//자식클래스(다형성)
public class TimeProxy extends ConcreateLogic{

	private ConcreateLogic concreateLogic;
	
	public TimeProxy(ConcreateLogic concreateLogic) {
		this.concreateLogic = concreateLogic;
	}
	
	//다형성으로 부모꺼 오버라이드로 사용
	@Override
	public String operation() {
		log.info("TimeProxy 실행");
		long startTime = System.currentTimeMillis();
		
		String result = concreateLogic.operation();
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("TimeProxy 종료 resultTime={}ms",resultTime);
		
		return result;
	}
}
