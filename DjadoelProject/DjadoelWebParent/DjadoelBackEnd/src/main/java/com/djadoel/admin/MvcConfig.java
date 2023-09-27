package com.djadoel.admin;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 
 * Created by: Ridho Suhaebi Arrowi
 * IDE: Spring Tool Suite 4
 * Information: ridhosuhaebi01@gmail.com
*/

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		String directoryName = "user-photos";

		Path userPhotosDirectory = Paths.get(directoryName);

		String userPhotosPath = userPhotosDirectory.toFile().getAbsolutePath();

		registry.addResourceHandler("/" + directoryName + "/**").addResourceLocations("file:/" + userPhotosPath + "/");
	}

}
