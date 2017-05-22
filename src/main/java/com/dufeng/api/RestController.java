package com.dufeng.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.dufeng.model.OrderChannel;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping("/orderChannels")
    public List<OrderChannel> getOrderChannel() {
        List<OrderChannel> list = new ArrayList<OrderChannel>();
        
        OrderChannel o1 = new OrderChannel();
        o1.setLabel("wechat");
        o1.setCount(2);
        
        OrderChannel o2 = new OrderChannel();
        o2.setLabel("app");
        o2.setCount(5);
        
        list.add(o1);
        list.add(o2);
        
        return list;
    }
}
