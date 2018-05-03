
/**
 **/
public class TestSqlLike {


    public static void main(String[] args) {
        String sql=" select * from person where personid like '%hello%' or name = 'asus'";
        String[] strings = formatKeyValue(sql);
        System.out.println("key="+strings[0]);
        System.out.println("value="+strings[1]);
    }


    public static String[] formatKeyValue(String sql){
        String[] strs=new String[2];
        if(sql.toLowerCase().indexOf(" like ")>-1){
            int likeindex = sql.toLowerCase().indexOf("like");
            System.out.println("likeindex="+likeindex);

            String keystr = sql.substring(0,likeindex).trim();
            System.out.println("keystr="+keystr);
            if(keystr.indexOf(" ")>-1){
                String key = keystr.substring(keystr.lastIndexOf(" ")).trim();
                strs[0]=key;
                System.out.println("key="+key);
            }

            String valuestr=sql.substring(likeindex+4).trim()+" ";
            if(valuestr.indexOf("' ")>-1){
                String value = valuestr.substring(0, valuestr.indexOf("' "));
                value=value.trim().substring(1, value.trim().length());
                System.out.println("value="+value);
                strs[1]=value;
            }
            System.out.println("valuestr="+valuestr);
        }
        ;


        return strs;
    }
}

