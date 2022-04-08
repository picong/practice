package com.example.demo.annatation;

import com.example.demo.config.ColorImportBeanDefinitionRegistrar;
import com.example.demo.config.ColorImportSelector;
import com.example.demo.entity.Red;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({Red.class, ColorImportSelector.class, ColorImportBeanDefinitionRegistrar.class})
public @interface EnableColor {
}
