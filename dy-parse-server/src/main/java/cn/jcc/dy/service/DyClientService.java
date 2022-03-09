package cn.jcc.dy.service;

import cn.jcc.dy.pojo.po.VideoInfo;
import cn.jcc.dy.pojo.vo.ResultData;

public interface DyClientService {
    public ResultData<VideoInfo> getVideoInfo(String urlText);
}
