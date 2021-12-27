package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/*
���� �д��̶� ������ �ʴ� �κ��� Context��� ���� �ΰ�,���ϴ� �κ��� stratege��� �������̽��� �����
�ش� �������̽��� �����ϵ��� �ؼ� �������� ���� �ذ�

=> ������ �Ķ���ͷ� ���� �޴� ���
*/

@Slf4j
public class ContextV2 {

	public void execute(Strategy strategy) {
		long startTime = System.currentTimeMillis();
		//����Ͻ� ���� ����
		strategy.call();//����
		//����Ͻ� ���� ����
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime-startTime;
		log.info("resultTime={}",resultTime);
	}
}
