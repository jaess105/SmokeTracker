package de.jaess105.SmokeTracker.model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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


  public static SmokeEntry makeEntryNow() {
    // Der pi läuft 2 Stunden nach. Ist zwar hacky, aber besser als die Datenbank um zu schreiben...
    return new SmokeEntry(LocalDateTime.now().plusHours(2));
  }

  public String toCSVLine() {
    return String.format("%s,%s", id, zeitstempel);
  }
}
