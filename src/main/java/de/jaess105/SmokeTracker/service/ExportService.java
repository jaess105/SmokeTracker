package de.jaess105.SmokeTracker.service;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import de.jaess105.SmokeTracker.repository.SmokeTrackerRepo;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExportService {

  private final SmokeTrackerRepo smokeTrackerRepo;

  public String getSmokeEntrysAsCSV( ){
    final Collection<SmokeEntry> allEntrys = smokeTrackerRepo.findAll();
    return convertToCSV(allEntrys);
  }

  private String convertToCSV(Collection<SmokeEntry> data){
    return data.stream().map(SmokeEntry::toCSVLine).collect(Collectors.joining(",\n"));
  }
}
