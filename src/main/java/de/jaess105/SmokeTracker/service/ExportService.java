package de.jaess105.SmokeTracker.service;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import de.jaess105.SmokeTracker.repository.SmokeTrackerRepo;
import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportService {

  private final SmokeTrackerRepo smokeTrackerRepo;


  /**
   * Konveriert die Einträge aus dem Smoke Entry repo in einen joined csv string mit new Line und
   * gibt diesen als {@link InputStreamResource} zurück.
   *
   * @return CSV String als {@link InputStreamResource}.
   */
  public InputStreamResource smokeEntrysToCSVasISR() {
    return new InputStreamResource(
        new ByteArrayInputStream(allSmokeEntrysAsCSVString().getBytes()));
  }


  private String allSmokeEntrysAsCSVString() {
    final Collection<SmokeEntry> allEntrys = smokeTrackerRepo.findAll();
    return convertToCSV(allEntrys);
  }

  private String convertToCSV(Collection<SmokeEntry> data) {
    return data.stream().map(SmokeEntry::toCSVLine).collect(Collectors.joining(",\n"));
  }
}
