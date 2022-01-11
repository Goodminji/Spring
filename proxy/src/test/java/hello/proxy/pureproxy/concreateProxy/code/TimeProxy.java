package hello.proxy.pureproxy.concreateProxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
//�ڽ�Ŭ����(������)
public class TimeProxy extends ConcreateLogic{

	private ConcreateLogic concreateLogic;
	
	public TimeProxy(ConcreateLogic concreateLogic) {
		this.concreateLogic = concreateLogic;
	}
	
	//���������� �θ� �������̵�� ���
	@Override
	public String operation() {
		log.info("TimeProxy ����");
		long startTime = System.currentTimeMillis();
		
		String result = concreateLogic.operation();
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("TimeProxy ���� resultTime={}ms",resultTime);
		
		return result;
	}
}
