package utils;

import org.testng.ITestContext;

public class ParamReader {
    public static String get(ITestContext ctx, String key, String fallback) {
        String fromXml = ctx.getCurrentXmlTest().getParameter(key);
        if (fromXml != null && !fromXml.isBlank()) return fromXml;
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys;
        String env = System.getenv(key);
        if (env != null && !env.isBlank()) return env;
        return fallback;
    }
}
