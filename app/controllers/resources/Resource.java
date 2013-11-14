package controllers.resources;

public class Resource {

    public static String BACK_LINK(final String context) {
        final String ret = context.substring(0, context.lastIndexOf("/"));
        if (ret.equals("")) {
            return "/";
        } else {
            return ret;
        }
    }
}
