package hello.proxy.pureproxy.concreateProxy.code;

public class ConcreateClient {

	private ConcreateLogic contracetLogic;
	
	public ConcreateClient(ConcreateLogic contracetLogic) {
		this.contracetLogic = contracetLogic;
	}
	
	public void execute() {
		contracetLogic.operation();
	}
}
