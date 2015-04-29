package mini.controller;

import java.util.HashMap;
import java.util.Map;

import mini.BeanFactory;
import mini.model.User;
import mini.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ash
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "index";
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
    @ResponseBody
    public String addUser(String name, long money) {
        userService.addUser(name, money);
        return "ok";
    }

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

    public static void main(String[] args) {
        UserController uc = BeanFactory.getBean(UserController.class);
        System.out.println(uc.x());
    }
}
