package crud.app;

import java.util.Locale;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages = {"crud.core", "crud.app"})
@PropertySource("classpath:datasource.properties")
@EnableTransactionManagement
public class AppConfig extends WebMvcConfigurerAdapter {
    @Value("${connection.driver}")
    String driverClass;
    @Value("${connection.url}")
    String jdbcUrl;
    @Value("${connection.username}")
    String user;
    @Value("${connection.password}")
    String pass;
    @Value("${dialect}")
    String dialect;
    @Value("${cache.resource}")
    String cacheResource;
    @Value("${cache.factory}")
    String cacheFactory;
    
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
    
	@Bean
    public SessionFactory sessionFactory() {
        LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
        builder.scanPackages("crud.core.model")
               .addProperties(getHibernateProperties());

        return builder.buildSessionFactory();
    }
    
    private Properties getHibernateProperties() {
        System.out.println("PROP " + dialect + " " + cacheResource + " " + cacheFactory);  
        Properties prop = new Properties();
        prop.put("hibernate.format_sql", "true");
        prop.put("hibernate.show_sql", "true");
       // prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        prop.put("hibernate.dialect", dialect);
        prop.put("hibernate.cache.use_second_level_cache", "true");
       // prop.put("net.sf.ehcache.configurationResourceName", "ehcache.xml");
        prop.put("net.sf.ehcache.configurationResourceName", cacheResource);
        // prop.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        prop.put("hibernate.cache.region.factory_class", cacheFactory);
        return prop;
    }

	@Bean(name = "dataSource")
	public ComboPooledDataSource dataSource() {
		try {
		    System.out.println("DATASOURCE " + driverClass + " " + jdbcUrl + " " + user + " " + pass); 
			ComboPooledDataSource ds = new ComboPooledDataSource();
			//ds.setDriverClass("org.postgresql.Driver");
			ds.setDriverClass(driverClass);
			//ds.setJdbcUrl("jdbc:postgresql://localhost:5433/hr");
			ds.setJdbcUrl(jdbcUrl);
			//ds.setUser("ecc");
			ds.setUser(user);
			ds.setPassword("ex1stgl0bal");
			//ds.setPassword(pass);
			ds.setMaxPoolSize(5);
			ds.setMaxStatements(2);
			ds.setMinPoolSize(1);
			return ds;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Bean
    public HibernateTransactionManager transactionManager() {
        return new HibernateTransactionManager(sessionFactory());
    }
		
	@Bean(name = "viewResolver")
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    
    @Bean
    public CommonsMultipartResolver multipartResolver() {
        return new CommonsMultipartResolver();
    }
    
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:message");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
    
    @Bean
    public LocaleResolver localeResolver(){
	    SessionLocaleResolver resolver = new SessionLocaleResolver();
	    resolver.setDefaultLocale(new Locale("en"));
	    return resolver;
    }
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
	    LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
	    interceptor.setParamName("language");
	    registry.addInterceptor(interceptor);
    }
	
}
