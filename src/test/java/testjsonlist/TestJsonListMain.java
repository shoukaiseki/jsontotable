package testjsonlist;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import testjsonlist.model.WorkorderModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 **/
public class TestJsonListMain {


    public static void main(String[] args) {
        List<WorkorderModel> wos=new ArrayList<>();
        WorkorderModel wo=new WorkorderModel();
        wo.setWonum("WO1001");
        wo.setRetb(true);
        wos.add(wo);

        wo=new WorkorderModel();
        wo.setWonum("WO1002");
        wo.setRetb(true);
        wos.add(wo);

        String jsonString = JSONObject.toJSONString(wos);
        System.out.println("发送过来的json="+jsonString);

        ArrayList works = JSON.parseObject(jsonString,ArrayList.class);
        for (Object obj:works){
            System.out.println("obj="+obj);
            WorkorderModel wm=JSON.parseObject(obj.toString(),WorkorderModel.class);
            System.out.println("wm.wonum="+wm.getWonum()+",wm.retb="+wm.isRetb());
        };

    }

}
