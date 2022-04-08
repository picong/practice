package com.example.demo;

import com.example.demo.initializers.ApplicationContextInitializerDemo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

//@EnableTransactionManagement
@EnableAspectJAutoProxy
@SpringBootApplication
//@EnableColor
//@Configuration
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ColorConfiguration.class);
//		String[] beanDefinitionNames = ctx.getBeanDefinitionNames();
//		Stream.of(beanDefinitionNames).forEach(System.out::println);
		SpringApplication springApplication = new SpringApplication(DemoApplication.class);
//		springApplication.setWebApplicationType(WebApplicationType.SERVLET); // 强制使用WebMvc环境
//		springApplication.setBannerMode(Banner.Mode.OFF); // 不打印Banner
		springApplication.addInitializers(new ApplicationContextInitializerDemo());
		springApplication.run(args);

	}

}
