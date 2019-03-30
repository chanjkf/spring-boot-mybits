package top.chanjkf.test.controller;


import com.alibaba.druid.support.json.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.chanjkf.test.aspect.AccessCheck;
import top.chanjkf.test.constants.Constants;
import top.chanjkf.test.entity.User;
import top.chanjkf.test.service.IUserService;
import top.chanjkf.test.util.ResponseUtil;

import java.util.*;

@RestController
@RequestMapping("/user")
public class MainController {

    @Autowired
    IUserService service;

    private static int start = 5;
    private volatile static int start1 = 5;


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        if (!user.check()) {
            return ResponseUtil.fail("uname or pwd is null", null);
        }
        user.setCreatedTime(new Date());
        user.setUpdatedTime(new Date());
        int i = service.insertUser(user);
        return ResponseUtil.success(i);
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@RequestBody User user) {
        if (!user.check()) {
            return ResponseUtil.fail("error", null);
        }
        List<User> userByName = service.findUserByName(user.getUserName());
        if (userByName != null && userByName.size() > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put(Constants.TOKEN_NAME, Constants.TOKEN_VALUE);
            return ResponseUtil.success(map);
        }
        return ResponseUtil.fail("user not exist", null);
    }

    @AccessCheck
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    public String getA() {
        return ResponseUtil.success(new Random().nextInt(100));
    }

    @AccessCheck
    @RequestMapping(value = "/b", method = RequestMethod.GET)
    public String getB() {
        return ResponseUtil.success(new Random().nextInt(100));
    }

    @AccessCheck
    @RequestMapping(value = "/sum", method = RequestMethod.GET)
    public String sum(@RequestParam(value = "a") int a,
                      @RequestParam(value = "b") int b) {

        int i = new Random().nextInt(10);
        if (i != 4) {
            return ResponseUtil.success(a + b);
        }
        return ResponseUtil.success(1);
    }

    @AccessCheck
    @RequestMapping(value = "/sync/no", method = RequestMethod.GET)
    public String syncNo() {
        int temp = start + 1;
        try {
            Thread.sleep(new Random().nextInt(10000) + 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        start = temp;
        return ResponseUtil.success(start);
    }

    @AccessCheck
    @RequestMapping(value = "/sync/yes", method = RequestMethod.GET)
    public String syncYes() {
        synchronized (MainController.class) {
            int temp = start1 + 1;
            try {
                Thread.sleep(new Random().nextInt(100) + 20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            start1 = temp;
            return ResponseUtil.success(start1);
        }
    }

    @AccessCheck
    @RequestMapping(value = "/mysql", method = RequestMethod.GET)
    public String testMysql() {
        List<User> users = service.findUserByName("aaa");
        return ResponseUtil.success("success");
    }
}
