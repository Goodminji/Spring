package hello.proxy.cglib;

import org.junit.jupiter.api.Test;
import org.springframework.cglib.proxy.Enhancer;

import hello.proxy.cglib.code.TimeMethodInterceptor;
import hello.proxy.common.service.ConcreateService;
import lombok.extern.slf4j.Slf4j;

/*
JDK동적 프록시는 인터페이스 구현에서 프록시를 생성.
Cglib은 구체 클래스를 상속받아서 프록시를 생성.

cglib는 클래스 기본 프록시는 상속을 사용하기 때문에 제약사항 발생
 1. 부모클래스의 생성자를 체크해야한다(cglib는 자식클래스를 동적으로 생성하기 때문에 기본생성자 필요)
 2. 클래스에 final 있으면 상속 불가능.
 3. 메소드에 final 있으면 오버라이딩 불가능 
 
*/
@Slf4j
public class CglibTest {

	@Test
	void cglib() {
		// ConcreateService 인터페이스가 없는 구체 클래스
		ConcreateService target = new ConcreateService();
		
		//cglib 는 Enhancer 를 사용해서 프록시를 생성
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(ConcreateService.class);//구체 클래스를 상속 받아서 사용
		enhancer.setCallback(new TimeMethodInterceptor(target));//프록시에 적용할 실행 로직 할당
		ConcreateService proxy  = (ConcreateService)enhancer.create();//프록시 생성
	
		log.info("targetClass={}",target.getClass());
		log.info("proxyClass={}",proxy.getClass());
		
		proxy.call(); // 프록시 실행
	}
}
