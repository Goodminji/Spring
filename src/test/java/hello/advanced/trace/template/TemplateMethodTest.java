package hello.advanced.trace.template;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TemplateMethodTest {
	
	@Test
	void templateMethodV0() {
		logic1();
		logic2();
	}
	
	private void logic1() {
		long startTime = System.currentTimeMillis();
		//����Ͻ� ���� ����
		log.info("����ϸ� ����1 ����");
		//����Ͻ� ���� ����
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime-startTime;
		log.info("resultTime={}",resultTime);
	}

	private void logic2() {
		long startTime = System.currentTimeMillis();
		//����Ͻ� ���� ����
		log.info("����ϸ� ����2 ����");
		//����Ͻ� ���� ����
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime-startTime;
		log.info("resultTime={}",resultTime);
	}
}
