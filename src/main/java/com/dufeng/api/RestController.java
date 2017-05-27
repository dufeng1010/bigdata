package com.dufeng.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dufeng.model.OrderChannel;
import com.dufeng.service.RestService;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    
    @Autowired
    private RestService restService;

    @RequestMapping("/orderChannels")
    public List<OrderChannel> getOrderChannel() {
        List<OrderChannel> list = restService.getOrderChannels();
        
        return list;
    }
}
