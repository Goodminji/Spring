package hello.proxy.pureproxy.concreateProxy;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.concreateProxy.code.ConcreateClient;
import hello.proxy.pureproxy.concreateProxy.code.ConcreateLogic;
import hello.proxy.pureproxy.concreateProxy.code.TimeProxy;

//�������̽� ���̵� Ŭ���� ��Ӱ���� ���Ͻ� ���� ����
public class ConcreateProxyTest {

	@Test
	void noProxy() {
		ConcreateLogic concreateLogic = new ConcreateLogic();
		ConcreateClient concreateClient = new ConcreateClient(concreateLogic);
		
		concreateClient.execute();
	}
	
	@Test
	//clinet -> proxy -> ConcreateLogic(�θ�) = timeProxy (�ڽ�,������������)
	void addProxy() {
		ConcreateLogic concreateLogic = new ConcreateLogic();
		//���Ͻ� ����
		TimeProxy timeProxy = new TimeProxy(concreateLogic);
		ConcreateClient concreateClient = new ConcreateClient(timeProxy);
		
		concreateClient.execute();
	}
}
