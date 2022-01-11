package hello.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponet;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;

/*
프록시패턴 vs 데코레이턴 패턴
: 의도에 따라서  구분
 프록시 패턴의 의도는 다른 개체에 대한 접근을 제어하기 위한 대리자를 제공
 데코레이턴 패턴의 의도는 객체에 추가 책임(기능)을 동적으로 추가하고 기능 확장을 위한 유연한 대안 제공
*/
@Slf4j
public class DecoratorPatternTest {
	
	@Test
	void noDecorator() {
		Component realComponent = new RealComponet();
		DecoratorPatternClient client = new DecoratorPatternClient(realComponent);
		
		client.execute();
	}
	
	@Test
	void decorator1() {
		Component realComponent = new RealComponet();
		Component messageDecorator = new MessageDecorator(realComponent);
		
		DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);
		
		client.execute();
	}
	
	//데코레이터 패턴
	//client -> timeDecorator -> messageDecorator -> realComponent
	@Test
	void decorator2() {
		Component realComponent = new RealComponet();
		Component messageDecorator = new MessageDecorator(realComponent);
		Component timeDeComponent = new TimeDecorator(messageDecorator);
		
		DecoratorPatternClient client = new DecoratorPatternClient(timeDeComponent);
		
		client.execute();
	}
}
