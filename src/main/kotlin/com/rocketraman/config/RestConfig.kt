package com.rocketraman.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.hateoas.UriTemplate
import org.springframework.hateoas.hal.CurieProvider
import org.springframework.hateoas.hal.DefaultCurieProvider
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import javax.inject.Inject

@Configuration
open class RestConfig {
  @Inject
  lateinit var asyncTaskExecutor: AsyncTaskExecutor

  @Bean
  open fun curieProvider(): CurieProvider {
    return DefaultCurieProvider("foo", UriTemplate("http://foo.com/rels/{#rel}"))
  }

  // this won't work, need a separate WebMvcConfig as shown below
  @Bean
  open fun webMvcConfigurer() = object: WebMvcConfigurerAdapter() {
    override fun configureAsyncSupport(configurer: AsyncSupportConfigurer) {
      configurer.setTaskExecutor(asyncTaskExecutor)
    }
  }
}

/*
class WebMvcConfig: WebMvcConfigurerAdapter() {
  @Inject
  lateinit var asyncTaskExecutor: AsyncTaskExecutor

  override fun configureAsyncSupport(configurer: AsyncSupportConfigurer) {
    configurer.setTaskExecutor(asyncTaskExecutor)
  }
}
*/