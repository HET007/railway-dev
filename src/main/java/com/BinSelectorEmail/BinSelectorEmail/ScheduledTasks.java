package com.BinSelectorEmail.BinSelectorEmail;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

    @Autowired
    private EmailService emailService;

    private List<String> recipients = Arrays.asList("hacker.boy.het22@gmail.com", "meetsuthar08@gmail.com");
    private List<String> binColors = Arrays.asList("Yellow", "Red");

    private int currentRecipientIndex = 0;
    private int currentBinColorIndex = 0;

    // Check the day of the week and send emails accordingly
    @Scheduled(cron = "0 0 9,18 * * THU")
    public void sendEmails() {
        LocalDate currentDate = LocalDate.now();
        DayOfWeek dayOfWeek = currentDate.getDayOfWeek();

        if (dayOfWeek == DayOfWeek.THURSDAY) {
            sendEmail("Morning Reminder");
            sendEmail("Evening Reminder");

            // Update indices after both emails have been sent
            updateIndices();
        }
    }
    
    
    @Scheduled(cron = "0 * * * * ?")
    public void sendEveningEmail() {
        System.out.print("Inside");
        sendEmail("Evening Reminder");
        updateIndices();

    }

    private void sendEmail(String subject) {
        String recipient = recipients.get(currentRecipientIndex);
        String binColor = binColors.get(currentBinColorIndex);
        String messageText = String.format("This is a reminder email. This week's garbage bin color is %s.", binColor);
        emailService.sendSimpleMessage(recipient, subject, messageText);
    }

    private void updateIndices() {
        // Move to the next recipient
        currentRecipientIndex = (currentRecipientIndex + 1) % recipients.size();
        currentBinColorIndex = (currentBinColorIndex + 1) % binColors.size();

//        // If we've looped back to the first recipient, change the bin color
//        if (currentRecipientIndex == 0) {
//            currentBinColorIndex = (currentBinColorIndex + 1) % binColors.size();
//        }
    }
}
