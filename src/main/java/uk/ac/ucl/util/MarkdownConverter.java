package uk.ac.ucl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MarkdownConverter {
    
    public static String convertToHtml(String markdown) {
        if (markdown == null) {
            return "";
        }
        
        // New lines
        String html = markdown.replaceAll("\n", "<br>");
        
        /*// Headers
        html = html.replaceAll("(?m)^# (.*?)$", "<h1>$1</h1>");
        html = html.replaceAll("(?m)^## (.*?)$", "<h2>$1</h2>");
        html = html.replaceAll("(?m)^### (.*?)$", "<h3>$1</h3>");
        
        // Bold
        html = html.replaceAll("\\*\\*(.*?)\\*\\*", "<strong>$1</strong>");
        
        // Italic
        html = html.replaceAll("\\*(.*?)\\*", "<em>$1</em>");
        */
        
        
        // Links
        Pattern linkPattern = Pattern.compile("\\[(.*?)\\]\\((.*?)\\)");
        Matcher linkMatcher = linkPattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        while (linkMatcher.find()) {
            String text = linkMatcher.group(1);
            String url = linkMatcher.group(2);
            url = url.replaceAll("\"", "&quot;");
            linkMatcher.appendReplacement(sb,
                    "<a href=\"" + url + "\" target=\"_blank\">" + text + "</a>");
        }
        linkMatcher.appendTail(sb);
        html = sb.toString();
        
        // Images
        Pattern imagePattern = Pattern.compile("!\\[(.*?)\\]\\((.*?)\\)");
        Matcher imageMatcher = imagePattern.matcher(html);
        sb = new StringBuffer();
        while (imageMatcher.find()) {
            String alt = imageMatcher.group(1);
            String src = imageMatcher.group(2);
            
            // Ensure the path is relative to context root
            if (!src.startsWith("http") && !src.startsWith("/")) {
                src = "/" + src;
            }
            
            src = src.replaceAll("\"", "&quot;");
            alt = alt.replaceAll("\"", "&quot;");
            imageMatcher.appendReplacement(sb,
                    "<img src=\"" + src + "\" alt=\"" + alt + "\" style=\"max-width:100%;\">");
        }
        imageMatcher.appendTail(sb);
        html = sb.toString();
        
        return html;
    }
}