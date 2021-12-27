package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/*
전략 패던이랑 변하지 않는 부분을 Context라는 곳에 두고,변하는 부분을 stratege라는 인터페이스를 만들고
해당 인터페이스를 구현하도록 해서 위임으로 문제 해결

=> 필드에 전략을 보관하는 방식
*/

@Slf4j
public class ContextV1 {

	private Strategy strategy;
	
	public ContextV1(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void execute() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		strategy.call();//위임
		//비즈니스 로직 종료
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime-startTime;
		log.info("resultTime={}",resultTime);
	}
}
