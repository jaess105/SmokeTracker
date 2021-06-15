package de.jaess105.SmokeTracker.service;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import de.jaess105.SmokeTracker.repository.SmokeTrackerRepo;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportService {

  private final SmokeTrackerRepo smokeTrackerRepo;

  public Optional<CSVParser> getCSV() {
    try {
      return Optional.of(CSVParser.parse(allSmokeEntrysAsCSVString(), CSVFormat.EXCEL));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  private String allSmokeEntrysAsCSVString() {
    final Collection<SmokeEntry> allEntrys = smokeTrackerRepo.findAll();
    return convertToCSV(allEntrys);
  }

  private String convertToCSV(Collection<SmokeEntry> data) {
    return data.stream().map(SmokeEntry::toCSVLine).collect(Collectors.joining(",\n"));
  }
}
