package de.jaess105.SmokeTracker.service;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import de.jaess105.SmokeTracker.repository.SmokeTrackerRepo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SmokeEntryService {

  private final SmokeTrackerRepo smokeRepo;

  public SmokeEntry makeSmokeEntry() {
    final SmokeEntry smokeEntry = SmokeEntry.makeEntryNow();
    return smokeRepo.save(smokeEntry);
  }


  public List<SmokeEntry> getSmokeEntrys() {
    return smokeRepo.findAll();
  }
}
