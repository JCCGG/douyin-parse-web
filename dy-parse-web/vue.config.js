const compressionPlugin=require('compression-webpack-plugin') 

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
    }
}