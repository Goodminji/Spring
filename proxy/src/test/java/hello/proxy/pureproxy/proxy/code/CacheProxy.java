package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

//���Ͻ� ����
@Slf4j
public class CacheProxy implements Subject{
	
	//���� ��ü
	private Subject target;
	private String cacheValue;

	public CacheProxy(Subject target) {
		this.target = target;
	}
	
	@Override
	public String operation() {
	
		log.info("���Ͻ�ȣ��");
		
		if(cacheValue == null) {
			cacheValue = target.operation();
		}
		return cacheValue;
	}

}
