import Vue from 'vue'
import App from './App.vue'
import TDesign from 'tdesign-vue'
import 'tdesign-vue/es/style/index.css'

Vue.use(TDesign)
Vue.config.productionTip = false

new Vue({
  render: h => h(App),
}).$mount('#app')
