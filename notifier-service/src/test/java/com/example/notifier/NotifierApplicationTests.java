package com.example.notifier;

import com.example.notifier.model.Message;
import com.example.notifier.repository.MessageRepository;
import com.example.notifier.service.MessageServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.mockito.Mockito.verify;

@SpringBootTest
class NotifierApplicationTests {
	@Mock
	private MessageRepository messageRepository;

	@InjectMocks
	private MessageServiceImpl messageService;

	@Captor
	private ArgumentCaptor<Message> messageCaptor;

	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void shouldSaveMessage() {
		Message message = new Message("Test title", "Test text", "Test platform name");

		messageService.saveMessage(message);


	}
}

