package com.longpets.longpetsecommerce.dto.response;

import org.springframework.beans.factory.annotation.Value;

public interface MoneyTotal12MonthResponseDto {
    @Value("#{target.category_name}")
    String getCategoryName();

    @Value("#{target.month1}")
    Long getMonth1();

    @Value("#{target.month2}")
    Long getMonth2();

    @Value("#{target.month3}")
    Long getMonth3();

    @Value("#{target.month4}")
    Long getMonth4();

    @Value("#{target.month5}")
    Long getMonth5();

    @Value("#{target.month6}")
    Long getMonth6();

    @Value("#{target.month7}")
    Long getMonth7();

    @Value("#{target.month8}")
    Long getMonth8();

    @Value("#{target.month9}")
    Long getMonth9();

    @Value("#{target.month10}")
    Long getMonth10();

    @Value("#{target.month11}")
    Long getMonth11();

    @Value("#{target.month12}")
    Long getMonth12();

    @Value("#{target.total_year}")
    Long getTotalYear();
}
