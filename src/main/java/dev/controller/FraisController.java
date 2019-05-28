package dev.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/frais")
@Secured("ROLE_EMPLOYE")
public class FraisController
{

}
