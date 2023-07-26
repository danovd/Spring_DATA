package softuni.exam.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.util.ValidationUtil;


@Configuration
public class ApplicationBeanConfiguration {

	//ToDo

    @Bean
    public Gson gson() {
        return null;
    }

    @Bean
    public ValidationUtil validationUtil() {
        return null;
    }

    @Bean
    public ModelMapper modelMapper() {
        return null;
    }

}
