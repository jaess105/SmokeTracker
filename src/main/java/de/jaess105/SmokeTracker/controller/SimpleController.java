package de.jaess105.SmokeTracker.controller;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import de.jaess105.SmokeTracker.service.ExportService;
import de.jaess105.SmokeTracker.service.SmokeEntryService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
  public String home() {
    return "home";
  }


  /**
   * Es wird ein Redirect innerhalb der html per js getriggert.
   *
   * @param model
   * @return
   */
  @PostMapping("/smoke")
  public String makeSmokeEntry(Model model) {
    final SmokeEntry smokeEntry = smokeEntryService.makeSmokeEntry();
    model.addAttribute("smoke_entry", smokeEntry);
    return "entry_information";
  }

  @GetMapping("/download-csv")
  public ResponseEntity<InputStreamResource> downloadCSV()
      throws IOException {
    InputStreamResource resource = exportService.getInputStreamResource();
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=Smoke_Entry.csv")
        .contentType(MediaType.parseMediaType("text/csv"))
        .body(resource);
  }

  @GetMapping("/show-entrys")
  public String showEntrys(Model model) {
    model.addAttribute("entry_list", smokeEntryService.getSmokeEntrys());
    return "render_entrys";
  }
}
