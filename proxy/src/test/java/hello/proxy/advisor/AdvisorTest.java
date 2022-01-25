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
		//DefaultPointcutAdvisor �� ���� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� �־��ָ� �ȴ�.
		// ���������� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� ������ �ȴ�.
		//���Ͻ� ���丮�� ������ ���������� �����Ѵ�. ���������� ���ο� ����Ʈ �ư� advice�� ��� ������ �ֵ�
		// ���� ��� �ΰ������ ������ �ؾ��ϴ��� �����̽� �ϳ��� �� �� �ִ�.���Ͻ� ���丮 ����Ҷ� �������� �ʼ�
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE,new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
		
		proxy.find();
		proxy.save();
		
	}
	@Test
	@DisplayName("���� ����� ����Ʈ��")
	void advisorTest2() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		//DefaultPointcutAdvisor �� ���� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� �־��ָ� �ȴ�.
		// ���������� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� ������ �ȴ�.
		//���Ͻ� ���丮�� ������ ���������� �����Ѵ�. ���������� ���ο� ����Ʈ �ư� advice�� ��� ������ �ֵ�
		// ���� ��� �ΰ������ ������ �ؾ��ϴ��� �����̽� �ϳ��� �� �� �ִ�.���Ͻ� ���丮 ����Ҷ� �������� �ʼ�
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointCut(),new TimeAdvice());
		proxyFactory.addAdvisor(advisor);
		
		ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
		
		proxy.find();
		proxy.save();
		
	}
	@Test
	@DisplayName("�������� �����ϴ� ����Ʈ��")
	void advisorTest3() {
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory = new ProxyFactory(target);
		
		NameMatchMethodPointcut pointCut = new NameMatchMethodPointcut();
		pointCut.setMappedName("save");
		//DefaultPointcutAdvisor �� ���� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� �־��ָ� �ȴ�.
		// ���������� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� ������ �ȴ�.
		//���Ͻ� ���丮�� ������ ���������� �����Ѵ�. ���������� ���ο� ����Ʈ �ư� advice�� ��� ������ �ֵ�
		// ���� ��� �ΰ������ ������ �ؾ��ϴ��� �����̽� �ϳ��� �� �� �ִ�.���Ͻ� ���丮 ����Ҷ� �������� �ʼ�
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
			//�޼ҵ� �̸��� save �϶��� true ��ȯ �ϵ���
			boolean result = method.getName().equals(matchName);
			log.info("����Ʈ �� ȣ�� method={}, targetClass={}",method.getName(),targetClass);
			log.info("����Ʈ �� ��� result={}",result);
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
