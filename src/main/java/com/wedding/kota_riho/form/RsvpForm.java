package com.wedding.kota_riho.form;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import com.wedding.kota_riho.Validation.AllergyDetailRequired;

import lombok.Data;

@Data
@AllergyDetailRequired
public class RsvpForm {

    // 名前
    @NotBlank(message = "姓は必須入力です")
    private String lastName;

    @NotBlank(message = "名は必須入力です")
    private String firstName;

    @NotBlank(message = "姓(かな)は必須入力です")
    private String lastNameKana;

    @NotBlank(message = "名(かな)は必須入力です")
    private String firstNameKana;

    @NotBlank(message = "姓(ローマ字)は必須入力です")
    private String lastNameEn;

    @NotBlank(message = "名(ローマ字)は必須入力です")
    private String firstNameEn;

    // 新郎新婦どちら側
    @NotBlank(message = "新婦もしくは新郎をお選びください")
    private String guestSide = "groom"; // groom / bride

    // 関係性
    @NotBlank(message = "関係性は必須選択です")
    private String relation = "relative"; // relative / company / friend / other

    // 出欠
    @NotBlank(message = "出欠は必須選択です")
    private String attendance = "yes"; // yes / no

    // 電話番号
    @NotBlank(message = "電話番号は必須入力です")
    private String phone;

    // メール
    @Email(message = "メールアドレスの形が正しくありません")
    @NotBlank(message = "メールアドレスは必須入力です")
    private String email;

    // 郵便番号
    @NotBlank(message = "郵便番号は必須入力です")
    @Pattern(regexp = "\\d{3}-?\\d{4}")
    private String post;

    // 都道府県
    @NotBlank(message = "都道府県は必須選択です")
    private String prefecture;

    // 市区町村
    @NotBlank(message = "市町村は必須入力です")
    private String address;

    // 建物名
    private String building;

    // 宿泊
    @NotBlank(message = "宿泊の有無は必須選択です")
    private String hotel = "yes"; // yes / no

    // バス
    @NotBlank(message = "シャトルバス利用有無は必須選択です")
    private String bus = "yes"; // yes / no

    // アレルギー
    @NotBlank(message = "アレルギー有無は必須選択です")
    private String allergy = "no"; // yes / no
    
    private String allergyText;

    // 同伴者
    private List<PlusoneForm> plusones = new ArrayList<>();
    
    private String plusone_flag= "no";
    
    // メッセージ
    private String message;
    
    //役割(受付・乾杯)
    private String role;
}
