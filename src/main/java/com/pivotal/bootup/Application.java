package com.pivotal.bootup;

import com.pivotal.bootup.config.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.MetricFilterAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.MetricRepositoryAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.SimpleCommandLinePropertySource;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import java.io.IOException;
import java.util.Arrays;

@ComponentScan
@EnableAutoConfiguration(exclude = {MetricFilterAutoConfiguration.class, MetricRepositoryAutoConfiguration.class})
public class Application {

    private final static Logger log = LoggerFactory.getLogger(Application.class);

    @Inject
    private  Environment env;

    /**
     * Initializes jhipster.
     * <p/>
     * Spring profiles can be configured with a program arguments --spring.profiles.active=your-active-profile
     * <p/>
     */
    @PostConstruct
    public void initApplication() throws IOException {
    	
    	// log.info("initApplication***"+env.getActiveProfiles());
    	
    	
    	
    	log.warn("default profile...."+env.getActiveProfiles());
    	
    	
        if (env.getActiveProfiles().length == 0) {
            log.warn("No Spring profile configured, running with default configuration");
        } else {
            log.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }

    /**
     * Main method, used to run the application.
     *
     * To run the application with hot reload enabled, add the following arguments to your JVM:
     * "-javaagent:spring_loaded/springloaded-jhipster.jar -noverify -Dspringloaded=plugins=io.github.jhipster.loaded.instrument.JHipsterLoadtimeInstrumentationPlugin"
     */
    public static void main(String[] args) {
    	
        SpringApplication app = new SpringApplication(Application.class);
        app.setShowBanner(false);

        SimpleCommandLinePropertySource source = new SimpleCommandLinePropertySource(args);
        // Check if the selected profile has been set as argument.
        // if not the development profile will be added
        // addDefaultProfile(app, source);
        // disabling default profile it will be configured at runtime...
        
        app.run(args);
    
    }

    /**
     * Set a default profile if it has not been set
     */
    private static void addDefaultProfile(SpringApplication app, SimpleCommandLinePropertySource source) {
    	log.info("********CURRENT PROFILE "+source.containsProperty("spring.profiles.active"));
    	//log.info("Running with Spring profile(s) : {}", env.getActiveProfiles());
    	
        if (!source.containsProperty("spring.profiles.active")) {
            app.setAdditionalProfiles(Constants.SPRING_PROFILE_LOCAL);
        }
       // return Constants.SPRING_PROFILE_CLOUD;
        app.setAdditionalProfiles(Constants.SPRING_PROFILE_LOCAL);
    }
}
