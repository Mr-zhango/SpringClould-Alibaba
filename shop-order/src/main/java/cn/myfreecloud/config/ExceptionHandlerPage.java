package cn.myfreecloud.config;

import com.alibaba.csp.sentinel.adapter.servlet.callback.UrlBlockHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class ExceptionHandlerPage implements UrlBlockHandler {

    ResponseData responseData = null;

    @Override
    public void blocked(HttpServletRequest request, HttpServletResponse response, BlockException e) throws IOException {
        response.setContentType("application/json;charset=utf-8");

        if(e instanceof FlowException){
            new ResponseData(-1,"接口被限流了");
        }else if(e instanceof DegradeException){
            new ResponseData(-2,"接口被限流了");
        }else if(e instanceof ParamFlowException){
            new ResponseData(-2,"参数异常被限流了");
        }else if(e instanceof AuthorityException){
            new ResponseData(-2,"授权异常被限流了");
        }else if(e instanceof SystemBlockException){
            new ResponseData(-2,"系统负载异常被限流了");
        }

        response.getWriter().write(JSON.toJSONString(responseData));
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ResponseData{
    private int code;
    private String message;
}
