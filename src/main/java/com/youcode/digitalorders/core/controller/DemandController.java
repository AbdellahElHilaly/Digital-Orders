package com.youcode.digitalorders.core.controller;


import com.youcode.digitalorders.core.dao.model.dto.DemandDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.youcode.digitalorders.shared.Const.AppEndpoints.DEMAND_ENDPOINT;

@RestController
@RequestMapping(DEMAND_ENDPOINT)
public class DemandController {


    @PostMapping
    public DemandDto insert() {
        return null;
    }



}
