package cn.myfreecloud.config;

//
//import com.alibaba.csp.sentinel.adapter.servlet.callback.RequestOriginParser;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Component
//public class RequestOriginParserDefinition implements RequestOriginParser {
//
//    @Override
//    public String parseOrigin(HttpServletRequest request) {
//        // 定义区分来源,本质作用是通过request域来获取来源标识
//        // app pc
//        // 然后交给流控应用 位置进行匹配
//        String serviceName = request.getParameter("serviceName");
//        if(StringUtils.isEmpty(serviceName)){
//            throw new RuntimeException("serviceName is not empty");
//        }
//        return serviceName;
//    }
//}
