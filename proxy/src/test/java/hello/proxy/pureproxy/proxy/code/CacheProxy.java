package hello.proxy.pureproxy.proxy.code;

import lombok.extern.slf4j.Slf4j;

//프록시 생성
@Slf4j
public class CacheProxy implements Subject{
	
	//실제 객체
	private Subject target;
	private String cacheValue;

	public CacheProxy(Subject target) {
		this.target = target;
	}
	
	@Override
	public String operation() {
	
		log.info("프록시호출");
		
		if(cacheValue == null) {
			cacheValue = target.operation();
		}
		return cacheValue;
	}

}
