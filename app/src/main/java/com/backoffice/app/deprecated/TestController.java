package com.backoffice.app.deprecated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping
    public void testMethod(@RequestBody TestVO vo){
        System.out.println(vo.getDate().toString());

    }
}
