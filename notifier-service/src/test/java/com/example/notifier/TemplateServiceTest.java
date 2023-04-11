package com.example.notifier;

import com.example.notifier.model.Template;
import com.example.notifier.service.TemplateService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TemplateServiceTest {

    @Autowired
    private TemplateService templateService;

    @Test
    public void testGetTemplateById() {
        Template result = templateService.getTemplateById(3);

        assertNotNull(result);
        assertEquals(result.getId(), Optional.of(2L).get());
        assertEquals(result.getText(), "Test Template");
    }
}