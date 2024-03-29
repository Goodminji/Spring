package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component{

	private Component component;
	
	public TimeDecorator(Component compoent) {
		this.component = compoent;
	}
	
	@Override
	public String operation() {
		log.info("TimeDecorator ����");
		long startTime = System.currentTimeMillis();
		
		String result = component.operation();
		
		long endTime = System.currentTimeMillis();
		long resultTime = endTime - startTime;
		log.info("TimeDecorator ���� resultTime={}ms",resultTime);
		
		return result;
	}

}
