package hello.proxy.pureproxy.concreateProxy;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.concreateProxy.code.ConcreateClient;
import hello.proxy.pureproxy.concreateProxy.code.ConcreateLogic;
import hello.proxy.pureproxy.concreateProxy.code.TimeProxy;

//인터페이스 없이도 클래스 상속관계로 프록시 도입 가능
public class ConcreateProxyTest {

	@Test
	void noProxy() {
		ConcreateLogic concreateLogic = new ConcreateLogic();
		ConcreateClient concreateClient = new ConcreateClient(concreateLogic);
		
		concreateClient.execute();
	}
	
	@Test
	//clinet -> proxy -> ConcreateLogic(부모) = timeProxy (자식,다형성때문에)
	void addProxy() {
		ConcreateLogic concreateLogic = new ConcreateLogic();
		//프록시 생성
		TimeProxy timeProxy = new TimeProxy(concreateLogic);
		ConcreateClient concreateClient = new ConcreateClient(timeProxy);
		
		concreateClient.execute();
	}
}
