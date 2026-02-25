package com.wedding.kota_riho.controller;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wedding.kota_riho.form.RsvpForm;
import com.wedding.kota_riho.service.GuestRoleService;
import com.wedding.kota_riho.service.MailService;
import com.wedding.kota_riho.service.MessageService;
import com.wedding.kota_riho.service.RsvpService;

@Controller
public class RsvpController {

    @Autowired
    private final RsvpService rsvpService;

    @Autowired
    private final GuestRoleService guestRoleService;

    @Autowired
    private final MailService mailService;

    @Autowired
    private final MessageService messageService;

    public RsvpController(
            RsvpService rsvpService,
            GuestRoleService guestRoleService,
            MailService mailService,
            MessageService messageService
    ) {
        this.rsvpService = rsvpService;
        this.guestRoleService = guestRoleService;
        this.mailService = mailService;
        this.messageService = messageService;
    }

    // 入力画面
    @GetMapping("/rsvp")
    public String getTop(Model model
    ) {
       
        model.addAttribute("rsvpForm", new RsvpForm());
        return "rsvp/rsvp";
    }

    @PostMapping("/rsvp")
    public String backToForm(@ModelAttribute RsvpForm rsvpForm,
            Model model
    ) {
        model.addAttribute("rsvpForm", rsvpForm);
        return "rsvp/rsvp";
    }

    // 確認画面
    @PostMapping("/rsvp/confirm")
    public String confirm(
            @Valid @ModelAttribute RsvpForm rsvpForm,
            BindingResult bindingResult,
            Model model,
            HttpSession session
    ) {
        if (bindingResult.hasErrors()) {
            return "rsvp/rsvp";
        }

        session.setAttribute("rsvpForm", rsvpForm);

        return "rsvp/rsvpConfirm";
    }

    // 保存処理
    @PostMapping("/rsvp/submit")
    public String submit(
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) throws MessagingException {

        // ★ セッションから取得
        RsvpForm rsvpForm = (RsvpForm) session.getAttribute("rsvpForm");

        // ★ セッション切れチェック
        if (rsvpForm == null) {
            return "error/sessionExpired"; // ← 専用エラー画面へ
        }
        // 役割判定
        String role = guestRoleService.detectRole(rsvpForm);
        rsvpForm.setRole(role);
        // DB保存
        rsvpService.saveRsvp(rsvpForm);
        
        // メッセージ生成
        String message = null;
        if(role != null) {
        	message = messageService.createSpecialMessage(rsvpForm);
        }else{
        	message = messageService.createMessage(rsvpForm);
        }
               
        // メール送信
        boolean mailError = false;
        try {
            mailService.sendRsvpMail(rsvpForm, message);
        } catch (Exception e) {
            e.printStackTrace();
            mailError = true;
        }

        // 完了画面用
        redirectAttributes.addFlashAttribute("name",
                rsvpForm.getLastName() + rsvpForm.getFirstName());
        redirectAttributes.addFlashAttribute("message", message);
        redirectAttributes.addFlashAttribute("mailError", mailError);

        // ★ 完了後にセッション破棄（重要）
        session.invalidate();

        return "redirect:/rsvp/complete";
    }

    // 完了画面
    @GetMapping("/rsvp/complete")
    public String complete(
            Model model,
            HttpSession session
    ) {
        session.invalidate();
        return "rsvp/rsvpComplete";
    }
}