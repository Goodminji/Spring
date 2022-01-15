package hello.proxy.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreateService;
import lombok.extern.slf4j.Slf4j;

/*
JDK���� ���Ͻô� �������̽� �������� ���Ͻø� ����.
Cglib�� ��ü Ŭ������ ��ӹ޾Ƽ� ���Ͻø� ����.

cglib�� Ŭ���� �⺻ ���Ͻô� ����� ����ϱ� ������ ������� �߻�
 1. �θ�Ŭ������ �����ڸ� üũ�ؾ��Ѵ�(cglib�� �ڽ�Ŭ������ �������� �����ϱ� ������ �⺻������ �ʿ�)
 2. Ŭ������ final ������ ��� �Ұ���.
 3. �޼ҵ忡 final ������ �������̵� �Ұ��� 
 
*/
@Slf4j
public class CglibTest {

	@Test
	void cglib() {
		// ConcreateService �������̽��� ���� ��ü Ŭ����
		ConcreateService target = new ConcreateService();
		
		//cglib �� Enhancer �� ����ؼ� ���Ͻø� ����
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(ConcreateService.class);//��ü Ŭ������ ��� �޾Ƽ� ���
		enhancer.setCallback(new TimeMethodInterceptor(target));//���Ͻÿ� ������ ���� ���� �Ҵ�
		ConcreateService proxy  = (ConcreateService)enhancer.create();//���Ͻ� ����
	
		log.info("targetClass={}",target.getClass());
		log.info("proxyClass={}",proxy.getClass());
		
		proxy.call(); // ���Ͻ� ����
	}
}
