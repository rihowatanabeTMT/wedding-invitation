package com.wedding.kota_riho;
import java.io.IOException;

import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public String handleDatabaseError(
            DataAccessException ex,
            @RequestParam(required = false) String from,
            Model model
    ) {
        model.addAttribute("from", from);
        model.addAttribute("errorMessage", "データベース処理中にエラーが発生しました。");
        return "error/dbError";
    }

    @ExceptionHandler(IOException.class)
    public String handlePdfError(
            IOException ex,
            @RequestParam(required = false) String from,
            Model model
    ) {
        model.addAttribute("from", from);
        model.addAttribute("errorMessage", "PDF を読み込めませんでした。");
        return "error/pdfError";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralError(
            Exception ex,
            @RequestParam(required = false) String from,
            Model model
    ) {
        model.addAttribute("from", from);
        return "error/500";
    }
}