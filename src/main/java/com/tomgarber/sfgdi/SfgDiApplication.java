package com.tomgarber.sfgdi;

import com.tomgarber.sfgdi.config.Sf5Configuration;
import com.tomgarber.sfgdi.config.Sf5ConstructorConfig;
import com.tomgarber.sfgdi.controllers.*;
import com.tomgarber.sfgdi.datasource.FakeDataSource;
import com.tomgarber.sfgdi.services.PrototypeBean;
import com.tomgarber.sfgdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

//@ComponentScan(basePackages = {"com.tomgarber.sfgdi", "com.tomgarber.pets"})
@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());

		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		MyController myController = (MyController) ctx.getBean("myController");

		System.out.println("------- Primary Bean");
		System.out.println(myController.sayHello());

		System.out.println("------ Property");
		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("--------- Setter");
		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("-------- Constructor" );
		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());


		System.out.println("--- Bean Scopes ---");
		SingletonBean singletonBean1 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean1.getMyScope());
		SingletonBean singletonBean2 = ctx.getBean(SingletonBean.class);
		System.out.println(singletonBean2.getMyScope());

		PrototypeBean prototypeBean1 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean1.getMyScope());
		PrototypeBean prototypeBean2 = ctx.getBean(PrototypeBean.class);
		System.out.println(prototypeBean2.getMyScope());

		System.out.println("--- Fake Data Source ---");
		FakeDataSource fakeDataSource = ctx.getBean(FakeDataSource.class);
		System.out.println(fakeDataSource.getUsername());
		System.out.println(fakeDataSource.getPassword());
		System.out.println(fakeDataSource.getJdbcurl());

		System.out.println("----- Config Props Bean --------");
		Sf5Configuration sf5Configuration = ctx.getBean(Sf5Configuration.class);
		System.out.println(sf5Configuration.getUsername());
		System.out.println(sf5Configuration.getPassword());
		System.out.println(sf5Configuration.getJdbcurl());

		System.out.println("--- Constructor Binding Config ---");
		Sf5ConstructorConfig sf5ConstructorConfig = ctx.getBean(Sf5ConstructorConfig.class);
		System.out.println(sf5ConstructorConfig.getUsername());
		System.out.println(sf5ConstructorConfig.getPassword());
		System.out.println(sf5ConstructorConfig.getJdbcurl());
	}

}
