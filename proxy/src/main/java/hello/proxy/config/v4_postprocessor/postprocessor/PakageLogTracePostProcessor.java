package hello.proxy.config.v4_postprocessor.postprocessor;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PakageLogTracePostProcessor implements BeanPostProcessor{

	private final String basePackage;
	private final Advisor advisor;
	
	public PakageLogTracePostProcessor(String basePackage, Advisor advisor) {
		this.basePackage = basePackage;
		this.advisor = advisor;
	}
	
	
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.info("param beanName={},bean={}",beanName,bean.getClass());
		
		//���Ͻ� ���� ��� ���� üũ
		//���Ͻ� ���� ����� �ƴϸ� ������ �״�� ����
		String packageName = bean.getClass().getPackageName();
		
		if(!packageName.startsWith(basePackage)) {
			return bean;
		}
		
		//����� ����̸� ���Ͻø� ���� ��ȯ
		ProxyFactory proxyFactory =  new ProxyFactory(bean);
		proxyFactory.addAdvisor(advisor);
		
		Object proxy = proxyFactory.getProxy();
		log.info("create proxy target = {} proxy = {}",bean.getClass(),proxy.getClass());
		
		return proxy;
	}
}