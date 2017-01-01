package main.com.express.controller;

import com.sun.deploy.net.HttpResponse;
import main.com.express.entity.User;
import main.com.express.service.AddressService;
import main.com.express.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by linzhijie on 2016/12/31.
 */
@Controller
@RequestMapping("/user")
public class registerController {
    @Autowired
    private UserService userService;
    @Autowired
    private AddressService addressService;

    @RequestMapping(value = "/checkUser")
    public void checkUser(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String username ;
        String phone = request.getParameter("phone");
        if(userService.hasUser(phone)){
            username = userService.getUserByPhone(phone).getUsername();
        }
        else
            username = "";
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(username);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/addUser")
    public String registerUser(User user,HttpSession session){
        if(!userService.hasUser(user.getPhone())) {
            userService.addUser(user);
        }
        //else
            //userService.updUser(user);
        session.setAttribute("user",user);
            return "neworder";
    }

}
