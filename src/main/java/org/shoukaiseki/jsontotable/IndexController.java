package org.shoukaiseki.jsontotable;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import org.apache.log4j.Logger;
import org.shoukaiseki.utils.StringKit;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@Scope("session")
public class IndexController{

    String jsonstr="";

    Logger log=Logger.getLogger("org.shoukaiseki");

    String errorstr="";


    String htmlstr="";

    int columnsize=3;

    @RequestMapping(value="/main/index")
    public ModelAndView main(ModelMap model) {
        ModelAndView mv=new ModelAndView();
        mv.setViewName("main/index");

        model.addAttribute("jsonfrom",jsonstr);
        model.addAttribute("errorstr",errorstr);
        model.addAttribute("htmlstr",htmlstr);
        log.debug("jsonfrom="+jsonstr);
        return mv;
    }


    @RequestMapping(value="/main/submit")
    public ModelAndView submit(@RequestParam(value="jsonfrom", required = false) String jsonfrom,ModelMap model) {
        ModelAndView mv=new ModelAndView();

        if(jsonfrom==null||jsonfrom.isEmpty()){
                this.jsonstr="";
        }else{
            if(jsonfrom.equals(jsonstr)){

            }else{
                this.jsonstr=jsonfrom;
            }
        }

        errorstr="";
        JSONObject jsonObject =null;
        htmlstr="";

        if(!StringKit.INSTANCE.isBlank(jsonstr)){

            try {
                jsonObject = JSONObject.parseObject(jsonstr);

                htmlstr=jsonObjectToHtml(jsonObject).toString();
            }catch (JSONException e){
               log.error("json.error",e);
               errorstr=e.getMessage();
               e.printStackTrace();
            }

        }



        mv.setViewName("main/index");
        mv.setViewName("redirect:/main/index");
        return mv;
    }


    public StringBuffer jsonArrayToHtml(JSONArray jsonArray){
        StringBuffer sb=new StringBuffer();

//        sb.append("<table>");
        sb.append("<div class=\"table_outside_border table_outside_border_div \">");

        log.debug("JSONArray="+jsonArray);
        if(jsonArray.isEmpty()){
            sb.append("(empty)");
        }

        int i=0;
        for (Object value:jsonArray){
            i++;
            log.debug("value="+value);

            if(value instanceof JSONObject) {
//                sb.append(jsonObjectToHtml((JSONObject) value));
                sb.append(fieldToHtml(String.valueOf(i),value));
            }else if(value instanceof JSONArray){
                sb.append(jsonArrayToHtml((JSONArray) value));
            }else{
                sb.append(fieldToHtml(value));

            }
        }
        sb.append("</div>");
//        sb.append("</table>");
        return sb;
    }

    public StringBuffer jsonObjectToHtml(JSONObject jsonObject){
        StringBuffer sb=new StringBuffer();

//        sb.append("<table>");
        sb.append("<div class=\"table_outside_border table_outside_border_div \">");

        log.debug("JSONObject="+jsonObject);
        Map<String, Object> innerMap = sortChildEnd(jsonObject.getInnerMap());
        if(innerMap==null||innerMap.isEmpty()){
            sb.append("(empty)");
        }else{
            int columnsizeTemp=0;
            for (Map.Entry<String,Object> entry:innerMap.entrySet()){
                String key=entry.getKey();
                Object value = entry.getValue();

                if(value instanceof JSONObject){

                }else{

                }
                sb.append(fieldToHtml(key,value));
            }
        }
        sb.append("</div>");
//        sb.append("</table>");
        return sb;
    }

    public StringBuffer fieldToHtml(Object value){
        StringBuffer sb=new StringBuffer();
        sb.append("<table>");
        if(value==null){
            sb.append("<tr>\n<td>(null)</td></td>");
        }else{
            if(value instanceof JSONObject) {
                sb.append("<tr>\n<td>");
                sb.append(jsonObjectToHtml((JSONObject) value));
                sb.append("</td></tr>\n");
            }else if(value instanceof JSONArray){
                sb.append("<tr>\n<td>");
                sb.append(jsonArrayToHtml((JSONArray) value));
                sb.append("</td></tr>\n");
            }else{
                sb.append("<tr>\n<td>").append(value).append("</td></tr>\n");
            }
        }
        sb.append("<tr>\n").append("<td>");
        sb.append("</td>").append("</tr>\n");
        sb.append("</table>");

        return sb;

    }

    public StringBuffer fieldToHtml(String key,Object value){
        StringBuffer sb=new StringBuffer();
        sb.append("<table>");
        sb.append("<tr>\n").append("<td>").append(key).append(":");
        sb.append("</td>").append("</tr>\n");
        if(value==null){
            sb.append("<tr>\n<td>(null)</td></td>");
        }else{
            if(value instanceof JSONObject) {
                sb.append("<tr>\n<td>");
                sb.append(jsonObjectToHtml((JSONObject) value));
                sb.append("</td></tr>\n");
            }else if(value instanceof JSONArray){
                sb.append("<tr>\n<td>");
                sb.append(jsonArrayToHtml((JSONArray) value));
                sb.append("</td></tr>\n");
            }else{
                sb.append("<tr>\n<td>").append(value).append("</td></tr>\n");
            }
        }
        sb.append("<tr>\n").append("<td>");
        sb.append("</td>").append("</tr>\n");
        sb.append("</table>");

        return sb;

    }

    public Map<String, Object> sortChildEnd(Map<String, Object> innerMap){
       HashMap<String,Object> map= Maps.newLinkedHashMap();
        HashMap<String,Object> childMap= Maps.newLinkedHashMap();

        for (Map.Entry<String,Object> entry:innerMap.entrySet()){
            String key=entry.getKey();
            Object value = entry.getValue();
            if(value!=null){
                log.debug("key="+key+",value="+value+",value.class="+value.getClass());
            }
            if(value!=null&&(value instanceof JSONObject||value instanceof JSONArray)){
                childMap.put(key,value);
            }else{
                map.put(key,value);
            }
        }

        map.putAll(childMap);
        log.debug("map="+map);

       return map;
    }

}

