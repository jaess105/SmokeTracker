package de.jaess105.SmokeTracker.repository;

import de.jaess105.SmokeTracker.model.SmokeEntry;
import java.util.Collection;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface SmokeTrackerRepo extends CrudRepository<SmokeEntry, Long> {

  @Override
  List<SmokeEntry> findAll();
}
