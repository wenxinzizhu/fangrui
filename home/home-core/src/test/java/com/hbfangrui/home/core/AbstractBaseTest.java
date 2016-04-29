package com.hbfangrui.home.core;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by tao.li on 2016/4/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = {"classpath:resources.xml", "classpath:infra/*.xml","classpath:services/*.xml"})
public abstract class AbstractBaseTest {
}
