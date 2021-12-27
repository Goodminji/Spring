package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/*
���� �д��̶� ������ �ʴ� �κ��� Context��� ���� �ΰ�,���ϴ� �κ��� stratege��� �������̽��� �����
�ش� �������̽��� �����ϵ��� �ؼ� �������� ���� �ذ�

=> �ʵ忡 ������ �����ϴ� ���
*/

@Slf4j
public class ContextV1 {

	private Strategy strategy;
	
	public ContextV1(Strategy strategy) {
		this.strategy = strategy;
	}
	
	public void execute() {
		long startTime = System.currentTimeMillis();
		//����Ͻ� ���� ����
		strategy.call();//����
		//����Ͻ� ���� ����
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime-startTime;
		log.info("resultTime={}",resultTime);
	}
}
