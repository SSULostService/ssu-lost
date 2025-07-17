package com.example.ssu_lost.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "분실물", description = "분실물 관련 API")
@RestController
@RequestMapping("/lost")
public class LostItemController {

}
