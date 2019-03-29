package cspy.online.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author CSpy
 * @date 2019/3/21 19:31
 */
@Slf4j
@Data

public class SystemLogger {


    private String s;

    @Autowired
    Logger logger;


    public void systemLogger(JoinPoint joinPoint) {
        System.out.println(Arrays.toString(joinPoint.getArgs()));
        Object[] args = joinPoint.getArgs();
        String type = (String) args[0];
        switch (type) {
            case "info":break;
            case "warn":break;
            case "error":break;
            default:

        }
        logger.info(args[0] + "" + args[1]);


    }
}
