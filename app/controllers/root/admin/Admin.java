package controllers.root.admin;

import controllers.resources.AdminResource;
import controllers.root.Application;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class Admin extends Controller {

    public static String url() {
        return controllers.root.admin.routes.Admin.get().url();
    }

    private static final Map<String,String> BACK = GENERATE_BACK_LINK();

    private static Map<String,String> GENERATE_BACK_LINK() {
        Map<String, String> aMap = new LinkedHashMap<String, String>();
        aMap.put("Back", Application.url());
        return Collections.unmodifiableMap(aMap);
    }

    public static Result get() {
        return AdminResource.get(BACK);
    }
}
