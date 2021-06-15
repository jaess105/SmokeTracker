package de.jaess105.SmokeTracker.model;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class SmokeEntry {

  @Id
  private Long id;
  private final LocalDateTime zeitstempel;

  public static SmokeEntry makeEntryNow(){
    return new SmokeEntry(LocalDateTime.now());
  }

  public String toCSVLine(){
    return String.format("%s,%s",id,zeitstempel);
  }
}
