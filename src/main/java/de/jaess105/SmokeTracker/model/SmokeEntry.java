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
    return new SmokeEntry(LocalDateTime.now().atZone(ZoneId.of("Europe/Berlin")).toLocalDateTime());
  }

  public String toCSVLine() {
    return String.format("%s,%s", id, zeitstempel);
  }
}
