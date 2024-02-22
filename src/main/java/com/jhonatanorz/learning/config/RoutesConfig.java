package com.jhonatanorz.learning.config;

public class RoutesConfig {

    public static class API {

        public static final String SIGN_IN = "/api/auth/signin";
        public static final String USER = "/api/me";

        public static final String TESTS = "/api/tests";
        public static final String COMPANY = "/api/companies/{companyId}";
        public static final String COMPANY_DEPARTMENTS = "/api/companies/{companyId}/departments";
        public static final String COMPANY_EMPLOYEES = "/api/companies/{companyId}/employees";
        public static final String COMPANY_ASSESSMENT_CAMPAIGNS = "/api/companies/{companyId}/assessment-campaigns";
        public static final String COMPANY_SUGGESTIONS = "/api/companies/{companyId}/suggestions";
        public static final String COMPANY_COMPLAINTS = "/api/companies/{companyId}/complaints";
        public static final String EMPLOYEES_AT_RISK = "/api/companies/{companyId}/employees/at-risk";

        public static final String TEST = "/api/tests/{testId}";

        public static final String DEPARTMENT = "/api/departments/{departmentId}";
        public static final String EMPLOYEE = "/api/employees/{employeeId}";
        public static final String EMPLOYEE_PROFILE = "/api/employees/{employeeId}/profile";
        public static final String EMPLOYEE_ASSESSMENTS = "/api/employees/{employeeId}/assessments";

        public static final String ASSESSMENT_CAMPAIGN = "/api/assessment-campaigns/{campaignId}";
        public static final String ASSESSMENT_CAMPAIGN_STATS = "/api/assessment-campaigns/{campaignId}/stats";
        public static final String ASSESSMENT_CAMPAIGN_RESULTS = "/api/assessment-campaigns/{campaignId}/results";
        public static final String ASSESSMENT_CAMPAIGN_RESULTS_RECORDS = "/api/assessment-campaigns/{campaignId}/results/records";
        public static final String ASSESSMENT_CAMPAIGN_ASSESSMENTS = "/api/assessment-campaigns/{campaignId}/assessments";

        public static final String ASSESSMENT = "/api/assessments/{assessmentId}";
        public static final String ASSESSMENT_RISK_DETAILS = "/api/assessments/{assessmentId}/risk-details";
        public static final String ASSESSMENT_ANSWERS = "/api/assessments/{assessmentId}/answers";

        public static final String SUGGESTION = "/api/suggestions/{suggestionId}";
        public static final String COMPLAINT = "/api/complaints/{complaintId}";
    }

    public static class UI {

        public static final String LOGIN = "/login";
        public static final String ROOT = "/";
        public static final String ADMIN_DASHBOARD = "/admin/dashboard";
        public static final String EMPLOYEE_DASHBOARD = "/employee/dashboard";
        public static final String DEPARTMENTS = "/departments";
        public static final String EMPLOYEES = "/employees";
        public static final String EMPLOYEE_FILES = "/employees/files";
        public static final String EMPLOYEE_ASSIGNED_ASSESSMENTS_SEARCHER = "/employees/assessments";
        public static final String ASSESSMENT_CAMPAIGNS = "/assessment-campaigns";
        public static final String COMPLAINTS_AND_SUGGESTIONS_BOX = "/complaints-and-suggestions/box";
        public static final String COMPLAINTS_AND_SUGGESTIONS = "/complaints-and-suggestions";
        public static final String ASSESSMENT_CAMPAIGN_DETAILS = "/assessment-campaigns/{campaignId}";
        public static final String ASSESSMENT_DETAILS = "/assessments/{assessmentId}";
        public static final String ANSWER_ASSESSMENT = "/assessments/{assessmentId}/answer";

        public static final String[] ALL = {
                LOGIN,
                ROOT,
                ADMIN_DASHBOARD,
                EMPLOYEE_DASHBOARD,
                DEPARTMENTS,
                EMPLOYEES,
                EMPLOYEE_FILES,
                EMPLOYEE_ASSIGNED_ASSESSMENTS_SEARCHER,
                ASSESSMENT_CAMPAIGNS,
                COMPLAINTS_AND_SUGGESTIONS_BOX,
                COMPLAINTS_AND_SUGGESTIONS,
                ASSESSMENT_CAMPAIGN_DETAILS,
                ASSESSMENT_DETAILS,
                ANSWER_ASSESSMENT
        };

    }

}
