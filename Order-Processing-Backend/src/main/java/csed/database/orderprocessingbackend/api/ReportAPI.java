package csed.database.orderprocessingbackend.api;

import csed.database.orderprocessingbackend.service.ReportService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@CrossOrigin
public class ReportAPI {

    @GetMapping("report/{type}")
    public String reports(@PathVariable String type) throws SQLException {
        ReportService report = new ReportService();
        switch (type){
            case "sales":
                return report.sales_report();
            case "books":
                return report.top_books_report();
            case "customer":
                return report.top_customers_report();
            default:
                return "Unknown Target";
        }
    }
}
