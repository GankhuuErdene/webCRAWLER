import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;

public class webCRWLER {

    private HashSet<String> links;

    public webCRWLER() {
        links = new HashSet<String>();
    }
    public void getPageLinks(String URL) {
        //4. URL oruulsan esexiig shalgana.
        if (!links.contains(URL)) {
            try {
                if (links.add(URL)) {
                    System.out.println(URL);
                }
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage = document.select("a[href]");
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
    public static void main(String[] args) {
        new webCRWLER().getPageLinks("https://www.caak.mn/");
    }

}