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
		���Ͻ� ���� - RealSubject �� Ŭ���̾�Ʈ �ڵ带 �������� �ʰ� ���Ͻø� �����ؼ� ������� ��.
		Ŭ���̾�Ʈ �ڵ� ���� ���� �����Ӱ� ���Ͻ� �߰�/���� �����ϸ� ���� Ŭ���̾�Ʈ ���忡���� ���Ͻ� ��ü�� ���ԵǾ����� ���� ��ü�� ���ԵǾ����� �� �� ����.
		*/
		RealSubject realSubject = new RealSubject();
		CacheProxy cacheProxy = new CacheProxy(realSubject);
		ProxyPatternClient client = new ProxyPatternClient(cacheProxy);
		
		client.execute();//���Ͻø� ȣ��
		client.execute();
		client.execute();
	}
}
