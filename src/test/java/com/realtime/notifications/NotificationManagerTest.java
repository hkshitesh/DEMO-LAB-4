package com.realtime.notifications;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotificationManagerTest {

    private NotificationService notificationService;
    private NotificationManager notificationManager;

    @BeforeEach
    public void setUp() {
        notificationService = Mockito.mock(NotificationService.class);
        notificationManager = new NotificationManager(notificationService);
    }

    @Test
    public void testSendNotification_instantAlert() {
        Notification notification = new Notification(NotificationType.INSTANT_ALERT, "Test Alert");
        notificationManager.sendNotification(notification);
        verify(notificationService, times(1)).sendNotification(notification);
    }

    @Test
    public void testSendNotification_userMessage() {
        Notification notification = new Notification(NotificationType.USER_MESSAGE, "Test Message");
        notificationManager.sendNotification(notification);
        verify(notificationService, times(1)).sendNotification(notification);
    }

    @Test
    public void testSendNotification_unsupportedType() {
        Notification notification = new Notification(NotificationType.UNSUPPORTED, "Unsupported");
        assertThrows(IllegalArgumentException.class, () -> notificationManager.sendNotification(notification));
    }

    @Test
    public void testScheduleNotification_scheduledReminder() {
        Notification notification = new Notification(NotificationType.SCHEDULED_REMINDER, "Test Reminder");
        long scheduledTime = 1000L; // 1 second delay
        notificationManager.scheduleNotification(notification, scheduledTime);
        verify(notificationService, timeout(scheduledTime + 500).times(1)).sendScheduledNotification(notification);
    }

    @Test
    public void testScheduleNotification_unsupportedType() {
        Notification notification = new Notification(NotificationType.UNSUPPORTED, "Unsupported");
        long scheduledTime = 1000L; // 1 second delay
        assertThrows(IllegalArgumentException.class, () -> notificationManager.scheduleNotification(notification, scheduledTime));
    }

    @Test
    public void testPersistNotifications() {
        Notification notification1 = new Notification(NotificationType.INSTANT_ALERT, "Alert 1");
        Notification notification2 = new Notification(NotificationType.USER_MESSAGE, "Message 1");
        List<Notification> notifications = Arrays.asList(notification1, notification2);
        notificationManager.persistNotifications(notifications);
        // Assuming persist logic is implemented, verify the persistence logic here
    }
}

