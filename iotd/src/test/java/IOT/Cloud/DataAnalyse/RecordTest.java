package IOT.Cloud.DataAnalyse;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import IOT.Cloud.DataAnalyse.entity.Record;
import IOT.Cloud.DataAnalyse.service.RecordService;
/**
 * 
 * @author 周扬
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DataAnalyseApplication.class)
public class RecordTest {

	@Resource
	RecordService recordService;
	@Test
	public void testRecordInsert() {
		Record record = new Record();
		record.setLightId(1);
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
			date=sdf.parse("2021-04-09 14:29:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}	*/
		Date date = new Date();
        record.setRecordTime(date);
        record.setRecordMark("closed");
		recordService.addRecord(record);
	}
	@Test
	public void testRecordFind() {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
			date=sdf.parse("2021-04-09 14:29:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}	*/
		List<Record> records = recordService.findAll();
		for (Record record :records) {
			System.out.println(record.getRecordTime());
		}
	}
	@Test
	public void testRecordFindByLightId() {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
			date=sdf.parse("2021-04-09 14:29:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}	*/
		List<Record> records = recordService.findByLightId(1);
		for (Record record :records) {
			System.out.println(record.getRecordTime());
		}
	}
	@Test
	public void testRecordGetNearestRecordByLightId() {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
			date=sdf.parse("2021-04-09 14:29:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}	*/
		Record record = recordService.getNearestRecordByLightId(1);
		if (record==null)System.out.println("null");
			else System.out.println(record.getRecordTime());
	}
	@Test
	public void testfindSleepTime() {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
			date=sdf.parse("2021-04-09 14:29:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}	*/
		List<Record> records = recordService.findSleepTime();
		for (Record record :records) {
			System.out.println(record.toString());
		}
	}
	@Test
	public void testfindRiseTime() {
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date=null;
        try {
			date=sdf.parse("2021-04-09 14:29:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}	*/
		List<Record> records = recordService.findRiseTime();
		for (Record record :records) {
			System.out.println(record.toString());
		}
	}
}
