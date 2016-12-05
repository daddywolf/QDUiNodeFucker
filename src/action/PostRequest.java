package action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author Post Method
 */
public class PostRequest {

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }


    public static String sendPost(String url, Map<String, String> paramMap) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // conn.setRequestProperty("Charset", "UTF-8");
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());

            // 设置请求属性
            String param = "";
            if (paramMap != null && paramMap.size() > 0) {
                Iterator<String> ite = paramMap.keySet().iterator();
                while (ite.hasNext()) {
                    String key = ite.next();// key
                    String value = paramMap.get(key);
                    param += key + "=" + value + "&";
                }
                param = param.substring(0, param.length() - 1);
            }

            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.err.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }


    public static void main(String[] args) {
        new PostRequest().login("172.20.1.1", "201440703144", "962464");
        System.out.println("---------------------------");
        new PostRequest().online("172.20.1.1");
        System.out.println("---------------------------");
//        new PostRequest().logout();
//        System.out.println("---------------------------");
        new PostRequest().heartBeat("172.20.1.1");
        System.out.println("---------------------------");
    }

    public String login(String serverIP, String userName, String userPwd) {
        System.out.println(serverIP);
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("userName", userName);
        mapParam.put("userPwd", EncyptUtil.getBase64(userPwd));
        mapParam.put("serviceType", "");
        mapParam.put("isQuickAuth", "false");
        mapParam.put("language", "Chinese");
        mapParam.put("browserFinalUrl", "Chinese");
        mapParam.put("userip", null);
        String pathUrl = "http://" + serverIP + "/portal/login.jsp";
        String result = sendPost(pathUrl, mapParam);
        System.out.println(result);
        if (result.contains("上线成功")) {
            online(serverIP);
            heartBeat(serverIP);
            return "上线成功\n";
        } else if (result.contains("用户已经在线")) {
            return "用户已经在线\n";
        } else if (result.contains("用户密码错误")) {
            return "用户密码错误\n";
        } else if (result.contains("E63018")) {
            return "用户不存在或者用户没有申请该服务\n";
        } else if (result.contains("用户正在认证")) {
            return "用户正在认证\n";
        } else {
            return "其他错误\n";
        }
    }

    public void online(String serverIP) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("language", "Chinese");
        mapParam.put("heartbeatCyc", "600000");
        mapParam.put("heartBeatTimeoutMaxTime", "3");
        mapParam.put("userDevPort", "SR6602-Portal-GW-vlan-00-0000@vlan");
        mapParam.put("userStatus", "99");
        mapParam.put("userip", null);
        mapParam.put("serialNo", "-22773");
        String pathUrl = "http://" + serverIP + "/portal/online.jsp";
        String result = sendPost(pathUrl, mapParam);
        System.out.println(result);
    }

    public String logout(String serverIP) {
        Map<String, String> mapParam = new HashMap<String, String>();
        mapParam.put("language", "Chinese");
        mapParam.put("userip", null);
        mapParam.put("serialNo", "-22773");
        String pathUrl = "http://" + serverIP + "/portal/logout.jsp";
        String result = sendPost(pathUrl, mapParam);
        System.out.println(result);
        if (result.contains("下线成功")) {
            return "下线成功\n";
        } else if (result.contains("用户已经下线")) {
            return "用户已下线\n";
        } else {
            return "其它错误\n";
        }
    }

    public void heartBeat(String serverIP) {
        String pathUrl = "http://" + serverIP + "/portal/online_heartBeat.jsp";
        String s = sendGet("http://" + serverIP + "/portal/online_heartBeat.jsp", "heartbeatCyc=600000&heartBeatTimeoutMaxTime=3&language=Chinese&userDevPort=SR6602-Portal-GW-vlan-00-0000@vlan&userStatus=99&userip=null&serialNo=-22773");
        System.out.println(s);
    }

}
