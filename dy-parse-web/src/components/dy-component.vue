<template>
    <div>
        <t-layout>
            <t-content class="content">
                <t-alert theme="success" message="当前仅支持抖音水印视频，背景音乐，封面解析！"></t-alert>
                <t-row  style="margin-top:10px;">
                    <t-col flex="auto">
                        <t-input size="large" v-model="url"/>
                    </t-col>
                    <t-col flex="none" >
                        <t-button size="large" @click="onBtnParse" :loading="isLoad">解析</t-button>
                    </t-col>
                </t-row>
                <t-divider align="left" >视频链接：</t-divider>
                <t-textarea status="success" v-bind:value="videoUrl" :autosize="{minRows:5}" readonly />
                <t-divider align="left" >BGM链接：</t-divider>
                <t-textarea status="warning" :value="music" :autosize="{minRows:2}" readonly />
                <t-divider align="left" >封面链接：</t-divider>
                <t-textarea status="error" :value="bgImage" :autosize="{minRows:3}" readonly />

            </t-content>
            <t-footer align="center">
                <div>
                    <t-tooltip    
                        content="点击打开项目地址！"
                        placement="top"
                        :overlay-style="{ width: '150px' }"
                        show-arrow>
                        <logo-github-filled-icon variant="outline" size="25px" @click="onOpenGithub" />
                        <t-loading size="small" :loading="opurl"></t-loading>
                    </t-tooltip>
                </div>
                <div>注：项目仅供学习交流，不能用于违法用途，如侵犯了您的利益，请联系删除！</div>
            </t-footer>
        </t-layout>
    </div>
    
</template>

<script>
import request from '@/utils/request'
import {LogoGithubFilledIcon} from 'tdesign-icons-vue';
export default {
    name:'AxiosTest',
    data:function(){
        return {
            videoUrl:"",
            music:"",
            bgImage:"",
            opurl:false,
            isLoad:false,
            url:"",
            msgShow:true
        }
    },
    components:{
        LogoGithubFilledIcon
    },
    mounted:function(){
    },
    methods:{
        onBtnParse:function(){
            const that=this;
            this.isLoad=true;
            request({
                method: 'post',
                url: '/videoInfo',
                withCredentials:false,
                data:{
                    urlText:that.url
                }

            }).then(function(res){
                console.log(res.data);
                if(res.data.code==0){
                    that.videoUrl=res.data.data.vedioUrl
                    that.music=res.data.data.music
                    that.msgShow=true
                }
                that.isLoad=false;
            }).catch(function(err){
                console.log('出错了：'+err)
                that.isLoad=false;
            })
           
        },
        onOpenGithub:function(){
            this.opurl=true;
            window.location.href="https://github.com/JCCGG/DouYin-Parse";
        }
    }
}
</script>

<style>

.content{
    background: rgb(255, 255, 255);
    padding: 30px;
}

.msg{
    position: relative;
    left: 0%;
}

</style>