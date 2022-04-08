package com.example.demo.oa;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class DoCreateWorkFlowReq {

  /**
   * workflowId
   */
  @JsonProperty("workflowId")
  private String workflowId;
  /**
   * email
   */
  @JsonProperty("email")
  private String email;
  /**
   * pjssgs
   */
  @JsonProperty("pjssgs")
  private String pjssgs;
  /**
   * ywl
   */
  @JsonProperty("ywlx")
  private String ywlx;
  /**
   * fplx
   */
  @JsonProperty("fplx")
  private String fplx;
  /**
   * fkrmc
   */
  @JsonProperty("fkrmc")
  private String fkrmc;
  /**
   * kpnr
   */
  @JsonProperty("kpnr")
  private String kpnr;
  /**
   * ywxgs
   */
  @JsonProperty("ywxgs")
  private String ywxgs;
  /**
   * khsh
   */
  @JsonProperty("khsh")
  private String khsh;
  /**
   * dzdh
   */
  @JsonProperty("dzdh")
  private String dzdh;
  /**
   * khxjzh
   */
  @JsonProperty("khxjzh")
  private String khxjzh;
  /**
   * jemx
   */
  @JsonProperty("jemx")
  private List<JemxDTO> jemx;

  public String getWorkflowId() {
    return workflowId;
  }

  public void setWorkflowId(String workflowId) {
    this.workflowId = workflowId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPjssgs() {
    return pjssgs;
  }

  public void setPjssgs(String pjssgs) {
    this.pjssgs = pjssgs;
  }

  public String getYwlx() {
    return ywlx;
  }

  public void setYwlx(String ywlx) {
    this.ywlx = ywlx;
  }

  public String getFplx() {
    return fplx;
  }

  public void setFplx(String fplx) {
    this.fplx = fplx;
  }

  public String getFkrmc() {
    return fkrmc;
  }

  public void setFkrmc(String fkrmc) {
    this.fkrmc = fkrmc;
  }

  public String getKpnr() {
    return kpnr;
  }

  public void setKpnr(String kpnr) {
    this.kpnr = kpnr;
  }

  public String getYwxgs() {
    return ywxgs;
  }

  public void setYwxgs(String ywxgs) {
    this.ywxgs = ywxgs;
  }

  public String getKhsh() {
    return khsh;
  }

  public void setKhsh(String khsh) {
    this.khsh = khsh;
  }

  public String getDzdh() {
    return dzdh;
  }

  public void setDzdh(String dzdh) {
    this.dzdh = dzdh;
  }

  public String getKhxjzh() {
    return khxjzh;
  }

  public void setKhxjzh(String khxjzh) {
    this.khxjzh = khxjzh;
  }

  public List<JemxDTO> getJemx() {
    return jemx;
  }

  public void setJemx(List<JemxDTO> jemx) {
    this.jemx = jemx;
  }

  public static class JemxDTO {

    /**
     * kpje
     */
    @JsonProperty("kpje")
    private String kpje;
    /**
     * bz
     */
    @JsonProperty("bz")
    private String bz;

    public String getKpje() {
      return kpje;
    }

    public void setKpje(String kpje) {
      this.kpje = kpje;
    }

    public String getBz() {
      return bz;
    }

    public void setBz(String bz) {
      this.bz = bz;
    }
  }
}
