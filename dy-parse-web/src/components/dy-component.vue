<template>
    <div>
        <t-layout>
            <t-content class="content">
                <t-alert theme="success" message="当前仅支持抖音无水印视频，背景音乐，封面解析！"></t-alert>
                <t-alert theme="error" style="margin-top:5px;" message="解析出来的视频下载链接有时效性，大概一个小时会过期！"></t-alert>
                <t-row  style="margin-top:10px;">
                    <t-col flex="auto">
                        <t-input placeholder="输入分享的抖音视频连接" size="large" autoWidth v-model="url"/>
                    </t-col>
                    <t-col flex="none" >
                        <t-button size="large" @click="onBtnParse" :loading="isLoad">解析</t-button>
                    </t-col>
                </t-row>
                <t-divider align="left" >视频分享链接：</t-divider>
                <t-textarea  v-bind:value="shareUrl" :autosize="{minRows:2}" readonly @focus="onFocusCopy" />
                <t-divider align="left" >视频下载链接：</t-divider>
                <t-textarea status="success" v-bind:value="videoUrl" :autosize="{minRows:5}" readonly @focus="onFocusCopy" />
                <t-divider align="left" >BGM链接：</t-divider>
                <t-textarea status="warning" :value="music" :autosize="{minRows:2}" readonly @focus="onFocusCopy"/>
                <t-divider align="left" >封面链接：</t-divider>
                <t-textarea status="error" :value="bgImage" :autosize="{minRows:3}" readonly @focus="onFocusCopy"/>

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
            shareUrl:"",
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
        // 点击复制
        onFocusCopy(v){
            const that=this
            if(v){
                that.$copyText(v).then(
                    function () {
                        that.showSuccessMsg("复制成功！")
                        }, 
                    function (e) {
                        that.showErroringsMsg("复制出错："+e)
                        }
                )
            }
        },
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
                    that.bgImage=res.data.data.bgImage
                    that.shareUrl=res.data.data.shareUrl
                    that.msgShow=true
                    that.showSuccessMsg(res.data.msg)
                }else{
                    that.showWarningsMsg(res.data.msg)
                }
                that.isLoad=false;
            }).catch(function(err){
                console.log('出错了：'+err)
                that.showErroringsMsg("网络异常！")
                that.isLoad=false;
            })
           
        },
        onOpenGithub:function(){
            this.opurl=true;
            window.location.href="https://github.com/JCCGG/DouYin-Parse";
        },
        showSuccessMsg(msg){
            this.$message.success({
            content: msg,
            duration: 3000,
            zIndex: 1001,
            });
        },
        showWarningsMsg(msg){
            this.$message.warning({
            content: msg,
            duration: 3000,
            zIndex: 1001,
            });
        },
         showErroringsMsg(msg){
            this.$message.error({
            content: msg,
            duration: 3000,
            zIndex: 1001,
            });
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