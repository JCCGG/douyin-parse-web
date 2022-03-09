package cn.jcc.dy.controller;

import cn.jcc.dy.pojo.po.VideoInfo;
import cn.jcc.dy.pojo.vo.ResultData;
import cn.jcc.dy.service.DyClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @program: dy-parse-server
 * @description: dy-client
 * @author: CCC
 * @create: 2022-03-08 13:46
 **/

@Controller
public class DyClientController {
    @Autowired
    private DyClientService dyClientService;
    @PostMapping("/videoInfo")
    @ResponseBody
    public ResultData<VideoInfo> getVideoInfo(@RequestParam(name="urlText") String urlText){
        return dyClientService.getVideoInfo(urlText);
    }
}
