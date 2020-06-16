package waw.anotherworld.demon.common.constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServiceConstant {

    @Autowired
    public ServiceUrls PROPS;

    @Component
    public class ServiceUrls {
//        @Value("${juvis.athome.prop.auth.loginPage}")
//        public String loginPage;
//        @Value("${juvis.athome.prop.auth.loginProcess}")
//        public String loginProcess;
//        @Value("${juvis.athome.prop.auth.indexPage}")
//        public String indexPage;
//        @Value("${juvis.athome.prop.auth.logoutPage}")
//        public String logoutPage;
//        @Value("${juvis.athome.prop.auth.username}")
//        public String userNameParam;
//        @Value("${juvis.athome.prop.auth.password}")
//        public String passwordParam;
//        @Value("${juvis.athome.prop.auth.rememberMe}")
//        public String remeberMe;
//        @Value("${juvis.athome.prop.auth.denied}")
//        public String auccessDenied;
//        @Value("${juvis.athome.prop.token.keepTime}")
//        public int keepTime;
//        @Value("${juvis.athome.prop.token.key}")
//        public String tokenKey;

    }

    public enum ReportType {

        REGULAR_NOTICE("A", "정기공시"),
        MAJOR_POINT_NOTICE("B","주요사항 공시"),
        ISSUE_NOTICE("C", "발행공시"),
        STOCK_STAKE_NOTICE("D", "지분공시");


        private String code;
        private String desc;

        ReportType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {return code; }
        public String getDesc() {return desc; }

    }

    public enum  CategoryType {

        KOSDAQ("K", "코스닥"),
        EXTRA("E", "기타"),
        OIL("Y", "유가");

        private String code;
        private String desc;

        CategoryType(String code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public String getCode() {return code; }
        public String getDesc() {return desc; }

    }


    // AtHome Biz type
    public final static String BIZ_TYPE_MEMBER = "member";
    public final static String BIZ_TYPE_NONMEMBER = "nonmember";
    public final static String BIZ_TYPE_PARTNER = "partner";

    // AtHome Empty Type
    public final static String EMPTY_STRING = "";

    public static final class HEADER_KEY {
    public static final String AUTH_TOKEN = "AUTH_TOKEN";
    public static final String ACCESS_IP = "X-FORWARDED-FOR";
    }

}
