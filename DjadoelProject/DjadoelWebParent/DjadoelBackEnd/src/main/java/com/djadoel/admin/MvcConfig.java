package com.djadoel.admin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		// User Configuration

		String userImagesDirectoryName = "user-photos";

		Path userPhotosDirectory = Paths.get(userImagesDirectoryName);

		String userPhotosPath = userPhotosDirectory.toFile().getAbsolutePath();

		registry.addResourceHandler("/" + userImagesDirectoryName + "/**")
				.addResourceLocations("file:/" + userPhotosPath + "/");

		// Category Configuration

		String categoryImagesDirectoryName = "../category-images";

		Path categoryImagesDirectory = Paths.get(categoryImagesDirectoryName);

		String categoryImagesPath = categoryImagesDirectory.toFile().getAbsolutePath();

		registry.addResourceHandler("/category-images/**").addResourceLocations("file:/" + categoryImagesPath + "/");
	}

	// Internationalization Configuration

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		Locale localeIndonesia = new Locale("id", "ID");
		slr.setDefaultLocale(localeIndonesia);
		return slr;
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}
}
