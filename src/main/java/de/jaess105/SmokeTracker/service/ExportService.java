package de.jaess105.SmokeTracker.service;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import de.jaess105.SmokeTracker.repository.SmokeTrackerRepo;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportService {

  private final SmokeTrackerRepo smokeTrackerRepo;


  public InputStreamResource getInputStreamResource() {
    return new InputStreamResource(new ByteArrayInputStream(allSmokeEntrysAsCSVString().getBytes()));
  }

  private Writer getCSVFile() {
    Writer fileWriter = getFileWriter();
    CSVPrinter csvPrinter = getCsvPrinter(fileWriter).orElseThrow();
    tryPrintingTo(allSmokeEntrysAsCSVString(), csvPrinter);
    return fileWriter;
  }

  private void tryPrintingTo(String smokeRecords, CSVPrinter csvPrinter) {
    try {
      csvPrinter.printRecords(smokeRecords);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Optional<CSVPrinter> getCsvPrinter(Writer fileWriter) {
    try {
      final CSVPrinter csvPrinter = new CSVPrinter(fileWriter, CSVFormat.EXCEL);
      return Optional.of(csvPrinter);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Optional.empty();
  }

  private Writer getFileWriter() {
    try {
      return new FileWriter("smoke.csv");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return FileWriter.nullWriter();
  }


  private String allSmokeEntrysAsCSVString() {
    final Collection<SmokeEntry> allEntrys = smokeTrackerRepo.findAll();
    return convertToCSV(allEntrys);
  }

  private String convertToCSV(Collection<SmokeEntry> data) {
    return data.stream().map(SmokeEntry::toCSVLine).collect(Collectors.joining(",\n"));
  }
}
