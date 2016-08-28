package com;

import com.Auth.GreetingAuthenticator;
import com.Auth.User;
import com.Models.*;
import com.Service.SwiggyPlayService;
import com.Util.GuiceInjector;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yammer.metrics.ConsoleReporter;
import com.yammer.metrics.JmxReporter;
import com.yammer.metrics.MetricRegistry;
import io.dropwizard.Application;
import io.dropwizard.auth.AuthFactory;
import io.dropwizard.auth.basic.BasicAuthFactory;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resources.SwiggyPlayResource;
//import resources.CustomerResource;

import java.util.concurrent.TimeUnit;

/**
 * Created by anilkumar.r on 05/08/16.
 */
public class MainApplication extends Application<AppConfiguration> {

    private static final Logger logger= LoggerFactory.getLogger(MainApplication.class);

//    private  HibernateBundle<AppConfiguration> hibernate;

    private final HibernateBundle<AppConfiguration> testApp = new HibernateBundle<AppConfiguration>(SongDetails.class, Playlist.class, PlayListIdToTagId.class , Tag.class) {

        @Override
        public DataSourceFactory getDataSourceFactory(AppConfiguration appConfiguration) {
            return appConfiguration.getExcaliburDatabase();
        }
        @Override
        protected String name() {
            return "hibernate.testApp";
        }
    };




    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);

    }



    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {

        bootstrap.addBundle(testApp);

    }


    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
//        logger.info( " messgae :: " + configuration.getMessage());

        InjectorModule injectorModule = new InjectorModule(configuration, testApp.getSessionFactory());
        Injector injector = Guice.createInjector(injectorModule);
        GuiceInjector.assignInjector(injector);

//        environment.jersey().register(injector.getInstance(CustomerResource.class));

        environment.jersey().register(injector.getInstance(SwiggyPlayResource.class));
        environment.jersey().register(AuthFactory.binder(
                new BasicAuthFactory<>(
                        new GreetingAuthenticator(configuration.getLogin(),
                                configuration.getPassword()),
                        "SECURITY REALM",
                        User.class)));

//        final MetricRegistry metrics = new MetricRegistry("Demonstration");

//        evictions = metrics.counter(MetricRegistry.name(HealthCheckDemo.class, "cache-evictions"));
//        request = metrics.timer(MetricRegistry.name(ArithmeticDemoOperation.class, "calculation-duration"));

//        ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics).build();
//        JmxReporter jmxReporter = JmxReporter.forRegistry(metrics).build();

//        reporter.start(1, TimeUnit.MINUTES); // should expose values every minute
//        jmxReporter.start();

//        environment.jersey().register(injector.getInstance(WFMConfigurationResource.class));
//        environment.healthChecks().register("Dashboard Health Check ",injector.getInstance(SpartansDashboardHealthCheck.class));
    }
}

