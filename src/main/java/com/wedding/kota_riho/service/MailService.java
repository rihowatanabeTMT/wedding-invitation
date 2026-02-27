package com.wedding.kota_riho.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.wedding.kota_riho.form.RsvpForm;
import com.wedding.kota_riho.util.RsvpValueConverter;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRsvpMail(RsvpForm form, String baseMessage) throws MessagingException {

        String to = form.getEmail();
        String subject = "【ご回答ありがとうございます】結婚式 RSVP";

        // ★ 日本語変換
        String relationJp = RsvpValueConverter.relation(form.getRelation());
        String hotelJp = RsvpValueConverter.hotel(form.getHotel());
        String busJp = RsvpValueConverter.bus(form.getBus());
        String allergyJp = RsvpValueConverter.allergy(form.getAllergy());

        StringBuilder body = new StringBuilder();

     // タイトル
     body.append("<h2 style=\"font-weight:bold; font-size:20px; margin-bottom:16px;\">")
         .append("RSVP ご回答ありがとうございます")
         .append("</h2>");

     // 名前
     body.append(form.getLastName())
         .append(" ")
         .append(form.getFirstName())
         .append(" 様<br><br>");

     // 出席/欠席メッセージ
     body.append(baseMessage).append("<br><br>");

     // ■ 会場案内（地図リンク付き）
     body.append("<div style=\"margin-bottom:20px;\">")
     .append("<strong style=\"font-size:16px;\">■ 会場のご案内</strong><br><br>")
     .append("会場名：THE GRAND ORIENTAL MINATOMIRAI（グランドオリエンタルみなとみらい）<br>")
     .append("<a href=\"https://www.google.com/maps/place/?q=グランドオリエンタルみなとみらい\" ")
     .append("style=\"color:#005BAC; text-decoration:underline;\">Googleマップで開く</a><br>")


     .append("</div>");


     // ■ 回答内容（枠で囲む）
     body.append("<div style=\"border:1px solid #ddd; padding:16px; border-radius:8px; background:#fafafa;\">");
     body.append("<strong style=\"font-size:16px;\">■ ご回答内容</strong><br><br>");

     body.append("お名前：")
         .append(form.getLastName()).append(" ").append(form.getFirstName()).append("<br>");

     body.append("出席：")
     .append(form.getAttendance().equals("yes") ? "出席" : "欠席")
     .append("<br>");

     body.append("新郎新婦どちら側：")
     .append(form.getGuestSide().equals("groom") ? "新郎側" : "新婦側")
     .append("<br>");

     body.append("関係性：").append(relationJp).append("<br>");
     body.append("宿泊：").append(hotelJp).append("<br>");
     body.append("バス：").append(busJp).append("<br>");
     body.append("アレルギー：").append(allergyJp).append("<br>");

     if ("yes".equals(form.getAllergy())) {
    	    body.append("アレルギー詳細：").append(form.getAllergyText()).append("<br>");
    	}

     // 同伴者
     if (form.getPlusones() != null && !form.getPlusones().isEmpty()) {
         body.append("<br><strong>■ 同伴者</strong><br>");
         form.getPlusones().forEach(plusOne -> {
             body.append("・ ").append(plusOne.getPlusOneName()).append("<br>");
         });
     }

     body.append("</div><br>");

     // 招待状ページボタン
     body.append("<a href=\"https://kota-riho-wedding.site\" "
             + "style=\"display:inline-block; padding:12px 20px; background:#005BAC; color:#fff; "
             + "text-decoration:none; border-radius:6px; font-weight:bold; margin-top:12px;\">"
             + "招待状ページはこちら"
             + "</a><br><br>");
        
        // ★ HTMLメールとして送る
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("Kota & Riho Wedding <weddingInfomation@kota-riho-wedding.site>");
        helper.setText(body.toString(), true); // ← HTML対応

        mailSender.send(message);
    }
}