package hello.advanced.trace.template;

import org.junit.jupiter.api.Test;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
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
	
	@Test // ���ø� �޼��� ���� ����
	void templeteMethodV1() {
		AbstractTemplate template1 = new SubClassLogic1();
		template1.execute();
		
		AbstractTemplate template2 = new SubClassLogic2();
		template2.execute();

	}
	@Test // ���ø� �޼��� ���� ����
	void templeteMethodV2() {
		AbstractTemplate template1 = new AbstractTemplate() { // �͸�����Ŭ����
			
			@Override
			protected void call() {
				log.info("����ϸ� ����1 ����");
			}
		};

		log.info("Ŭ���� �̸�1={}",template1.getClass());
		template1.execute();
		
		AbstractTemplate template2 = new AbstractTemplate() {
			
			@Override
			protected void call() {
				log.info("����ϸ� ����2 ����");
			}
		};
		log.info("Ŭ���� �̸�2={}",template2.getClass());
		template2.execute();
	}
}