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

        // 名前
        body.append(form.getLastName())
            .append(" ")
            .append(form.getFirstName())
            .append(" 様<br><br>");

        // 出席/欠席メッセージ（すでに <br> が入っている）
        body.append(baseMessage).append("<br><br>");

        // 回答内容
        body.append("―――――――――――――――――――<br>");
        body.append("■ ご回答内容<br>");
        body.append("お名前：")
            .append(form.getLastName()).append(" ").append(form.getFirstName()).append("<br>");
        body.append("ふりがな：")
            .append(form.getLastNameKana()).append(" ").append(form.getFirstNameKana()).append("<br>");
        body.append("メール：").append(form.getEmail()).append("<br>");
        body.append("電話番号：").append(form.getPhone()).append("<br>");
        body.append("出欠：").append(form.getAttendance().equals("yes") ? "出席" : "欠席").append("<br>");
        body.append("関係性：").append(relationJp).append("<br>");
        body.append("宿泊：").append(hotelJp).append("<br>");
        body.append("バス：").append(busJp).append("<br>");
        body.append("アレルギー：").append(allergyJp).append("<br>");

        if ("yes".equals(form.getAllergy())) {
            body.append("アレルギー詳細：").append(form.getAllergyText()).append("<br>");
        }

        body.append("メッセージ：").append(form.getMessage()).append("<br>");
        body.append("―――――――――――――――――――<br>");
        
        body.append("公式サイト：<br>");
        body.append("<a href=\"https://kota-riho-wedding.site\">https://kota-riho-wedding.site</a><br>");
        
        body.append("―――――――――――――――――――<br>");
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