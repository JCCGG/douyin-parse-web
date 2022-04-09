const compressionPlugin=require('compression-webpack-plugin') 
const name=process.env.VUE_APP_TITLE||"抖音无水印视频解析"

module.exports = {
    productionSourceMap: false,
    configureWebpack: () => {
        // 开启gzip压缩
        if (process.env.NODE_ENV === 'production') {
            return {
                plugins: [
                    new compressionPlugin({
                        test: /\.(js|css)$/, // 匹配文件名
                        threshold: 10240, // 超过10kb的数据才压缩
                        deleteOriginalAssets: false, // 是否删除原文件
                    }),
                ],
            };
        }
    },
    chainWebpack: (config) => {
        config
            .plugin('html')
            .tap((args) => {
                args[0].title = name;
                return args;
            });
    }
}