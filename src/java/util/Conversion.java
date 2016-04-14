/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.*;

/**
 *
 * @author nick
 */
public class Conversion {

    public static String getDatabaseString(String str) {
        if (str == null) {
            return "-";
        }
        StringBuilder tmp = new StringBuilder();
        int iFrom = 0;
        int iTo = 0;
        while (iTo >= 0) {
            iTo = str.indexOf("'", iFrom);
            tmp.append(str.substring(iFrom, iTo));
            tmp.append("`");
            iTo = iFrom;
        }
        tmp.append(str.substring(iFrom, str.length()));
        for (int i = tmp.length() - 1; i >= 0; i--) {
            if (tmp.charAt(i) == '"') {
                tmp.deleteCharAt(i);
            }
        }
        return tmp.toString();
    }

    public static List<String> tokenizeString(String toTokenize, String separator) {

        StringTokenizer tokenizer;
        List<String> result = new ArrayList<>();

        if (toTokenize != null) {
            tokenizer = new StringTokenizer(toTokenize, separator);
            while (tokenizer.hasMoreTokens()) {
                result.add((String) tokenizer.nextToken());
            }
        }

        return result;

    }

    public static String html2xml(String html) {
        String[] tag = {"amp", "euml", "ndash", "rdquo", "ldquo", "dollar", "quot", "rsquo", "percnt", "nbsp", "raquo", "quot", "Ugrave", "Uacute", "ugrave", "uacute", "Ouml", "Ograve", "Oacute", "ograve", "oacute", "Ntilde", "Iuml", "Igrave", "Iacute", "iuml", "igrave", "iacute", "Euml", "Egrave", "Eacute", "euml", "egrave", "eacute", "aacute", "agrave", "auml", "Aacute", "Agrave", "Auml"};
        String[] xml = {"38", "235", "173", "39", "39", "36", "34", "39", "37", "160", "187", "34", "217", "218", "249", "250", "214", "210", "211", "242", "243", "209", "207", "204", "205", "239", "236", "237", "203", "200", "201", "235", "232", "233", "225", "224", "228", "193", "192", "196"};
        for (int i = 0; i < tag.length; i++) {
            html = replaceAll(html, "&" + tag[i], "&#" + xml[i]);
        }

        return html;
    }
    
    public static String chr2xml(String html) {
        String[] tag = {"ë", "­", "$", "%", "»", "Ù", "Ú", "ù", "ú", "Ö", "Ò", "Ó", "ò", "ó", "Ñ", "Ï", "Ì", "Í", "ï", "ì", "í", "Ë", "È", "É", "ë", "è", "é", "á", "à", "ä", "Á", "À", "Ä"};
        String[] xml = {"235", "173", "36", "37", "187", "217", "218", "249", "250", "214", "210", "211", "242", "243", "209", "207", "204", "205", "239", "236", "237", "203", "200", "201", "235", "232", "233", "225", "224", "228", "193", "192", "196"};
        for (int i = 0; i < tag.length; i++) {
            html = replaceAll(html, tag[i], "&#" + xml[i] + ";");
        }

        return html;
    }
    
    public static String extra2xml(String html) {

        /*  &--> &amp; */
        html = replaceAll(html, "&#", "@@");
        html = replaceAll(html, "&", "&#38;");
        html = replaceAll(html, "@@", "&#");
        return html;
    }
    
    public static String replaceAll(String sTxt, String sOldTag, String sNewTag) {

        String newText = "";
        int pos;
        int lastpos = 0;
        while ((pos = sTxt.indexOf(sOldTag, lastpos)) != -1) {
            newText += sTxt.substring(lastpos, pos) + sNewTag;
            lastpos = pos + sOldTag.length();
        }
        newText += sTxt.substring(lastpos, sTxt.length());
        return newText;

    }

}
