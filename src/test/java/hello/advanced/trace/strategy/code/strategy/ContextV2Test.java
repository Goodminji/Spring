package hello.advanced.trace.strategy.code.strategy;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContextV2Test {

	
	//�������� ���
	@Test
	void strategyV1() {
		ContextV2 context = new ContextV2();
		context.execute(new StrategyLogic1());
		context.execute(new StrategyLogic2());
	}
	

	//�������� ��� - �͸� ���� Ŭ��
	@Test
	void strategyV2() {
		ContextV2 context = new ContextV2();
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("����Ͻ� ����1 ����");
			}
		});
		context.execute(new Strategy() {
			@Override
			public void call() {
				log.info("����Ͻ� ����2 ����");
			}
		});
	}
	
	//�������� ��� -����
	@Test
	void strategyV3() {
		ContextV2 context = new ContextV2();
		context.execute(() ->log.info("����Ͻ� ����1 ����"));
		context.execute(() ->log.info("����Ͻ� ����2 ����"));
	}
}
