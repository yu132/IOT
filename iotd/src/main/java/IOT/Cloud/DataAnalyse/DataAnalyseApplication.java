package IOT.Cloud.DataAnalyse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
/**
 * 
 * @author 周扬
 *
 */
@SpringBootApplication
@EnableJpaAuditing
public class DataAnalyseApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
        SpringApplication.run(DataAnalyseApplication.class, args);
    }
}
