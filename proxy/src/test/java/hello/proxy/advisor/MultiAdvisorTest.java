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
 ������ AOP �����Ҷ� ���Ͻô� �ϳ��� ����� �ϳ��� ���Ͻÿ� ���� ���������� ����
 �ϳ��� target�� ���� AOP�� ���ÿ� ����Ǿ �������� AOP�� target���� �ϳ��� ���Ͻø� ����
*/
public class MultiAdvisorTest {

	@Test
	@DisplayName("���� ���Ͻ�")
	void multiAdvisorTest1() {
		//client -> proxy2(advisor2) -> proxy1(advisor1) -> target
		//���Ͻ�1 ����
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory1 = new ProxyFactory(target);
		//DefaultPointcutAdvisor �� ���� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� �־��ָ� �ȴ�.
		// ���������� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� ������ �ȴ�.
		//���Ͻ� ���丮�� ������ ���������� �����Ѵ�. ���������� ���ο� ����Ʈ �ư� advice�� ��� ������ �ֵ�
		// ���� ��� �ΰ������ ������ �ؾ��ϴ��� �����̽� �ϳ��� �� �� �ִ�.���Ͻ� ���丮 ����Ҷ� �������� �ʼ�
		DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE,new Advice1());
		proxyFactory1.addAdvisor(advisor1);
		ServiceInterface proxy1 = (ServiceInterface)proxyFactory1.getProxy();
		
		//���Ͻ�2 ����,tartget->proxy1
		ProxyFactory proxyFactory2 = new ProxyFactory(proxy1);
		//DefaultPointcutAdvisor �� ���� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� �־��ָ� �ȴ�.
		// ���������� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� ������ �ȴ�.
		//���Ͻ� ���丮�� ������ ���������� �����Ѵ�. ���������� ���ο� ����Ʈ �ư� advice�� ��� ������ �ֵ�
		// ���� ��� �ΰ������ ������ �ؾ��ϴ��� �����̽� �ϳ��� �� �� �ִ�.���Ͻ� ���丮 ����Ҷ� �������� �ʼ�
		DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE,new Advice2());
		proxyFactory2.addAdvisor(advisor2);
		ServiceInterface proxy2 = (ServiceInterface)proxyFactory2.getProxy();
		
		//����
		proxy2.save();
	}
	
	@Test
	@DisplayName("�ϳ��� ���Ͻ�, ���� ��������")
	void multiAdvisorTest2() {
		//client -> proxy -> advisor2  ->advisor1 -> target
		DefaultPointcutAdvisor advisor1 = new DefaultPointcutAdvisor(Pointcut.TRUE,new Advice1());
		DefaultPointcutAdvisor advisor2 = new DefaultPointcutAdvisor(Pointcut.TRUE,new Advice2());
		
		//���Ͻ�1 ����
		ServiceInterface target = new ServiceImpl();
		ProxyFactory proxyFactory1 = new ProxyFactory(target);
		//DefaultPointcutAdvisor �� ���� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� �־��ָ� �ȴ�.
		// ���������� �ϳ��� ����Ʈ�ư� �ϳ��� advice�� ������ �ȴ�.
		//���Ͻ� ���丮�� ������ ���������� �����Ѵ�. ���������� ���ο� ����Ʈ �ư� advice�� ��� ������ �ֵ�
		// ���� ��� �ΰ������ ������ �ؾ��ϴ��� �����̽� �ϳ��� �� �� �ִ�.���Ͻ� ���丮 ����Ҷ� �������� �ʼ�
		proxyFactory1.addAdvisor(advisor2); //���� �������� ����,�� ������� �Է�
		proxyFactory1.addAdvisor(advisor1);
		ServiceInterface proxy1 = (ServiceInterface)proxyFactory1.getProxy();
		
		//����
		proxy1.save();
	}
	
	@Slf4j
	static class Advice1 implements MethodInterceptor{

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			log.info("advice1 ȣ��");
			return invocation.proceed();
		}
		
	}
	@Slf4j
	static class Advice2 implements MethodInterceptor{

		@Override
		public Object invoke(MethodInvocation invocation) throws Throwable {
			log.info("advice2 ȣ��");
			return invocation.proceed();
		}
		
	}
}
