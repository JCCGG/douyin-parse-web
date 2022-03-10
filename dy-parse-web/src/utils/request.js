import axios from 'axios';
import qs from 'qs';

const request=axios.create({
    baseURL:'http://localhost:9099',
    timeout:15000,

    headers:{
        "Content-Type":"application/x-www-form-urlencoded;charset=UTF-8"
}
})

request.interceptors.request.use((config)=>{
    config.data=qs.stringify(config.data)
    return config
})


export default request