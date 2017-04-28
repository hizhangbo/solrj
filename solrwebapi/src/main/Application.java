package main;

/**
 * Created by zhangbo on 2017/4/24.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan(basePackageClasses = SpxxController.class)
//@EntityScan("com.ensat.entities")
@SpringBootApplication(exclude = SolrAutoConfiguration.class)
@ComponentScan(basePackages = {"shxh.controller", "swagger"})
public class Application extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(Application.class);
	}
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
