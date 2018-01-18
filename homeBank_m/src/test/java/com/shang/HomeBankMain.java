package com.shang;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.thread.QueuedThreadPool;

public class HomeBankMain {
	
	private static Server server = new Server();
	
	public static void main(String[] args)throws Exception {
		QueuedThreadPool boundedThreadPool = new QueuedThreadPool();
		boundedThreadPool.setMaxThreads(5);
		server.setThreadPool(boundedThreadPool);

		Connector connector = new SelectChannelConnector();
		connector.setPort(8080);
		server.addConnector(connector);

		WebAppContext context = new WebAppContext("src/main/webapp", "/homeBank_m");
		context.setDefaultsDescriptor("src/test/resources/webdefault.xml");
		
//		EnvConfiguration envConfiguration = new EnvConfiguration();
//        envConfiguration.setJettyEnvXml(new File("src/test/resources/jetty/jetty-env.xml").toURI().toURL());
//		Configuration[] configurations = new Configuration[]{
//		        new org.mortbay.jetty.webapp.WebInfConfiguration(),
//		        envConfiguration,
//		        new org.mortbay.jetty.plus.webapp.Configuration(),
//		        new org.mortbay.jetty.webapp.JettyWebXmlConfiguration(),
//		        new org.mortbay.jetty.webapp.TagLibConfiguration()
//		};
//		context.setConfigurations(configurations);
		
		server.setHandler(context);

		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);

		server.start();
		String res = "\n\n\nStartUP 0000000000000000000000000000";
		System.out.println(res);
		server.join();
	}
}
