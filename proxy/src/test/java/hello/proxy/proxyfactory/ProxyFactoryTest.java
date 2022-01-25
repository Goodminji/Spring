package hello.proxy.proxyfactory;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreateService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxyFactoryTest {

		@Test
		@DisplayName("인터페이스가 있으면 JDK 동적 프록시 사용")
		void interfaceProxy() {
			ServiceInterface target =  new ServiceImpl();
			
			// 인터페이스가 없다면 cglib프록시를 사용, 프록시 팩토리를 생성할때 호출 대상을 같이 넘겨줌
			ProxyFactory proxyFactory = new ProxyFactory(target);
			
			//프록시가 제공하는 부가기능 로직을 ADVICE 라고 한다.
			proxyFactory.addAdvice(new TimeAdvice());//프록시가 부가기능 로직 사용할 advice 추가
			
			ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
			
			log.info("targetClass={}",target.getClass());
			log.info("proxyClass={}",proxy.getClass());
			
			proxy.save();//프록시 실행
			
			assertThat(AopUtils.isAopProxy(proxy)).isTrue();//프록시 팩토리를 사용 할때만 사용 가능
			assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();//프록시 팩토리를 사용 할때만 사용 가능
			assertThat(AopUtils.isCglibProxy(proxy)).isFalse();//프록시 팩토리를 사용 할때만 사용 가능
			
		}
		
		@Test
		@DisplayName("구체클래스만 있으면 CGLIB 사용")
		void concreteProxy() {
			ConcreateService target =  new ConcreateService();
			
			// 인터페이스가 없다면 cglib프록시를 사용, 프록시 팩토리를 생성할때 호출 대상을 같이 넘겨줌
			ProxyFactory proxyFactory = new ProxyFactory(target);
			
			//프록시가 제공하는 부가기능 로직을 ADVICE 라고 한다.
			proxyFactory.addAdvice(new TimeAdvice());//프록시가 부가기능 로직 사용할 advice 추가
			
			ConcreateService proxy = (ConcreateService)proxyFactory.getProxy();
			
			log.info("targetClass={}",target.getClass());
			log.info("proxyClass={}",proxy.getClass());
			
			proxy.call();//프록시 실행
			
			assertThat(AopUtils.isAopProxy(proxy)).isTrue();//프록시 팩토리를 사용 할때만 사용 가능
			assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();//프록시 팩토리를 사용 할때만 사용 가능
			assertThat(AopUtils.isCglibProxy(proxy)).isTrue();//프록시 팩토리를 사용 할때만 사용 가능
		}
		
		@Test
		@DisplayName("proxyTargetClass 옵션을 사용하면 인터페이스가 있어도 CGLIB 사용하고, 클래스 기반 프록시 사용")
		void proxyTargetClass() {
			ServiceInterface target =  new ServiceImpl();
			
			ProxyFactory proxyFactory = new ProxyFactory(target);
			proxyFactory.setProxyTargetClass(true);//항상 CGLIB기반으로 만들어짐
			//프록시가 제공하는 부가기능 로직을 ADVICE 라고 한다.
			proxyFactory.addAdvice(new TimeAdvice());//프록시가 부가기능 로직 사용할 advice 추가
			
			ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
			
			log.info("targetClass={}",target.getClass());
			log.info("proxyClass={}",proxy.getClass());
			
			proxy.save();//프록시 실행
			
			assertThat(AopUtils.isAopProxy(proxy)).isTrue();//프록시 팩토리를 사용 할때만 사용 가능
			assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();//프록시 팩토리를 사용 할때만 사용 가능
			assertThat(AopUtils.isCglibProxy(proxy)).isTrue();//프록시 팩토리를 사용 할때만 사용 가능
			
		}
}
