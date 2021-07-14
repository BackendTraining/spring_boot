package com.training.springbootbuyitem.utils.properties;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ItemStorageProperties {

    @Value("${notification.email.send:false}")
    private boolean sendNotificationEmail;

    @Value("homilzio.santos@celfocus.com")
    private String sendNotificationEmailUri;

    @Value("notification.email.recipient")
    private String sendNotificationEmailRecipient;

    @Value("notification.email.subject.restock")
    private String sendNotificationEmailSubject;

    @Value("notification.email.subject.message")
    private String sendNotificationEmailMessage;

}
