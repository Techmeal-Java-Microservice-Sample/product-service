package com.example.product.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("product")
@Api(value="Product Controller", description="operations pertaining to manage product", tags = {"Products"})
public class ProductController {

}
