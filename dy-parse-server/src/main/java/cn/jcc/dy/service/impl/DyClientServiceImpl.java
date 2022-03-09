package cn.jcc.dy.service.impl;

import cn.jcc.dy.pojo.po.VideoInfo;
import cn.jcc.dy.pojo.vo.ResultData;
import cn.jcc.dy.sdk.DyClient;
import cn.jcc.dy.service.DyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.SocketException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: dy-parse-server
 * @description: client impl
 * @author: CCC
 * @create: 2022-03-08 13:50
 **/

@Service
public class DyClientServiceImpl implements DyClientService {
    @Autowired
    private DyClient dyClient;
    @Override
    public ResultData<VideoInfo> getVideoInfo(String urlText) {
        ResultData<VideoInfo> videoInfoResultData = new ResultData<>();
        if ("".equals(urlText)||urlText==null){
            videoInfoResultData.setMsg("输入的地址不能为空！");
            videoInfoResultData.setCode(-1);
            return videoInfoResultData;
        }

        String pattern="(ht|f)tp(s?)\\:\\/\\/[0-9a-zA-Z]([-.\\w]*[0-9a-zA-Z])*(:(0-9)*)*(\\/?)([a-zA-Z0-9\\-\\.\\?\\,\\'\\/\\\\\\+&%\\$#_]*)?";
        try {
            Pattern compile = Pattern.compile(pattern);
            Matcher matcher = compile.matcher(urlText);
            if(matcher.find()) {
                String url = matcher.group(0);
                System.out.println(url);
                VideoInfo realVedioUrl = dyClient.getRealVedioUrl(url);
                videoInfoResultData.setCode(0);
                videoInfoResultData.setMsg("解析成功！");
                videoInfoResultData.setData(realVedioUrl);
                return videoInfoResultData;
            }
        }catch(SocketException e){
            e.printStackTrace();
            videoInfoResultData.setMsg("网络异常，可能是被封了！");
            videoInfoResultData.setCode(-1);
            return videoInfoResultData;
        }catch (Exception e) {
            e.printStackTrace();
            videoInfoResultData.setMsg("解析异常！");
            videoInfoResultData.setCode(-1);
            return videoInfoResultData;
        }
        videoInfoResultData.setCode(-1);
        videoInfoResultData.setMsg("解析失败，没有返回数据！");
        return videoInfoResultData;

    }
}
