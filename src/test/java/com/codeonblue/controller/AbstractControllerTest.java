package com.codeonblue.controller;

import com.codeonblue.AbstractTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public abstract class AbstractControllerTest extends AbstractTest{

    @Autowired
    protected MockMvc mvc;


}
