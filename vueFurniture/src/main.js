// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import ElementUI from 'element-ui'
import router from './router'
import axios from 'axios'
// var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8088/api'
axios.defaults.withCredentials = true
Vue.prototype.$axios = axios
Vue.config.productionTip = false
Vue.use(ElementUI)
// 加载 element 组件库的样式
import 'element-ui/lib/theme-chalk/index.css'
// 加载全局样式文件
// import './styles/index.less'
Vue.prototype.$hostURL='http://localhost:8088'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
