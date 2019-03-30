package top.chanjkf.test.aspect;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import top.chanjkf.test.constants.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

@Aspect
@Component
public class TokenAspect {

    @Pointcut("@annotation(top.chanjkf.test.aspect.AccessCheck)")
    private void anyMethod() {

    }

    @Around("anyMethod()")
    public Object checkAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getResponse();

        request.setCharacterEncoding("UTF-8");
        String token = request.getHeader(Constants.TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "没有token");
            return null;
        }
        if (Constants.TOKEN_VALUE.equals(token)) {
            try {
                Object proceed = joinPoint.proceed();
                return proceed;
            } catch (Throwable throwable) {
                return null;
            }
        }
        return null;
    }

}
