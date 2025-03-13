package com.realtime.notifications;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class NotificationManager {

    private NotificationService notificationService;

    public NotificationManager(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    /**
     * Sends an instant notification.
     *
     * @param notification the notification to be sent
     * @throws IllegalArgumentException if the notification type is unsupported
     */
    public void sendNotification(Notification notification) {
        // Logic to send instant notification
        switch (notification.getType()) {
            case INSTANT_ALERT:
            case USER_MESSAGE:
                notificationService.sendNotification(notification);
                break;
            default:
                throw new IllegalArgumentException("Unsupported notification type");
        }
    }

    /**
     * Schedules a notification to be sent at a specified time.
     *
     * @param notification the notification to be scheduled
     * @param scheduledTime the time at which the notification should be sent
     * @throws IllegalArgumentException if the notification type is unsupported for scheduling
     */
    public void scheduleNotification(Notification notification, long scheduledTime) {
        // Logic to schedule notification
        switch (notification.getType()) {
            case SCHEDULED_REMINDER:
                Timer timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        notificationService.sendScheduledNotification(notification);
                    }
                }, scheduledTime);
                break;
            default:
                throw new IllegalArgumentException("Unsupported notification type for scheduling");
        }
    }

    /**
     * Persists a list of notifications.
     *
     * @param notifications the list of notifications to be persisted
     */
    public void persistNotifications(List<Notification> notifications) {
        // Logic to persist notifications
        for (Notification notification : notifications) {
            // Persist each notification
        }
    }
}