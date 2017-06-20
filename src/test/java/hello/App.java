package hello;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/6/15/015.
 */
public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        IService service = context.getBean("myGateway", IService.class);
        String world = service.hello("world");
        System.out.println(world);
    }
}
