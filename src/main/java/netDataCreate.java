/**
 * Created by mike on 17-1-10.
 */

import com.mike.EmailTools.EmailTools;
import com.mike.EmailTools.netTools;

public class netDataCreate {
    public static void main(String[] args)
    {

        System.out.println("test");
//        netTools.sendEmail("allmy163mail2@163.com","allmy163mail2@163.com",
//                "there is no subject in test", "xxxxxxxxxxxxx", "pop.163.com",
//                "/home/mike/Desktop/aaa.txt");
        EmailTools.send163();
        System.out.println("end");
    }
}
