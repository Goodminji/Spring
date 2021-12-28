package hello.advanced.trace.strategy;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.strategy.code.template.Callback;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateCallbackTest {

	//탬플릿콜백패턴 - 익명 내부 클래스
	@Test
	void callbackV1() {
		TimeLogTemplate template = new TimeLogTemplate();
		template.execute(new Callback() {
			
			@Override
			public void call() {
				log.info("비즈니스 로직 1 실행");
			}
		});
		
		template.execute(new Callback() {
			
			@Override
			public void call() {
				log.info("비즈니스 로직 2 실행");
			}
		});
		
	}
	
	//탬플릿콜백패턴 - 람다로
	@Test
	void callbackV2() {
		TimeLogTemplate template = new TimeLogTemplate();
		template.execute(()-> log.info("비즈니스 로직 1 실행"));
		template.execute(()-> log.info("비즈니스 로직 2 실행"));
	}
}
