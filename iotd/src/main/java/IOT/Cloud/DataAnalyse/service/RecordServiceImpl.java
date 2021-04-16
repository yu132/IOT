package IOT.Cloud.DataAnalyse.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IOT.Cloud.DataAnalyse.entity.Record;
import IOT.Cloud.DataAnalyse.mapper.RecordRepository;
/**
 * 
 * @author 周扬
 *
 */
@Service(value = "recordService")
public class RecordServiceImpl implements RecordService{
	
	private final RecordRepository recordRepository;
	 
    @Autowired
    RecordServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public Record addRecord(Record record) {
    	return recordRepository.save(record);
    }

    public List<Record> findAll() {
        return recordRepository.findAll();
    }
    
    public List<Record> findByLightId(Integer lightId){
    	return recordRepository.findByLightId(lightId);
    }
    public Record getNearestRecordByLightId(Integer lightId) {
    	List<Record> records = recordRepository.findByLightId(lightId);
    	if (records.isEmpty())return null;
    	Record Ans = records.get(0);
    	for (Record record: records) {
    		if (record.getRecordTime().compareTo(Ans.getRecordTime())>0)Ans = record;
    	}
    	return Ans;
    }
    /**
     * 根据用户家中无线网的使用情况、声音大小以及灯具开关来自动判断是否需要改变灯具的状态。
     */
    public void checkLight() {
    	
    }

    public Date changeDate(Date oriDate,Integer dHour,Integer dMinute,Integer dSecond) {
    	Calendar calendar = Calendar.getInstance();  
    	calendar.setTime(oriDate);  
    	calendar.set(Calendar.HOUR_OF_DAY,dHour);
    	calendar.set(Calendar.MINUTE,dMinute);
    	calendar.set(Calendar.SECOND,dSecond);
    	Date date= calendar.getTime();
    	return date;
    }
    /**
     * 查询每天0-5点的第一次关灯
     */
    public List<Record> findSleepTime(){
    	List<Record> Ans = new ArrayList<Record>();
    	List<Record> OriRecords = findAll();
    	Record lastRecord = null;
    	boolean rep = false;
    	for (Record record:OriRecords) {
    		if (record.getRecordMark().equals("open"))continue;
    		if (rep==false && lastRecord!=null) {
				rep = true;
				Ans.add(lastRecord);
			}
    		Date date = record.getRecordTime();
    		Date rDate = changeDate(date,5,0,0);
    		if (date.compareTo(rDate)>0) continue;
    		lastRecord = record;
    		rep = false;
    	}
    	if (rep==false && lastRecord!=null) {
			rep = true;
			Ans.add(lastRecord);
		}
    	return Ans;
    }
    /**
     * 6点后第一次开灯时间
     */
    public List<Record> findRiseTime(){
    	List<Record> Ans = new ArrayList<Record>();
    	List<Record> OriRecords = findAll();
    	Record lastRecord = null;
    	boolean rep = false;
    	for (Record record:OriRecords) {
    		if (record.getRecordMark().equals("closed"))continue;
    		if (rep==false && lastRecord!=null) {
				rep = true;
				Ans.add(lastRecord);
			}
    		Date date = record.getRecordTime();
    		Date rDate = changeDate(date,6,0,0);
    		if (date.compareTo(rDate)<0)continue;
    		lastRecord = record;
    		rep = false;
    	}
    	if (rep==false && lastRecord!=null) {
			rep = true;
			Ans.add(lastRecord);
		}
    	return Ans;
    }
}
