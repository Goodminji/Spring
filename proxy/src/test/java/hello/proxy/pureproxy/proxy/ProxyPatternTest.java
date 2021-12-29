package hello.proxy.pureproxy.proxy;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.proxy.code.CacheProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;

public class ProxyPatternTest {
	
	@Test
	void noProxyTest() {
		RealSubject realSubject = new RealSubject();
		ProxyPatternClient client = new ProxyPatternClient(realSubject);
		client.execute();
		client.execute();
		client.execute();
	}

	@Test
	void cacheProxyTest() {
		/*
		프록시 패턴 - RealSubject 와 클라이언트 코드를 변경하지 않고 프록시를 도입해서 접근제어를 함.
		클라이언트 코드 변경 없이 자유롭게 프록시 추가/삭제 가능하며 실제 클라이언트 입장에서는 프록시 객체가 주입되었는지 실제 객체가 주입되었는지 알 수 없다.
		*/
		RealSubject realSubject = new RealSubject();
		CacheProxy cacheProxy = new CacheProxy(realSubject);
		ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
		
		client.execute();//프록시를 호출
		client.execute();
		client.execute();
	}
}
