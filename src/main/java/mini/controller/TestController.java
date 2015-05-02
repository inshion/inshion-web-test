package mini.controller;

import java.util.HashMap;
import java.util.Map;

import mini.model.User;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ash
 */

@RestController
public class TestController {

    @RequestMapping(value = "t", method = RequestMethod.GET)
    @ResponseBody
    public String t() {
        System.out.println("tttttt------------------");
        return "ok";
    }

    @RequestMapping(value = "x", method = RequestMethod.GET)
    public Map<String, Object> x() {

        Map<String, Object> a = new HashMap<>();
        a.put("a", "aaa");
        a.put("b", "bbbb");
        System.out.println("x-> a:" + a);
        return a;
    }

    @RequestMapping(value = "x1", method = RequestMethod.GET)
    public User x1() {

        User a = new User();
        a.setMoney(1234);
        a.setName("jim");
        System.out.println("x-> a:" + a);
        return a;
    }

}
