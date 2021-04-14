package cn.edu.nju.software.iot.service;

public interface IntelligentSceneService {
    public String[] getLeaveHomeLampIds();
    public void setLeaveHomeLampIds(String[] lampIds);
    public String[] getReturnHomeLampIds();
    public void setReturnHomeLampIds(String[] lampIds);
    public String[] getPartyLampIds();
    public void setPartyLampIds(String[] lampIds);
    public void leaveHome();
    public void returnHome();
    public boolean getIsParty();
    public void setIsParty(boolean isParty);
}
