package test;

import mini.BeanFactory;
import mini.controller.TestController;
import mini.model.User;

public class Main {

    public static void main(String[] args) {

        TestController b = BeanFactory.getBean(TestController.class);
        User ret = b.x1();
        System.out.println(ret);
    }
}
