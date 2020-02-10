package com.wb.config;

import com.wb.utility.ApplicationConstants;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

@Configuration
public class LocaleResolver extends AcceptHeaderLocaleResolver {
	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		if (request.getHeader("Accept-Language") == null) {
			return Locale.getDefault();
		}
		List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
		Locale locale = Locale.lookup(list, ApplicationConstants.LOCALES);
		return locale;
	}
}
