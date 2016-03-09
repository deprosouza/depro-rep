package br.com.depro.mugetsu.carga.test;

public class RenoveHTML {

    public static String removeHTML(String htmlString)
    {
          // Remove HTML tag from java String    
        String noHTMLString = htmlString.replaceAll("\\<.*?\\>", "");

        // Remove Carriage return from java String
        noHTMLString = noHTMLString.replaceAll("\r", "<br/>");

        // Remove New line from java string and replace html break
        noHTMLString = noHTMLString.replaceAll("\n", " ");
        noHTMLString = noHTMLString.replaceAll("\'", "&#39;");
        noHTMLString = noHTMLString.replaceAll("\"", "&quot;");
        return noHTMLString;
    }
    
    public static void main(String[] args) {

    String strHTML= "<html>"+
                    "<head>"+
                    "<title>Convert HTML to Text String</title>"+
                    "</head>"+

                    "<body>"+
                    "This is HTML String of java's source code  \"my program\""+
                    "</body>"+
                    "</html>";

        String stringWithoutHTML=removeHTML(strHTML);

        System.out.println(stringWithoutHTML);
    }
}
