package com.javalad.habitdeveloper.test.service.util;

import com.javalad.habitdeveloper.HabitDeveloper;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;

/**
 * @author KotovDV
 */


@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(HabitDeveloper.class)
public abstract class AbstractMockitoServiceTest {}
