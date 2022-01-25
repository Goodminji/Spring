package hello.proxy.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;

/*
 스프링 AOP 적용할때 프록시는 하나만 만들고 하나의 프록시에 여러 어드바이저를 적용
 하나의 target에 여러 AOP가 동시에 적용되어도 스프링의 AOP는 target마다 하나의 프록시만 생성
*/
public class MultiAdvisorTest {

	@Test
	@DisplayName("여러 프록시")
	void multiAdvisorTest1() {
		//client -> proxy2(advisor2) -> proxy1(advisor1) -> target
		//프록시1 생성
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory1 = new ProxyFactory(target);
		//DefaultPointcutAdvisor 를 통해 하나의 포인트컷과 하나의 advice를 넣어주면 된다.
		// 어드바이저는 하나의 포인트컷과 하나의 advice로 구성이 된다.
		//프록시 팩토리에 적용할 어드바이저를 지정한다. 어드바이저는 내부에 포인트 컷과 advice를 모두 가지고 있따
		// 따라서 어디에 부가기능을 적용을 해야하는지 어드바이스 하나로 알 수 있다.프록시 팩토리 사용할때 어드바이저 필수
		DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE,new Advice1());
		proxyFactory1.addAdvisor(advisor1);
		ServiceInterface proxy1 = (ServiceInterface)proxyFactory1.getProxy();
		
		//프록시2 생성,tartget->proxy1
		ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
		//DefaultPointcutAdvisor 를 통해 하나의 포인트컷과 하나의 advice를 넣어주면 된다.
		// 어드바이저는 하나의 포인트컷과 하나의 advice로 구성이 된다.
		//프록시 팩토리에 적용할 어드바이저를 지정한다. 어드바이저는 내부에 포인트 컷과 advice를 모두 가지고 있따
		// 따라서 어디에 부가기능을 적용을 해야하는지 어드바이스 하나로 알 수 있다.프록시 팩토리 사용할때 어드바이저 필수
		DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE,new Advice2());
		proxyFactory2.addAdvisor(advisor2);
		ServiceInterface proxy2 = (ServiceInterface)proxyFactory2.getProxy();
		
		//실행
		proxy2.save();
	}
	
	@Test
	@DisplayName("하나의 프록시, 여러 어드바이저")
	void multiAdvisorTest2() {
		//client -> proxy -> advisor2  ->advisor1 -> target
		DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE,new Advice1());
		DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE,new Advice2());
		
		//프록시1 생성
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory1 = new ProxyFactory(target);
		//DefaultPointcutAdvisor 를 통해 하나의 포인트컷과 하나의 advice를 넣어주면 된다.
		// 어드바이저는 하나의 포인트컷과 하나의 advice로 구성이 된다.
		//프록시 팩토리에 적용할 어드바이저를 지정한다. 어드바이저는 내부에 포인트 컷과 advice를 모두 가지고 있따
		// 따라서 어디에 부가기능을 적용을 해야하는지 어드바이스 하나로 알 수 있다.프록시 팩토리 사용할때 어드바이저 필수
		proxyFactory1.addAdvisor(advisor2); //여러 어드바이저 가능,단 순서대로 입력
		proxyFactory1.addAdvisor(advisor1);
		ServiceInterface proxy1 = (ServiceInterface)proxyFactory1.getProxy();
		
		//실행
		proxy1.save();
	}
	
	@Slf4j
	static class Advice1 implements MethodInterceptor{

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			log.info("advice1 호출");
			return invocation.proceed();
		}
		
	}
	@Slf4j
	static class Advice2 implements MethodInterceptor{

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			log.info("advice2 호출");
			return invocation.proceed();
		}
		
	}
}
