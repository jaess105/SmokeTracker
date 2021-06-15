package de.jaess105.SmokeTracker.repository;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import java.util.List;
import org.springframework.data.repository.CrudRepository;


public interface SmokeTrackerRepo extends CrudRepository<SmokeEntry, Long> {

  @Override
  List<SmokeEntry> findAll();
}
