package com.yugandhar.jms;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.JndiDestinationResolver;

@Configuration
@EnableJms
public class YugJMSAppConfig {
	public static final String QUEUE_YUG_DEFAULT_SYNC_REQUEST = "YUG.DEFAULT.SYNC.REQUEST";
	public static final String QUEUE_YUG_DEFAULT_ASYNC_REQUEST = "YUG.DEFAULT.ASYNC.REQUEST";
	public static final String QUEUE_YUG_DEFAULT_RESPONSE = "YUG.DEFAULT.RESPONSE";

	@Resource(mappedName = "java:jboss/com/yugandhar/DefaultPooledConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "java:jboss/com/yugandhar/default/requestQueue")
	private Destination yugDefaultRequestQueueDestination;

	@Resource(mappedName = "java:jboss/com/yugandhar/default/responseQueue")
	private Destination yugDefaultResponseQueueDestination;

	@Bean
	public DefaultJmsListenerContainerFactory yugJNDIDestJmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		JndiDestinationResolver theJndiDestinationResolver = new JndiDestinationResolver();
		factory.setConnectionFactory(connectionFactory);
		factory.setDestinationResolver(theJndiDestinationResolver);
		factory.setConcurrency("1");
		return factory;
	}
	
	
	@Bean
	public DefaultJmsListenerContainerFactory yugDefaultDestJmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrency("1");
		return factory;
	}

}

/*
 * @Bean public MessageListenerContainer defaultRequestQueueListenerContainer()
 * { DefaultMessageListenerContainer container = new
 * DefaultMessageListenerContainer();
 * container.setConnectionFactory(connectionFactory);
 * container.setDestination(yugDefaultRequestQueueDestination);
 * container.setMessageListener(new YugDefaultRequestQueueListener());
 * container.setConcurrency("10"); return container; }
 */
// reference code for bean connection factory and multiple listeners
/*
 * @Bean public ConnectionFactory connectionFactory() { ConnectionFactory
 * connectionFactory = new ActiveMQConnectionFactory("vm://localhost"); return
 * connectionFactory; }
 */

// @Bean
// public MessageListenerContainer asyncQueueListenerContainer() {
// DefaultMessageListenerContainer container = new
// DefaultMessageListenerContainer();
// container.setConnectionFactory(connectionFactory);
// container.setDestinationName(QUEUE_YUG_DEFAULT_ASYNC_REQUEST);
// container.setMessageListener(new YugAsyncQueueListener());
// return container;
// }