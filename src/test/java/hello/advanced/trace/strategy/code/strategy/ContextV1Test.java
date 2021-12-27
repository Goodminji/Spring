package hello.advanced.trace.strategy.code.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.strategy.ContextV1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
//전략 패던이랑 변하지 않는 부분을 Context라는 곳에 두고,변하는 부분을 stratege라는 인터페이스를 만들고
// 해당 인터페이스를 구현하도록 해서 위임으로 문제 해결
@Slf4j
public class ContextV1Test {
	
	@Test
	void strategyV0() {
		logic1();
		logic2();
	}
	
	private void logic1() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		log.info("비즈니르 로직1 실행");
		//비즈니스 로직 종료
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime-startTime;
		log.info("resultTime={}",resultTime);
	}

	private void logic2() {
		long startTime = System.currentTimeMillis();
		//비즈니스 로직 실행
		log.info("비즈니르 로직2 실행");
		//비즈니스 로직 종료
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime-startTime;
		log.info("resultTime={}",resultTime);
	}
	
	@Test // 전략패턴
	void strategyV1() {
		StrategyLogic1 strategyLogic1 = new StrategyLogic1();
		ContextV1 contextV1 = new ContextV1(strategyLogic1);
		
		contextV1.execute();
		
		StrategyLogic2 strategyLogic2 = new StrategyLogic2();
		ContextV1 contextV2 = new ContextV1(strategyLogic2);
		
		contextV2.execute();

	}
	
	//익명 내부 클래스 사용
	@Test
	void strategyV2() {
		
		Strategy strategyLogeic1 =  new Strategy() {
			
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
			
		};
		
		ContextV1 contextV1 = new ContextV1(strategyLogeic1);
		log.info("strategyLogeic1={}",strategyLogeic1.getClass());
		contextV1.execute();
		
		Strategy strategyLogeic2 =  new Strategy() {
			
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
			
		};
		
		ContextV1 contextV2 = new ContextV1(strategyLogeic2);
		log.info("strategyLogeic2={}",strategyLogeic2.getClass());
		contextV2.execute();
		
	}
	

	//익명 내부 클래스 사용
	@Test
	void strategyV3() {
		
		
		ContextV1 contextV1 = new ContextV1(new Strategy() {
			
			@Override
			public void call() {
				log.info("비즈니스 로직1 실행");
			}
			
		});
		contextV1.execute();
		
		ContextV1 contextV2 = new ContextV1(new Strategy() {
			
			@Override
			public void call() {
				log.info("비즈니스 로직2 실행");
			}
			
		});
		contextV2.execute();
		
	}
	
	//익명 내부 클래스 사용-람다로 사용
	@Test
	void strategyV4() {
		
		
		ContextV1 contextV1 = new ContextV1(() -> log.info("비즈니스 로직1 실행"));
		contextV1.execute();
		
		ContextV1 contextV2 = new ContextV1(() -> log.info("비즈니스 로직2 실행"));
		contextV2.execute();
		
	}
}
