package gov.michigan.obra.automation.common.constant;

import static gov.michigan.obra.automation.util.Reader.getEnvironmentConfig;


public class ApplicationConstants {
    public static final String APPLICATION_URL = getEnvironmentConfig("application.url");
    public static final String APPLICATION_WORKER_URL = getEnvironmentConfig("application.worker.url");

    public static final String FAC_AD_USERNAME = getEnvironmentConfig("facility.admin.username");
    public static final String FAC_AD_PASSWORD = getEnvironmentConfig("facility.admin.password");

    public static final String FAC_FU_USERNAME = getEnvironmentConfig("facility.admin.username1");
    public static final String FAC_FU_PASSWORD = getEnvironmentConfig("facility.admin.password1");

    public static final String FAC_CMH_ADUSERNAME = getEnvironmentConfig("facility.admin.username2");
    public static final String FAC_CMH_ADPASSWORD = getEnvironmentConfig("facility.admin.password2");

    public static final String INVALID_LOGIN_USERNAME = getEnvironmentConfig("invalidusername");
    public static final String INVALID_LOGIN_PASSWORD = getEnvironmentConfig("invalidpassword");

    public static final String WORKER_USERNAME = getEnvironmentConfig("worker.username");
    public static final String WORKER_PASSWORD = getEnvironmentConfig("worker.password");
    public static final String AUTHORIZATION_LETTER_PATH = getEnvironmentConfig("document.upload.absolute.path");

    public static final String MI_LOGIN_WINDOW = null;

    public static final String ADMIN_USER_NAME = getEnvironmentConfig("admin.user.name");
    public static final String ADMIN_USER_PASSWORD = getEnvironmentConfig("admin.user.password");

    public static final String OBRA_HOME_WORKER_URL = getEnvironmentConfig("worker.obra.home.url");
    public static final String MI_LOGIN_SELFSERVICE_URL = getEnvironmentConfig("milogin.selfservice.page");

    public static final String OBRA_HOME_TP_URL = getEnvironmentConfig("tp.obra.dashboard");

    public static final String CMH_USERNAME = getEnvironmentConfig("cmh.username");
    public static final String CMH_PASSWORD = getEnvironmentConfig("cmh.password");

    public static final String MI_LOGIN_TP_SELFSERVICE_URL = getEnvironmentConfig("milogin.tp.selfservice.page");
    
    public static final String NSNMI_PDF_TEXT ="Based on a review of the available information, the recipient does not meet criteria for a serious mental illness,\r\n"
    		+ "developmental disability, intellectual disability, or related condition under the PASARR provisions but may\r\n"
    		+ "have a less than serious mental illness.";

    public static final String ADMIN_USER_REGISTRATION_PDF_EXPECTED_CONTENT="I hereby acknowledge that as a Facility Administrator User";


    public static final String EDIT_CONSUMER_SSN = getEnvironmentConfig("edit.consumer.with.legalrep.ssn");

    public static final String EDIT_CONSUMER_WITHOUT_LEGALREP_SSN = getEnvironmentConfig("edit.consumer.without.legalrep.ssn");

    public static final String CONSUMER_WITHOUT_LEGALREP_SSN_INITIATE_3878 = getEnvironmentConfig("consumer.without.legalrep.initiate3878.ssn");

    public static final String PLNDSC_PDF_TEXT ="Based on a review of the available information, the recipient was admitted to the nursing facility with a hospital\r\n"
    		+ "exemption. Although the resident remains at the nursing facility, there is a tentative discharge date\r\n"
    		+ "scheduled within 2 weeks.";
    public static final String TRANSFER_PDF_TEXT ="Based on a review of the available information, the recipient was admitted to the nursing facility with a hospital\r\n"
    		+ "exemption. Although the resident remains at the nursing facility, there is a tentative discharge date\r\n"
    		+ "scheduled within 2 weeks.";  		

    public static final String CMH_LOGIN_USERNAME = getEnvironmentConfig("cmh.username");
    public static final String CMH_LOGIN_PASSWORD = getEnvironmentConfig("cmh.password");

}
