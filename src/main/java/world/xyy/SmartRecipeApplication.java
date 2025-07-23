package world.xyy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application startup class
 *
 * @author XYY
 */
@SpringBootApplication
@MapperScan("world.xyy.dao")
public class SmartRecipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartRecipeApplication.class, args);
    }

}
