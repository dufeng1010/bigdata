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
        /*List<OrderChannel> list = new ArrayList<OrderChannel>();
        
        OrderChannel o1 = new OrderChannel();
        o1.setLabel("wechat");
        o1.setCount(2);
        
        OrderChannel o2 = new OrderChannel();
        o2.setLabel("app");
        o2.setCount(5);
        
        list.add(o1);
        list.add(o2);*/
        
        List<OrderChannel> list = restService.getOrderChannels();
        
        return list;
    }
}
