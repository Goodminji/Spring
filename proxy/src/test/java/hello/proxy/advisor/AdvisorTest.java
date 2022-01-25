package hello.proxy.advisor;

import java.lang.reflect.Method;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AdvisorTest {
	
	@Test
	void advisorTest1() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		//DefaultPointcutAdvisor 를 통해 하나의 포인트컷과 하나의 advice를 넣어주면 된다.
		// 어드바이저는 하나의 포인트컷과 하나의 advice로 구성이 된다.
		//프록시 팩토리에 적용할 어드바이저를 지정한다. 어드바이저는 내부에 포인트 컷과 advice를 모두 가지고 있따
		// 따라서 어디에 부가기능을 적용을 해야하는지 어드바이스 하나로 알 수 있다.프록시 팩토리 사용할때 어드바이저 필수
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE,new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
		
		proxy.find();
		proxy.save();
		
	}
	@Test
	@DisplayName("직접 만드는 포인트컷")
	void advisorTest2() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		//DefaultPointcutAdvisor 를 통해 하나의 포인트컷과 하나의 advice를 넣어주면 된다.
		// 어드바이저는 하나의 포인트컷과 하나의 advice로 구성이 된다.
		//프록시 팩토리에 적용할 어드바이저를 지정한다. 어드바이저는 내부에 포인트 컷과 advice를 모두 가지고 있따
		// 따라서 어디에 부가기능을 적용을 해야하는지 어드바이스 하나로 알 수 있다.프록시 팩토리 사용할때 어드바이저 필수
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(),new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
		
		proxy.find();
		proxy.save();
		
	}
	@Test
	@DisplayName("스프링이 제공하는 포인트컷")
	void advisorTest3() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		
		NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
		pointCut.setMappedName("save");
		//DefaultPointcutAdvisor 를 통해 하나의 포인트컷과 하나의 advice를 넣어주면 된다.
		// 어드바이저는 하나의 포인트컷과 하나의 advice로 구성이 된다.
		//프록시 팩토리에 적용할 어드바이저를 지정한다. 어드바이저는 내부에 포인트 컷과 advice를 모두 가지고 있따
		// 따라서 어디에 부가기능을 적용을 해야하는지 어드바이스 하나로 알 수 있다.프록시 팩토리 사용할때 어드바이저 필수
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointCut,new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
		
		proxy.find();
		proxy.save();
		
	}
	static class MyPointCut implements Pointcut{

		@Override
		public ClassFilter getClassFilter() {
			return ClassFilter.TRUE;
		}

		@Override
		public MethodMatcher getMethodMatcher() {
			return new MyMethodMatcher();
		}
		
	}
	
	static class MyMethodMatcher implements MethodMatcher{

		private String matchName = "save";
		
		@Override
		public boolean matches(Method method, Class<?> targetClass) {
			//메소드 이름이 save 일때만 true 반환 하도록
			boolean result = method.getName().equals(matchName);
			log.info("포인트 컷 호출 method={}, targetClass={}",method.getName(),targetClass);
			log.info("포인트 컷 결과 result={}",result);
			return result;
		}

		@Override
		public boolean isRuntime() {
			return false;
		}

		@Override
		public boolean matches(Method method, Class<?> targetClass, Object... args) {
			return false;
		}
		
	}

}
