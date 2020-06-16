package waw.anotherworld.demon.common.util;

public class UtilString {

    // html 변환
    public static String converterHTML(String data){
        String content = data;
        content = content.replace("&amp;", "&");
        content = content.replace("&ldquo;", "\"");
        content = content.replace("&rdquo;", "\"");
        content = content.replace("&lt;", "<");
        content = content.replace("&gt;", ">");
        content = content.replace("&quot;", "\"");
        content = content.replace("&#39;", "'");
        content = content.replace("&#40;", "(");
        content = content.replace("&#41;", ")");
        content = content.replace("&#x2F;", "/");
        content = content.replace("&nbsp;", " ");
        content = content.replace("&bull;", "•");
        return content;
    };
}
