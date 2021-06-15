package de.jaess105.SmokeTracker.controller;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import de.jaess105.SmokeTracker.service.ExportService;
import de.jaess105.SmokeTracker.service.SmokeEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SimpleController {

  private final SmokeEntryService smokeEntryService;
  private final ExportService exportService;

  @GetMapping("/")
  public String home(){
    return "home";
  }


  /**
   * Es wird ein Redirect innerhalb der html per js getriggert.
   * @param model
   * @return
   */
  @PostMapping("/smoke")
  public String makeSmokeEntry(Model model){
    final SmokeEntry smokeEntry = smokeEntryService.makeSmokeEntry();
    model.addAttribute("smoke_entry", smokeEntry);
    return "entry_information";
  }

  /*@GetMapping("/toCSV")
  public RedirectView downloadCSV(HttpServletResponse respnse){
    try {
      
      InputStream io = exportService.getCSV().get().;
    }*/

  @GetMapping("/showEntrys")
  public String showEntrys(Model model){
    model.addAttribute("entry_list", smokeEntryService.getSmokeEntrys());
    return "render_entrys";
  }
}
