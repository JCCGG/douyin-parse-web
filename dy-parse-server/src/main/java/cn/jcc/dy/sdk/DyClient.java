package cn.jcc.dy.sdk;

import cn.jcc.dy.pojo.po.VideoInfo;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: dy-parse-server
 * @description: dy client
 * @author: CCC
 * @create: 2022-03-08 13:51
 **/

@Component
public class DyClient {
    @Autowired
    OkHttpClient okHttpClient;

    @Autowired
    VideoInfo videoInfo;

    private String videoApiUrl="https://www.iesdouyin.com/web/api/v2/aweme/iteminfo/?item_ids=";
    private String vedioUrl="https://aweme.snssdk.com/aweme/v1/play/?video_id=";
    private String vid="";
    private String music="";
    private String videoRealUrl="";
    private String bgImage="";

    //    根据短连接获取长连接
    private String getLongUrl(String url) throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(url)
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String logUrl = response.header("Location");
        System.out.println(logUrl);
        return logUrl;

    }

    private boolean getVideoApiInfo(String url) throws IOException {
        String longUrl = this.getLongUrl(url);
        String pattern="(?<=/video/).*?(?=/)";
        Pattern compile = Pattern.compile(pattern);
        Matcher matcher = compile.matcher(longUrl);
        if(matcher.find()){
            String vedioId = matcher.group(0);
            Request request = new Request.Builder()
                    .get()
                    .url(this.videoApiUrl + vedioId)
                    .build();
            Response response = this.okHttpClient.newCall(request).execute();
            System.out.println(response.code());
            if(response.code()==200){
                String jsonStr = response.body().string();
//                System.out.println(jsonStr);
                ObjectMapper mapper = new ObjectMapper();
                JsonNode jsonNode = mapper.readTree(jsonStr);
                if(jsonNode.get("status_code").asInt()==0){
//                    请求接口成功！
                    this.vid = jsonNode.get("item_list").get(0).get("video").get("vid").asText();
                    this.music=jsonNode.get("item_list").get(0).get("music").get("play_url").get("uri").asText();
//                    String  originBgImageLIst= jsonNode.get("item_list").get("video").get("origin_cover").get("url_list");
//                    jsonNode.get("item_list").get("video").get("origin_cover").get("url_list").withArray("")
//                  获取视频封面
                    return true;
                }
            }
        }else{
            throw new NullPointerException("获取视频ID异常！");
        }
        return false;
    }

//获取真实地址
    public VideoInfo getRealVedioUrl(String url) throws Exception {
        if(this.getVideoApiInfo(url)){
            Request request = new Request.Builder()
                    .get()
                    .url(this.vedioUrl + this.vid + "&ratio=720p&line=0")
                    .build();

            Response response = this.okHttpClient.newCall(request).execute();
            String realUrl = response.header("Location");
            this.videoRealUrl=realUrl;
            System.out.println(realUrl);
            return this.getVideoInfo();
        }else{
            throw new Exception("获取视频信息异常！");
        }
    }

//    返回视频实体信息
    private VideoInfo getVideoInfo(){
        this.videoInfo.setBgImage(this.bgImage);
        this.videoInfo.setMusic(this.music);
        this.videoInfo.setVedioUrl(this.videoRealUrl);
        return this.videoInfo;
    }

}
