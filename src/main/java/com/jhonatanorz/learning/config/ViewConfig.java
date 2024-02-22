package com.jhonatanorz.learning.config;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig {

    public static final String RESOURCE_NOT_FOUND = "errors/404";
    public static final String BAD_REQUEST = "errors/400";
    public static final String UNAUTHORIZED = "errors/401";
    public static final String INTERNAL_SERVER_ERROR = "errors/500";

    public static final String LOGIN_VIEW = "auth/login";
    public static final String INDEX_VIEW = "index";
    public static final String ADMIN_DASHBOARD_VIEW = "dashboard/admin-dashboard";
    public static final String EMPLOYEE_DASHBOARD_VIEW = "dashboard/employee-dashboard";

    public static final String COMPANY_DEPARTMENTS_VIEW = "companies/departments";
    public static final String COMPANY_EMPLOYEES_VIEW = "companies/employees";
    public static final String ASSESSMENT_CAMPAIGNS_HISTORY_VIEW = "assessments/campaigns-history";
    public static final String ASSIGNED_EMPLOYEE_ASSESSMENTS_VIEW = "assessments/assigned-assessments";
    public static final String ASSIGNED_ASSESSMENTS_SEARCHER_VIEW = "assessments/assigned-assessments-searcher";
    public static final String ANSWER_EMPLOYEE_ASSESSMENT = "assessments/answer-employee-assessment";
    public static final String EMPLOYEE_ASSESSMENT_DETAILS_VIEW = "assessments/employee-assessment-details";
    public static final String EMPLOYEE_FILES_VIEW = "employees/files";
    public static final String ASSESSMENT_CAMPAIGN_RESULTS_VIEW = "assessments/campaign-results";
    public static final String COMPLAINTS_AND_SUGGESTIONS_BOX_VIEW = "complaints-and-suggestions/box";
    public static final String COMPLAINTS_AND_SUGGESTIONS_HISTORY_VIEW = "complaints-and-suggestions/history";

    /**
     * Required to use hierarchical layouts on thymeleaf
     *
     * @return layoutDialect
     */
    @Bean
    public LayoutDialect layoutDialect() {
        return new LayoutDialect();
    }
}
