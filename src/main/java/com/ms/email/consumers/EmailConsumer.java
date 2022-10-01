package com.ms.email.consumers;

import com.ms.email.dtos.EmailDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queues}")
    public void listen(@Payload EmailDto dto){
        EmailModel model = new EmailModel();
        BeanUtils.copyProperties(dto, model);
        emailService.sendEmail(model);
        System.out.println("status do email: " + model.getStatusEmail().toString());
    }
}
