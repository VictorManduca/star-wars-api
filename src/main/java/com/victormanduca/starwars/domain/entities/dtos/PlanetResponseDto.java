package com.victormanduca.starwars.domain.entities.dtos;

import com.victormanduca.starwars.domain.entities.PlanetEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Objects;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PlanetResponseDto that = (PlanetResponseDto) o;
    return pageSize == that.pageSize && totalElements == that.totalElements && totalPages == that.totalPages && Objects.equals(content, that.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, pageSize, totalElements, totalPages);
  }

  @Override
  public String toString() {
    return "PlanetResponseDto{" + "content=" + content + ", pageSize=" + pageSize + ", totalElements=" + totalElements + ", totalPages=" + totalPages + '}';
  }
}
