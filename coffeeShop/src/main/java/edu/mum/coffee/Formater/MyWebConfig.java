package edu.mum.coffee.Formater;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {

    @Override
  public void addFormatters (FormatterRegistry registry) {
  AddressFormatter addressFormatter = new AddressFormatter();
  registry.addFormatter(addressFormatter);
  }
}