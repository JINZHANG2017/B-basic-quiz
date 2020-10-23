package com.thoughtworks.quiz.api;

import com.thoughtworks.quiz.dto.Education;
import com.thoughtworks.quiz.dto.User;
import com.thoughtworks.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {

    // TODO GTB-3: - 推荐使用构造器注入
    // TODO GTB-3: - 违反了封装性，字段应该使用private权限修饰符
    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    // TODO GTB-3: - 建议用@PathVariable的value属性显式的指定请求参数名
    public User getUser(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    // TODO GTB-3: - 违反Restful实践，Post接口应返回创建成功的对象
    public String addUser(@RequestBody User user){
        Long id = userService.addUser(user);
        // TODO GTB-4: - 请不要手动拼写JSON，需要定义对象，然后由框架序列化
        return "{\"id\":\""+id+"\"}";
    }
    @GetMapping("/{id}/educations")
    public List<Education> getUserEducations(@PathVariable Long id){
        return userService.getEducationsByUserId(id);
    }

    @PostMapping("/{id}/educations")
    public void addEducation(@PathVariable Long id,@RequestBody Education education){
        userService.addEducation(id,education);
    }
}
