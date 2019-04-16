package com.example.aser.careerbook;

import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import me.leolin.shortcutbadger.ShortcutBadger;

public class MyMessagingService extends FirebaseMessagingService {
    int badgeCount=0;

    @Override

    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        saveNotification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        badgeCount++;
        ShortcutBadger.applyCount(this, badgeCount);
    }
    public void showNotification(String title,String message)
    {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"MyNotification")
                .setContentTitle(title)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setAutoCancel(true)
                .setContentText(message);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(999,builder.build());
    }
    public  void saveNotification(String title,String message)
    {
        String messaget="workagain";
        String titlet = "yes";
        DatabaseReference notificationref;
        notificationref = FirebaseDatabase.getInstance().getReference("Notification");
        String id = notificationref.push().getKey();
        Notification_Model notificationModel =new Notification_Model(title,message);
        notificationref.child(id).setValue(notificationModel);

    }

}
