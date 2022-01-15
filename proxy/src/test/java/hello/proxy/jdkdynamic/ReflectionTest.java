package hello.proxy.jdkdynamic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionTest {
	@Test
	void reflection0() {
		Hello target = new Hello();
		
		//���� ���� 1 ����
		log.info("start");
		String result1 = target.callA(); // ���� ó�� �ʿ�
		log.info("result={}",result1);
		//���� ����1 ����
		
		//���� ���� 2 ����
		log.info("start");
		String result2 = target.callB();// ���� ó�� �ʿ�
		log.info("result={}",result2);
		//���� ����2 ����
		
	}
	
	@Test
	void reflection1() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//Ŭ���� ���� - Ŭ���� ��Ÿ ������ ȹ���Ѵ�. ����Ŭ������ ������ ����$���
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
		
		Hello target = new Hello();

		//callA �޼��� ����
		Method methodCallA = classHello.getMethod("callA");//�޼ҵ� ��Ÿ���� ȹ��
		Object result1 = methodCallA.invoke(target);//invoke �޼ҵ� ȣ��
		log.info("result={}",result1);
		
		//callB �޼��� ����
		Method methodCallB = classHello.getMethod("callB");
		Object result2 = methodCallA.invoke(target);
		log.info("result={}",result2);
	}
	
	/*
	���÷����� ����ϸ� Ŭ������ �޼����� ��Ÿ������ ����Ͽ� �������� �����ϰ� ���. 
	��Ÿ������ ������ ������� �����Ͻ� �߰����� ���ϰ� ��Ÿ�ӽ� �߰�.
	*/	
	@Test
	void reflection2() throws Exception{
	Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
		
		Hello target = new Hello();

		//callA �޼��� ����
		Method methodCallA = classHello.getMethod("callA");//�޼ҵ� ��Ÿ���� ȹ��
		dynamicCall(methodCallA,target);
		
		//callB �޼��� ����
		Method methodCallB = classHello.getMethod("callB");
		dynamicCall(methodCallB,target);
		
	}
	
	private void dynamicCall(Method method,Object target) throws Exception{
		log.info("start");
		Object result1 = method.invoke(target); // ���� ó�� �ʿ�
		log.info("result={}",result1);
	}
	
	@Slf4j
	static class Hello {
		public String callA() {
			log.info("callA");
			return "A";
		}
		
		public String callB() {
			log.info("callB");
			return "B";
		}
	}
}
