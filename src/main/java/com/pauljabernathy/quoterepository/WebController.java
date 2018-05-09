package com.pauljabernathy.quoterepository;

import com.pauljabernathy.quoterepository.db.QuoteRepository;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Controller
public class WebController extends WebMvcConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(Application.class);
    
    @GetMapping("/greeting")
    public String greetingForm(Model model) {
	logger.info("greetingForm(" + model + ")");
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting) {
	logger.info("greetingSubmit(" + greeting + ")");
        return "result";
    }
    
    @GetMapping("/quotes")
    public String quotes(Model model) {
	logger.info("quotes(" + model + ")");
	model.addAttribute("quotes", com.pauljabernathy.quoterepository.db.QuoteRepository.getAllQuotes());
	return "/quotes/list";
    }
    
    @GetMapping("/quoteEntry")
    public String quoteEntryGet(Quote quoteEntry) {
	logger.info("quoteEntry(" + quoteEntry + ")");
	System.out.println("quoteEntry(" + quoteEntry + ")");
	return "quoteEntry";
    }

    @PostMapping("/quoteEntry")
    public String quoteEntryPost(@Valid Quote quote, BindingResult bindingResult) {
	if(bindingResult.hasErrors()) {
	    return "quoteForm";
	} else {
	    QuoteRepository.addQuote(quote);
	    return "redirect:/quotes";
	}
    }
    
    //copied from the other project
    @GetMapping("/quoteForm")
    public String showQuoteForm(QuoteForm quoteForm) {
	System.out.println("showQuoteForm(" + quoteForm + ")");
	return "quoteForm";
    }
    
    @PostMapping("/quoteForm")
    public String checkQuoteInfo(@Valid QuoteForm quoteForm, BindingResult bindingResult) {
	System.out.println("checkQuoteInfo(" + quoteForm + ", " + bindingResult + ")");
	if(bindingResult.hasErrors()) {
	    return "quoteForm";
	} else {
	    return "redirect:/results";
	}
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }
}
