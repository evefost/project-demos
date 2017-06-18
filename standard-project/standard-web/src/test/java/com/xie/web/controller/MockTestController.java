package com.xie.web.controller;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * mock 单无测试
 */
public class MockTestController extends BaseTest{

    @Test
    public  void addUser() throws Exception{
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addParameter("name", "senvonaasf");
        HttpSession session = request.getSession();
        session.setAttribute("name", "senvonInSession");

        //没有使用spring注入
        //无法注入service
        UserController controller = new UserController();
        Map<String , Object> result = controller.test(request);
        Assert.assertTrue(result != null);
        Assert.assertTrue("123".equalsIgnoreCase(result.get("returnCode")+"" ));

    }

}
