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
		@DisplayName("�������̽��� ������ JDK ���� ���Ͻ� ���")
		void interfaceProxy() {
			ServiceInterface target =  new ServiceImpl();
			
			// �������̽��� ���ٸ� cglib���Ͻø� ���, ���Ͻ� ���丮�� �����Ҷ� ȣ�� ����� ���� �Ѱ���
			ProxyFactory proxyFactory = new ProxyFactory(target);
			
			//���Ͻð� �����ϴ� �ΰ���� ������ ADVICE ��� �Ѵ�.
			proxyFactory.addAdvice(new TimeAdvice());//���Ͻð� �ΰ���� ���� ����� advice �߰�
			
			ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
			
			log.info("targetClass={}",target.getClass());
			log.info("proxyClass={}",proxy.getClass());
			
			proxy.save();//���Ͻ� ����
			
			assertThat(AopUtils.isAopProxy(proxy)).isTrue();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
			assertThat(AopUtils.isJdkDynamicProxy(proxy)).isTrue();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
			assertThat(AopUtils.isCglibProxy(proxy)).isFalse();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
			
		}
		
		@Test
		@DisplayName("��üŬ������ ������ CGLIB ���")
		void concreteProxy() {
			ConcreateService target =  new ConcreateService();
			
			// �������̽��� ���ٸ� cglib���Ͻø� ���, ���Ͻ� ���丮�� �����Ҷ� ȣ�� ����� ���� �Ѱ���
			ProxyFactory proxyFactory = new ProxyFactory(target);
			
			//���Ͻð� �����ϴ� �ΰ���� ������ ADVICE ��� �Ѵ�.
			proxyFactory.addAdvice(new TimeAdvice());//���Ͻð� �ΰ���� ���� ����� advice �߰�
			
			ConcreateService proxy = (ConcreateService)proxyFactory.getProxy();
			
			log.info("targetClass={}",target.getClass());
			log.info("proxyClass={}",proxy.getClass());
			
			proxy.call();//���Ͻ� ����
			
			assertThat(AopUtils.isAopProxy(proxy)).isTrue();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
			assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
			assertThat(AopUtils.isCglibProxy(proxy)).isTrue();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
		}
		
		@Test
		@DisplayName("proxyTargetClass �ɼ��� ����ϸ� �������̽��� �־ CGLIB ����ϰ�, Ŭ���� ��� ���Ͻ� ���")
		void proxyTargetClass() {
			ServiceInterface target =  new ServiceImpl();
			
			ProxyFactory proxyFactory = new ProxyFactory(target);
			proxyFactory.setProxyTargetClass(true);//�׻� CGLIB������� �������
			//���Ͻð� �����ϴ� �ΰ���� ������ ADVICE ��� �Ѵ�.
			proxyFactory.addAdvice(new TimeAdvice());//���Ͻð� �ΰ���� ���� ����� advice �߰�
			
			ServiceInterface proxy = (ServiceInterface)proxyFactory.getProxy();
			
			log.info("targetClass={}",target.getClass());
			log.info("proxyClass={}",proxy.getClass());
			
			proxy.save();//���Ͻ� ����
			
			assertThat(AopUtils.isAopProxy(proxy)).isTrue();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
			assertThat(AopUtils.isJdkDynamicProxy(proxy)).isFalse();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
			assertThat(AopUtils.isCglibProxy(proxy)).isTrue();//���Ͻ� ���丮�� ��� �Ҷ��� ��� ����
			
		}
}
