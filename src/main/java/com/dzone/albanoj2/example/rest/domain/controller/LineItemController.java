package com.dzone.albanoj2.example.rest.domain.controller;

import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dzone.albanoj2.example.rest.domain.LineItem;

@RestController
@ExposesResourceFor(LineItem.class)
@RequestMapping("/order/{orderId}/lineItem")
public class LineItemController {

}
