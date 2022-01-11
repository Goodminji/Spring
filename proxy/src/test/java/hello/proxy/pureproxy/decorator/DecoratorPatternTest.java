package hello.proxy.pureproxy.decorator;

import org.junit.jupiter.api.Test;

import hello.proxy.pureproxy.decorator.code.Component;
import hello.proxy.pureproxy.decorator.code.DecoratorPatternClient;
import hello.proxy.pureproxy.decorator.code.MessageDecorator;
import hello.proxy.pureproxy.decorator.code.RealComponet;
import hello.proxy.pureproxy.decorator.code.TimeDecorator;
import lombok.extern.slf4j.Slf4j;

/*
���Ͻ����� vs ���ڷ����� ����
: �ǵ��� ����  ����
 ���Ͻ� ������ �ǵ��� �ٸ� ��ü�� ���� ������ �����ϱ� ���� �븮�ڸ� ����
 ���ڷ����� ������ �ǵ��� ��ü�� �߰� å��(���)�� �������� �߰��ϰ� ��� Ȯ���� ���� ������ ��� ����
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
	
	//���ڷ����� ����
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
