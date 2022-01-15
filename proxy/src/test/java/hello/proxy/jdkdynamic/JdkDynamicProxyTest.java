package hello.proxy.jdkdynamic;

import java.lang.reflect.Proxy;

import org.junit.jupiter.api.Test;

import hello.proxy.jdkdynamic.code.AImpl;
import hello.proxy.jdkdynamic.code.AInterface;
import hello.proxy.jdkdynamic.code.BImpl;
import hello.proxy.jdkdynamic.code.BInterface;
import hello.proxy.jdkdynamic.code.TimeInvovationHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdkDynamicProxyTest {

	@Test
	void dynamicA() {
		AInterface target = new AImpl();
		
		TimeInvovationHandler handler = new TimeInvovationHandler(target);
		
		//���Ͻ� �������� ����
		AInterface proxy = (AInterface)Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[] {AInterface.class}, handler);
		
		proxy.call();
		
		log.info("targetClass={}",target.getClass());
		log.info("proxyClass={}",proxy.getClass());
	}
	
	@Test
	void dynamicB() {
		BInterface target = new BImpl();
		
		TimeInvovationHandler handler = new TimeInvovationHandler(target);
		
		//���Ͻ� �������� ����
		BInterface proxy = (BInterface)Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[] {BInterface.class}, handler);
		
		proxy.call();
		
		log.info("targetClass={}",target.getClass());
		log.info("proxyClass={}",proxy.getClass());
	}
}
