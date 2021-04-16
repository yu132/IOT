package IOT.Cloud.DataAnalyse.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import IOT.Cloud.DataAnalyse.entity.ResponseResult;
import IOT.Cloud.DataAnalyse.service.RecordService;
import IOT.Cloud.DataAnalyse.entity.Record;
/**
 * 
 * @author 周扬
 *
 */
@Controller
class RecordController {

	@Resource
	RecordService recordService;
	
	@ResponseBody
	@RequestMapping("/record/findAll")
	public ResponseResult<List<Record> > findAll(HttpSession session){
		List<Record> records = recordService.findAll();
        ResponseResult<List<Record> > rs = new ResponseResult<List<Record> >();
        rs.setState(0);
        rs.setMessage("success");
        rs.setData(records);
		return rs;
	}
	
	@ResponseBody
	@RequestMapping(value="/getInfo/{sts}")//灯具id_无线网使用情况_声音大小_灯具开关     例如：1_0_100_0
	public String getRes(@PathVariable("sts") String sts,HttpSession session){
		String[] str = sts.split("_");
		Record record = new Record();
		record.setLightId(Integer.parseInt(str[0]));
		if (str[3].equals("0"))record.setRecordMark("closed");
			else record.setRecordMark("open");
		record.setRecordTime(new Date());
		recordService.addRecord(record);
		return "success";
	}
	
	@RequestMapping("/index")
	public String ToIndexPage(HttpSession session){
		return "index";
	}
	@ResponseBody
	@RequestMapping("/record/findSleepTime")
	public ResponseResult<List<Record> > findSleepTime(HttpSession session){
		List<Record> records = recordService.findSleepTime();
        ResponseResult<List<Record> > rs = new ResponseResult<List<Record> >();
        rs.setState(0);
        rs.setMessage("success");
        rs.setData(records);
		return rs;
	}
	
	@ResponseBody
	@RequestMapping("/record/findRiseTime")
	public ResponseResult<List<Record> > findRiseTime(HttpSession session){
		List<Record> records = recordService.findRiseTime();
        ResponseResult<List<Record> > rs = new ResponseResult<List<Record> >();
        rs.setState(0);
        rs.setMessage("success");
        rs.setData(records);
		return rs;
	}
}
