package com.wedding.kota_riho.util;

public class RsvpValueConverter {

    public static String relation(String relation) {
        return switch (relation) {
            case "relative" -> "ご親族";
            case "friend" -> "ご友人";
            case "senior" -> "職場関係";
            default -> "その他";
        };
    }

    public static String hotel(String hotel) {
        return hotel.equals("yes") ? "あり" : "なし";
    }

    public static String bus(String bus) {
        return bus.equals("yes") ? "利用する" : "利用しない";
    }

    public static String allergy(String allergy) {
        return allergy.equals("yes") ? "あり" : "なし";
    }
}