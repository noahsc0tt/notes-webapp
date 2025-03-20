package uk.ac.ucl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import uk.ac.ucl.exceptions.MarkdownParseException;

// Converts Markdown text to HTML through regex matching and replacement

public class MarkdownConverter {
    
    public static String convertToHtml(String markdown) {
        if (markdown == null) {
            return "";
        }
        
        try
        {
            String html = markdown.replaceAll("\n", "<br>");
            html = processImages(html);
            html = processLinks(html);
            
            return html;
        }
        catch (Exception e) { throw new MarkdownParseException("Error converting markdown to HTML", e); }
    }
    
    private static String processImages(String html)
    {
        Pattern imagePattern = Pattern.compile("!\\[(.*?)\\]\\((.*?)\\)");
        Matcher imageMatcher = imagePattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        
        while (imageMatcher.find())
        {
            String alt = escapeQuotes(imageMatcher.group(1));
            String src = normalizePath(imageMatcher.group(2));
            
            imageMatcher.appendReplacement(sb, createImageTag(src, alt));
        }
        
        imageMatcher.appendTail(sb);
        return sb.toString();
    }
    
    private static String normalizePath(String src)
    {
        if (!src.startsWith("http") && !src.startsWith("/")) src = "/" + src;
        return escapeQuotes(src);
    }
    
    private static String createImageTag(String src, String alt)
    {
        return "<img src=\"" + src + "\" alt=\"" + alt + "\" style=\"max-width:50%; height:auto;\">";
    }
    
    private static String processLinks(String html)
    {
        Pattern linkPattern = Pattern.compile("\\[(.*?)\\]\\((.*?)\\)");
        Matcher linkMatcher = linkPattern.matcher(html);
        StringBuffer sb = new StringBuffer();
        
        while (linkMatcher.find())
        {
            String text = linkMatcher.group(1);
            String url = escapeQuotes(linkMatcher.group(2));
            
            linkMatcher.appendReplacement(sb, createLinkTag(url, text));
        }
        
        linkMatcher.appendTail(sb);
        return sb.toString();
    }
    
    private static String createLinkTag(String url, String text)
    {
        return "<a href=\"" + url + "\" target=\"_blank\">" + text + "</a>";
    }
    
    private static String escapeQuotes(String text) { return text.replaceAll("\"", "&quot;"); }
}