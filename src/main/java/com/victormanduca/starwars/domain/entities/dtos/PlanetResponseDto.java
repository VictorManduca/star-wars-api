package com.victormanduca.starwars.domain.entities.dtos;

import com.victormanduca.starwars.domain.entities.PlanetEntity;
import org.springframework.data.domain.Page;

import java.util.List;

public class PlanetResponseDto {
  private List<PlanetEntity> content;
  private int pageSize;
  private long totalElements;
  private int totalPages;

  public PlanetResponseDto(Page<PlanetEntity> planetPage) {
    this.content = planetPage.getContent();
    this.pageSize = planetPage.getSize();
    this.totalElements = planetPage.getTotalElements();
    this.totalPages = planetPage.getTotalPages();
  }

  public List<PlanetEntity> getContent() {
    return content;
  }

  public void setContent(List<PlanetEntity> content) {
    this.content = content;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }
}
