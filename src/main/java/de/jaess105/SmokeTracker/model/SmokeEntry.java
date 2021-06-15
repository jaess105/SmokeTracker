package de.jaess105.SmokeTracker.model;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor()
@NoArgsConstructor
public class SmokeEntry {

  @Id
  private Long id;
  private LocalDateTime zeitstempel;

  private SmokeEntry(LocalDateTime zeitstempel) {
    this.zeitstempel = zeitstempel;
  }

  public static SmokeEntry makeEntryNow(){
    return new SmokeEntry(LocalDateTime.now());
  }

  public String toCSVLine(){
    return String.format("%s,%s",id,zeitstempel);
  }
}
