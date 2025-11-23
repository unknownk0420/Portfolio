import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Main {

    @GetMapping("/calculator")
    public String introduction(Model model){
        model.addAttribute("username", "강경운");
        return "Main";
    }

    @GetMapping("/calculatorAdd")
    public String add(Model model, @RequestParam(required = false, defaultValue = "0") int a,
    @RequestParam(required = false, defaultValue = "0") int b){
        int sum = a + b;
        model.addAttribute("addResult", sum);
        return "MainAdd";
    }
}
