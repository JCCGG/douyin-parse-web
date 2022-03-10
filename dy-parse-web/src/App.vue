<template>
  <div id="app">
    <dyComponent ></dyComponent>
  </div>
</template>

<script>
import dyComponent from './components/dy-component.vue'
export default {
  name: 'App',
  components: {
   dyComponent
  },
  mounted:()=>{
    document.title="抖音无水印视频解析！"
  }
}
</script>

<style>
</style>
