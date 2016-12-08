package action;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class OpenBrowser {


    public static void openSelfService() {

//		// 启用cmd运行IE的方式来打开网址。
//		String str = "cmd /c start iexplore http://172.20.1.1/selfservice/login.jsf";
//		try {
//			Runtime.getRuntime().exec(str);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
        // 启用系统默认浏览器来打开网址。
        try {
            URI uri = new URI("http://172.20.1.1/selfservice/login.jsf");
            Desktop.getDesktop().browse(uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
