package csed.database.orderprocessingbackend;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AccountInterceptor())
                .addPathPatterns("/books/manager/**");
        registry.addInterceptor(new AccountInterceptor())
                .addPathPatterns("/Publisher/manager/**");
    }


}