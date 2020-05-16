//package cn.myfreecloud.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RefreshScope
//public class NacosConfigConfig {
//
//    /*推荐使用,配置注解支持动态刷新*/
//    @Value("${config.appName}")
//    private String appName;
//
//    @Value("${config.env}")
//    private String env;
//
//    @Value("${config.test}")
//    private String test;
//
//
//    @Autowired
//    private ConfigurableApplicationContext applicationContext;
//
//    @RequestMapping("test-config1")
//    public String testConfig(){
//       return applicationContext.getEnvironment().getProperty("config.appName");
//    }
//
//    @RequestMapping("test-config2")
//    public String testConfig2(){
//        return appName;
//    }
//
//    @RequestMapping("test-config3")
//    public String testConfig3(){
//        return env;
//    }
//
//    @RequestMapping("test-config4")
//    public String testConfig4(){
//        return test;
//    }
//}
