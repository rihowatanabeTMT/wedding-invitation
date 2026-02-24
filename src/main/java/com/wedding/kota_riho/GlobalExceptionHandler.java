package com.wedding.kota_riho;
import java.io.IOException;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // ★ DB エラー（DataAccessException）
    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseError(DataAccessException ex, Model model) {
        model.addAttribute("errorMessage", "データベース処理中にエラーが発生しました。");
        return "error/dbError";
    }

    // ★ PDF 読み込みエラー（IOException）
    @ExceptionHandler(IOException.class)
    public String handlePdfError(IOException ex, Model model) {
        model.addAttribute("errorMessage", "PDF を読み込めませんでした。");
        return "error/pdfError";
    }

    // ★ その他の例外（500.html に任せる）
    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Exception ex, Model model) {
        return "error/500";
    }
}
