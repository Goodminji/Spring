package hello.proxy.pureproxy.concreateProxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreateLogic {
	public String operation() {
		log.info("ConcreateLogic ½ÇÇà");
		return "data";
	}
}
