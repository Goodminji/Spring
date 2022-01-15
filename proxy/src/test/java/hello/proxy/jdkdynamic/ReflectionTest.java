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
		
		//공통 로직 1 시작
		log.info("start");
		String result1 = target.callA(); // 동적 처리 필요
		log.info("result={}",result1);
		//공통 로직1 종료
		
		//공통 로직 2 시작
		log.info("start");
		String result2 = target.callB();// 동적 처리 필요
		log.info("result={}",result2);
		//공통 로직2 종료
		
	}
	
	@Test
	void reflection1() throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//클래스 정보 - 클래스 메타 정보를 획득한다. 내부클래스는 구분을 위해$사용
		Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
		
		Hello target = new Hello();

		//callA 메서드 정보
		Method methodCallA = classHello.getMethod("callA");//메소드 메타정보 획득
		Object result1 = methodCallA.invoke(target);//invoke 메소드 호출
		log.info("result={}",result1);
		
		//callB 메서드 정보
		Method methodCallB = classHello.getMethod("callB");
		Object result2 = methodCallA.invoke(target);
		log.info("result={}",result2);
	}
	
	/*
	리플랙션을 사용하면 클래스와 메서드의 메타정보를 사용하여 동적으로 유연하게 사용. 
	메타정보에 오류가 있을경우 컴파일시 발견하지 못하고 런타임시 발견.
	*/	
	@Test
	void reflection2() throws Exception{
	Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");
		
		Hello target = new Hello();

		//callA 메서드 정보
		Method methodCallA = classHello.getMethod("callA");//메소드 메타정보 획득
		dynamicCall(methodCallA,target);
		
		//callB 메서드 정보
		Method methodCallB = classHello.getMethod("callB");
		dynamicCall(methodCallB,target);
		
	}
	
	private void dynamicCall(Method method,Object target) throws Exception{
		log.info("start");
		Object result1 = method.invoke(target); // 동적 처리 필요
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
