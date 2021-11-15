package com.disqo.interview_flow_service.client;

import com.disqo.interview_flow_service.persistance.entity.Talent;
import com.disqo.interview_flow_service.persistance.entity.User;
import com.disqo.interview_flow_service.persistance.enums.EmailTextType;

import java.net.URI;

public class MailTextGenerator {

    private MailTextGenerator() {
    }

    private static final String subject = "Interview invitation with Talent_Journey company";

    private static final String textToTalent = "Hi dear  %s,\n" +
            "We want to interview you, please choose the time that are convenient for you \n" +
            " %s \n" +
            " Best regards, \n" +
            "Talent_Journey ";

    private static final String textToTalentAgain = "Dear  %s,\n" +
            " " + "We have made a new time calendar,please choose the time from the new calendar that are convenient for you\n" +
            " %s \n" +
            "Best regards, \n" +
            "Talent_Journey ";

    private static final String textToUsers = "Dear %s, \n" +
            "The Talent hasn't selected any time \n" +
            " %s \n" +
            "Sincerely Interview Flow";

    public static String getSubject() {
        return subject;
    }

    public static String getEmailText(Talent talent, User user, EmailTextType emailTextType, URI uri) {
        switch (emailTextType) {

            case TO_TALENT_FIRST:
                return String.format(textToTalent, talent.getName(), uri);

            case TO_TALENT:
                return String.format(textToTalentAgain, talent.getName(), uri);

            case TO_USER:
                return String.format(textToUsers, user.getFirstName(), uri);

            default:
                return "";

        }
    }


}
