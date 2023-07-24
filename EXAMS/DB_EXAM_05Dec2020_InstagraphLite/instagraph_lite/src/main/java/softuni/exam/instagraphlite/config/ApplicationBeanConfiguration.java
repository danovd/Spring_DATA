package softuni.exam.instagraphlite.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import softuni.exam.instagraphlite.models.entity.Picture;
import softuni.exam.instagraphlite.repository.PictureRepository;

import java.util.Optional;

@Configuration
public class ApplicationBeanConfiguration {


    private final PictureRepository pictureRepository;
    public ApplicationBeanConfiguration(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }



    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(new Converter<String, Picture>() {
            @Override
            public Picture convert(MappingContext<String, Picture> mappingContext) {
                String filePath = mappingContext.getSource();

                Optional<Picture> picture = pictureRepository.findByPath(filePath);
                return picture.orElse(null);
            }
        });

        return modelMapper;
    }

    @Bean
    Gson gson() {
        return new GsonBuilder()
                //   .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }
}
