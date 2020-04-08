package com.wb.service;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class TranslatorService {
    private final ResourceBundleMessageSource messageSource;

    TranslatorService(ResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String toLocale(String msgCode) {
        return messageSource.getMessage(msgCode, null, LocaleContextHolder.getLocale());
    }
}