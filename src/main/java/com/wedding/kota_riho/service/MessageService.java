package com.wedding.kota_riho.service;

import org.springframework.stereotype.Service;

import com.wedding.kota_riho.form.RsvpForm;

@Service
public class MessageService {

    // 出欠に応じてメッセージを振り分け
    public String createMessage(RsvpForm form) {
        if ("no".equals(form.getAttendance())) {
            return createAbsentMessage(form);
        } else {
            return createAttendMessage(form);
        }
    }

    // 欠席メッセージ
    private String createAbsentMessage(RsvpForm form) {
        return switch (form.getRelation()) {
            case "relative" ->
                "ご欠席のご連絡をいただき、ありがとうございます。<br>"
              + "またの機会にお会いできることを楽しみにしております。";

            case "senior" ->
                "ご欠席の旨、承知いたしました。<br>"
              + "お忙しい中ご返信いただき、誠にありがとうございます。";

            case "friend" ->
                "欠席了解だよ！また落ち着いたらゆっくり会おうね。";

            default ->
                "ご欠席のご連絡をいただき、ありがとうございました。";
        };
    }

    // 出席メッセージ
    private String createAttendMessage(RsvpForm form) {
        return switch (form.getRelation()) {
            case "relative" ->
                "当日お会いできることを楽しみにしております。<br>"
              + "9:30までに3階受付会場へお越しください。";

            case "senior" ->
                "当日お会いできることを楽しみにしております。<br>"
              + "10:30までに3階受付会場へお越しください。<br>"
              + "エレベータなど混み合う可能性がございますので、早めにお越しください。";

            case "friend" ->
                "当日、会えるの楽しみにしてます！<br>"
              + "10:30までに3階受付会場へお越しください。<br>"
              + "混むかもだから早めに来てね！";

            default ->
                "当日お会いできることを楽しみにしております。<br>"
              + "10:30までに3階受付会場までお越しください。";
        };
    }

    // 役割メッセージ
    public String createSpecialMessage(RsvpForm form) {
        if (form.getRole() == null) return "";

        return switch (form.getRole()) {
            case "reception" ->
            	"当日お会いできることを楽しみにしております。<br>"
            	+ "当日は受付のご協力をいただき、本当にありがとうございます。<br>"
            	+ "9時半までに３階受付会場へお越しください。<br>"
            	+ "よろしくお願いいたします。";

            case "toast" ->
            	"当日お会いできることを楽しみにしております。<br>"
            	+ "9:30までに3階受付会場へお越しください。<br>"
            	+ "乾杯のご挨拶およびリングボーイ＆ガールをお引き受けいただき、心より感謝申し上げます。";

            default -> "";
        };
    }
}