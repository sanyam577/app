package in.linkedin.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin(origins = "https://www.linkedin.com")
public class SalaryController {

    @GetMapping("/salary")
    public String showForm() {
        return "index"; // Corresponds to an HTML file for input
    }

    @PostMapping(value = "/salary", produces = "application/json")
    @ResponseBody
    public Map<String, String> getSalary(@RequestParam String company, @RequestParam String jobRole) {
        Map<String, String> response = new HashMap<>();
        try {
            String salaryData = getData(company, jobRole);
            response.put("salaryData", salaryData);
        } catch (Exception e) {
            e.printStackTrace();
            response.put("errorMessage", "Error fetching salary data.");
        }
        return response;
    }

private String getData(String companyName, String jobRole) throws IOException {
    StringBuilder br = new StringBuilder();

    String url = "https://www.ambitionbox.com/salaries/" + companyName + "-salaries/" + jobRole;

    // Set the User-Agent header to mimic a web browser request
    Document doc = Jsoup.connect(url)
        .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36")
        .get();
    
    System.out.println(doc.title());
    Elements elements = doc.select(".salaries-wrapper__rows");
    System.out.println();

    String salary;

    for (Element e : elements) {
        salary = e.select(".row-left__salary").text();
        br.append(salary);
    }

    return br.toString();
}

}

//Url:- http://localhost:7777/salary


