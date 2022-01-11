package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageDecorator implements Component{

	private Component component;
	
	public MessageDecorator(Component compoent) {
		this.component = compoent;
	}
	
	@Override
	public String operation() {
		log.info("MessageDecorator ����");
		
		String operation = component.operation();
		String decoResult = "*****"+operation +"*****";
		log.info("MessageDecorator �ٹ̱� ���� �� = {}, �� = {}",operation,decoResult);
		return decoResult;
	}

}
