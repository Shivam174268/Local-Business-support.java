package com.localbiz.thread;

import com.localbiz.service.NotificationService;
import com.localbiz.model.Notification;

/**
 * Background worker to process notifications.
 */
public class NotificationWorker extends Thread {
    private final NotificationService service;
    private volatile boolean running = true;

    public NotificationWorker(NotificationService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (running) {
            try {
                for (Notification n : service.getNotificationQueue()) {
                    System.out.println("Processing notification: " + n.getMessage());
                    n.markRead();
                }
                Thread.sleep(2000); // wait 2 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopWorker() {
        running = false;
    }

    public void startWorker() {
        this.start();
    }
}
