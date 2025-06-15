package com.cbrit0.microservices.notification.service;

import com.cbrit0.microservices.order.event.OrderPlacedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final JavaMailSender javaMailSender;

    @KafkaListener(topics = "order-placed")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Received message from order-placed topic: {}", orderPlacedEvent);
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("springshop@gmail.com");
            messageHelper.setTo(orderPlacedEvent.getEmail());
            messageHelper.setSubject(String.format("Order %s placed successfully", orderPlacedEvent.getOrderNumber()));
            messageHelper.setText(String.format(
                    """
                    Hello,
                    
                    Thank you for placing an order with us. Your order number is %s.
                    
                    We will notify you once your order is shipped.
                    
                    Regards,
                    Spring Shop Team
                    """,
                    orderPlacedEvent.getOrderNumber()
            ));
        };

        try {
            javaMailSender.send(messagePreparator);
            log.info("Email sent successfully to {}", orderPlacedEvent.getEmail());
        } catch (MailException e) {
            log.error("Failed to send email to {}: {}", orderPlacedEvent.getEmail(), e.getMessage());
            throw new RuntimeException("Failed to send email notification", e);
        }
    }
}
