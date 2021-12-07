package com.example.mointor.controller;

import com.example.mointor.service.InfoService;
import com.example.mointor.service.UsageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author KPQ
 * @date 2021/12/7
 */
@RestController
@RequestMapping(value = "/api")
public class InfoController {

    @Resource
    private InfoService infoService;

    @Resource
    private UsageService usageService;

    @GetMapping("/info")
    public ResponseEntity<?> getInfo() {
        return new ResponseEntity<>(infoService.getInfo(), HttpStatus.OK);
    }

    @GetMapping("/usage")
    public ResponseEntity<?> getUsage() {
        return new ResponseEntity<>(usageService.getUsage(), HttpStatus.OK);
    }
}
