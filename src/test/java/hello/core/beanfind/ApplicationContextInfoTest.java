package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든빈 출력")
    public void findAll(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for(String eachBean:beanDefinitionNames){
            Object bean = applicationContext.getBean(eachBean);
            System.out.println("bean = " + bean);
        }
    }

    @Test
    @DisplayName("application 빈 출력")
    public  void findApp(){
        String[] appBeanDefinitionNames= applicationContext.getBeanDefinitionNames();
        for(String eachBean: appBeanDefinitionNames){
            BeanDefinition beanDefinition = applicationContext.getBeanDefinition(eachBean);

            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                Object bean = applicationContext.getBean(eachBean);
                System.out.println("bean = " + bean);

            }
        }


    }



}


