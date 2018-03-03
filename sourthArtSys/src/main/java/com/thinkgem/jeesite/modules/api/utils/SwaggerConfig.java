package com.thinkgem.jeesite.modules.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

/**
 * 小程序接口管理框架
 * @description API 管理框架swagger配置文件
 * @author LM
 */
@Configuration
@EnableSwagger
@EnableWebMvc
@ComponentScan("com.thinkgem.jeesite.modules.api")
public class SwaggerConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
		this.springSwaggerConfig = springSwaggerConfig;
	}

	/**
	 * 链式编程 来定制API样式 后续会加上分组信息
	 * 
	 * @return
	 */
	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
				.apiInfo(apiInfo()).includePatterns(".api.*")
				// .pathProvider(new GtPaths())
				//.apiVersion("0.0.1").swaggerGroup("user")
				;
	}

	/**
	 * 	参数
	 */
	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("西南艺术文化馆平台API文档",
				"小程序各类接口", 
				"http://127.0.0.1:8080/sourthArtSys",
				"", 
				"内部使用", 
				"接口文档可能随时更新，注意版本号");
		return apiInfo;
	}

	@Override
	public void configureDefaultServletHandling(
			DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
