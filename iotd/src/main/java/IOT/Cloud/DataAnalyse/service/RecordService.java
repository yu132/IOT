package IOT.Cloud.DataAnalyse.service;

import java.util.Date;
import java.util.List;

import IOT.Cloud.DataAnalyse.entity.Record;
/**
 * 
 * @author 周扬
 *
 */
public interface RecordService {

	public Record addRecord(Record record);
    public List<Record> findAll();
    public List<Record> findByLightId(Integer lightId);
    public Record getNearestRecordByLightId(Integer lightId);
    public void checkLight();
    public List<Record> findSleepTime();
    public List<Record> findRiseTime();
    public Date changeDate(Date oriDate,Integer dHour,Integer dMinute,Integer dSecond);
}
