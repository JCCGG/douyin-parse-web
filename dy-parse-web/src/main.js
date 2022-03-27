import Vue from 'vue'
import App from './App.vue'
// import TDesign from 'tdesign-vue'
import { Button,Layout,Content, Footer,Alert,Divider,Textarea,Tooltip,Input,MessagePlugin,Loading,Icon,Col,Row} from 'tdesign-vue'
import 'tdesign-vue/es/style/index.css'
import VueClipboard from 'vue-clipboard2'
VueClipboard.config.autoSetContainer = true
Vue.use(VueClipboard)

// Vue.use(TDesign)
Vue.use(Button)
Vue.use(Layout)
Vue.use(Content)
Vue.use(Footer)
Vue.use(Alert)
Vue.use(Divider)
Vue.use(Textarea)
Vue.use(Tooltip)
Vue.use(Input)
Vue.use(MessagePlugin)
Vue.use(Loading)
Vue.use(Icon)
Vue.use(Col)
Vue.use(Row)
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
