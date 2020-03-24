package org.kevin.demo0212;

import org.junit.jupiter.api.Test;
import org.kevin.demo0212.common.CommonUtils;

/**
 * @author Kevin.Z
 * @version 2020-03-15
 */
public class Tester {

    @Test
    void testLength(){
        String s = "0322";
        String e = CommonUtils.securityPwdEncoder(s);
        System.out.println(s);
        System.out.println(e.length());

        // fng: {bcrypt}$2a$10$EYmG0xP0woV1bzq3GNGayO5NZJDemAperf7412u6LC1kvC/DzTfnG
        // 0322: {bcrypt}$2a$10$b5fujPML6omKYzCb/3P8seY4obHc.70p/sQYAfE2Y/3cVaJBQIJn6
    }
}
